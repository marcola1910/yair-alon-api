package com.yairalon.api.application.service;

import com.yairalon.api.application.dto.*;
import io.smallrye.mutiny.Uni;
import swisseph.*;
import java.time.LocalDateTime;



public class EphemerisServiceImpl implements EphemerisService {

    private static final int[] PLANETS = {
            SweConst.SE_SUN,
            SweConst.SE_MOON,
            SweConst.SE_MERCURY,
            SweConst.SE_VENUS,
            SweConst.SE_MARS,
            SweConst.SE_JUPITER,
            SweConst.SE_SATURN,
            SweConst.SE_URANUS,
            SweConst.SE_NEPTUNE,
            SweConst.SE_PLUTO,
            SweConst.SE_CHIRON,
            SweConst.SE_TRUE_NODE,
            SweConst.SE_MEAN_APOG  // Adding Chiron, North Node, and Lilith
    };

    private static final int margin = 3;

    private static final String[] PLANET_NAMES = {
            "Sol",
            "Lua",
            "Mercúrio",
            "Vênus",
            "Marte",
            "Júpiter",
            "Saturno",
            "Urano",
            "Netuno",
            "Plutão",
            "Quíron",
            "Nodo Norte",
            "Lilith"  // Adding names for Chiron, North Node, and Lilith
    };

    private final SwissEph swe = new SwissEph("./ephe");

    public BirthChartResponseDTO calculateEphemeris(BirthChartRequestDTO requestDTO) {
        int year = requestDTO.getBirthday().getYear();
        int month = requestDTO.getBirthday().getMonthValue();
        int day = requestDTO.getBirthday().getDayOfMonth();
        double hour = requestDTO.getBirthday().getHour() + (requestDTO.getBirthday().getMinute() / 60.0) - requestDTO.getUtc();
        double latitude = requestDTO.getLatPlace();
        double longitude = requestDTO.getLongPlace();
        double julianDay = SweDate.getJulDay(year, month, day, hour, true);

        BirthChartResponseDTO responseDTO = new BirthChartResponseDTO();
        responseDTO.setInputData(requestDTO);
        double[] cusps = new double[13];  // Array for house cusps
        double[] ascmc = new double[10];  // Array for ASC/MC (Ascendant and Midheaven)
        swe.swe_houses(julianDay, SweConst.SEFLG_SPEED, latitude, longitude, 'P', cusps, ascmc);

        // Add only the cusps longitude (degree) to the response
        for (int i = 1; i <= 12; i++) {
            responseDTO.getCuspides().add(new CuspDTO(cusps[i], i));  // Only adding the degree value of each cusp
        }

        // Interest Zones
        responseDTO.getInterestZone().add(new InterestZoneDTO("ASC", ascmc[0], getZodiacSign(ascmc[0])));
        responseDTO.getInterestZone().add(new InterestZoneDTO("MC", ascmc[1], getZodiacSign(ascmc[1])));
        responseDTO.getInterestZone().add(new InterestZoneDTO("DSC", normalizeDegree(ascmc[0] + 180), getZodiacSign(normalizeDegree(ascmc[0] + 180))));
        responseDTO.getInterestZone().add(new InterestZoneDTO("LWM", normalizeDegree(ascmc[1] + 180), getZodiacSign(normalizeDegree(ascmc[1] + 180))));

// --- Fixed Stars Calculation ---
        String[] fixedStarNames = {"ACRUX", "REGULUS", "SPICA", "ALDEBARAN", "SIRIUS"};
        for (String fixedStar : fixedStarNames) {
            double[] starResult = new double[6];
            StringBuffer starError = new StringBuffer();
            // Create a StringBuffer for the star name, as required by swe_fixstar
            StringBuffer starNameBuffer = new StringBuffer(fixedStar);
            int ret = swe.swe_fixstar(starNameBuffer, julianDay, SweConst.SEFLG_SWIEPH, starResult, starError);
            if (ret < 0) {
                System.err.println("Error calculating " + fixedStar + ": " + starError.toString());
            } else {
                int starHouse = getHouse(starResult[0], cusps);
                double starCuspAngle = calculateCuspAngle(starResult[0], starHouse, cusps);
                // Add the fixed star to the DTO.
                // Adjust the FixedStarDTO constructor parameters as needed (here we pass name, house, and longitude).
                responseDTO.getFixedStars().add(new FixedStarDTO(fixedStar, starHouse, starResult[0]));
            }
        }


        // Planets data
        for (int i = 0; i < PLANETS.length; i++) {
            double[] result = new double[6];
            int ret = swe.swe_calc_ut(julianDay, PLANETS[i], SweConst.SEFLG_SWIEPH, result, new StringBuffer());
            if ( ret == SweConst.ERR) continue;
            boolean retrograde = (result[3] < 0);
            int house = getHouse(result[0], cusps);
            double cuspAngle = calculateCuspAngle(result[0], house, cusps);  // Novo cálculo do ângulo do planeta na cúspide da casa

            responseDTO.getPlanets().add(new PlanetsDTO(PLANET_NAMES[i], result[0], getZodiacSign(result[0]), getHouse(result[0], cusps), retrograde, cuspAngle));

        }
        calculateAspects(responseDTO.getPlanets(), responseDTO.getAspects());
        return responseDTO;
    }

    public TransitResponseDTO calculateTransits(TransitCalculationRequestDTO transit) {

        double hour = transit.getBirthChart().getInputData().getBirthday().getHour() + (transit.getBirthChart().getInputData().getBirthday().getMinute() / 60.0) - transit.getBirthChart().getInputData().getUtc();

        double julianNow = SweDate.getJulDay(transit.getDateTime().getYear(), transit.getDateTime().getMonthValue(), transit.getDateTime().getDayOfMonth(), 0, true);
        TransitResponseDTO transitResponse = new TransitResponseDTO();
        transitResponse.setNatalChart(transit.getBirthChart());

        double[] transitCusps = new double[13];
        double[] transitAscmc = new double[10];
        swe.swe_houses(julianNow, SweConst.SEFLG_SPEED, transit.getBirthChart().getInputData().getLatPlace(), transit.getBirthChart().getInputData().getLongPlace(), 'P', transitCusps, transitAscmc);

        for (int i = 1; i <= 12; i++)
            transitResponse.getCuspides().add(new CuspDTO(transitCusps[i], i));

        for (PlanetsDTO natalPlanet : transit.getBirthChart().getPlanets()) {
            for (int i = 0; i < transit.getBirthChart().getPlanets().size(); i++) {
                double[] result = new double[6];
                swe.swe_calc_ut(julianNow, PLANETS[i], SweConst.SEFLG_SWIEPH, result, new StringBuffer());
                AspectsDTO aspect = checkAspect(natalPlanet.getPlanetName(), natalPlanet.getPlanetLongitude(), PLANET_NAMES[i], result[0], natalPlanet.getHouse());
                if (aspect != null) {
                    if(PLANETS[i] != 15) {
                        aspect.setStartDate(findAspectStartDate(natalPlanet.getPlanetLongitude(), PLANETS[i], julianNow));
                        aspect.setEndDate(findAspectEndDate(natalPlanet.getPlanetLongitude(), PLANETS[i], julianNow));
                    }
                    transitResponse.getTransitAspects().add(aspect);
                }
            }


        }

        //calculate transits on interesting points
        for (InterestZoneDTO zone : transit.getBirthChart().getInterestZone() ) {

            if(zone.getName().equals(String.valueOf("ASC"))  || zone.getName().equals(String.valueOf("MC"))) {
                for (int i = 0; i < PLANETS.length; i++) {
                    double[] result = new double[6];
                    swe.swe_calc_ut(julianNow, PLANETS[i], SweConst.SEFLG_SWIEPH, result, new StringBuffer());

                    AspectsDTO aspect = checkAspect(zone.getName(), zone.getAngle(), PLANET_NAMES[i], result[0], 0);
                    if (aspect != null)
                        if (PLANETS[i] != 15 && PLANETS[i] != 16) {
                            aspect.setStartDate(findAspectStartDate(zone.getAngle(), PLANETS[i], julianNow));
                            aspect.setEndDate(findAspectEndDate(zone.getAngle(), PLANETS[i], julianNow));
                            transitResponse.getTransitAspects().add(aspect);
                        }
                }
            }
        }

        return transitResponse;
    }

    private LocalDateTime findAspectStartDate(double natalLongitude, int transitPlanet, double julianNow) {
        return findAspectBoundary(natalLongitude, transitPlanet, julianNow, -1);
    }

    private LocalDateTime findAspectEndDate(double natalLongitude, int transitPlanet, double julianNow) {
        return findAspectBoundary(natalLongitude, transitPlanet, julianNow, 1);
    }

    private static double calculateCuspAngle(double planetLongitude, int house, double[] cusps) {
        if (house == 0) return 0; // Caso o planeta não esteja associado a uma casa
        double cuspStart = cusps[house];
        double angle = normalizeDegree(planetLongitude - cuspStart);
        return angle;
    }

    private LocalDateTime findAspectBoundary(double natalLongitude, int transitPlanet, double julianNow, int step) {
        double[] result = new double[6];
        double currentJulian = julianNow;
        StringBuffer serr = new StringBuffer();

        while (true) {
            swe.swe_calc_ut(currentJulian, transitPlanet, SweConst.SEFLG_SWIEPH, result, serr);
            AspectsDTO aspect = checkAspect("", natalLongitude, "", result[0], 0);
            if (aspect == null) break;
            currentJulian += step;
        }

        return julianToGregorian(currentJulian);
    }

    private LocalDateTime julianToGregorian(double julianDay) {
        // Adjust Julian date
        double JD = julianDay + 0.5;
        int Z = (int) JD;
        double F = JD - Z;
        int A = (int) ((Z - 1867216.25) / 36524.25);
        int B = Z + 1 + A - (int) (A / 4);
        double C = B + 1524;
        int D = (int) ((C - 122.1) / 365.25);
        int E = (int) (365.25 * D);
        int G = (int) ((C - E) / 30.6001);

        int day = (int) (C - E - (int) (30.6001 * G) + F);
        int month = G < 13.5 ? G - 1 : G - 13;
        int year = month > 2.5 ? D - 4716 : D - 4715;

        // Return as LocalDateTime
        return LocalDateTime.of(year, month, day, 0, 0);
    }

    private void calculateAspects(java.util.List<PlanetsDTO> planets, java.util.List<AspectsDTO> aspects) {
        for (int i = 0; i < planets.size(); i++) {
            for (int j = i + 1; j < planets.size(); j++) {
                AspectsDTO aspect = checkAspect(planets.get(i).getPlanetName() , planets.get(i).getPlanetLongitude(), planets.get(j).getPlanetName(), planets.get(j).getPlanetLongitude(), planets.get(j).getHouse());
                if (aspect != null) aspects.add(aspect);
            }
        }
    }

    private static AspectsDTO checkAspect(String planet1, double pos1, String planet2, double pos2, int house) {
        double angle = Math.abs(pos1 - pos2);
        angle = angle > 180 ? 360 - angle : angle;
        String aspect = null;

        if (Math.abs(angle - 0) <= margin) aspect = "Conjunção (0°)";
        else if (Math.abs(angle - 180) <= margin) aspect = "Oposição (180°)";
        else if (Math.abs(angle - 120) <= margin) aspect = "Trígono (120°)";
        else if (Math.abs(angle - 90) <= margin) aspect = "Quadratura (90°)";
        else if (Math.abs(angle - 60) <= margin) aspect = "Sextil (60°)";
        return aspect != null ? new AspectsDTO(planet1, planet2, aspect, house, pos2 ) : null;
    }

    private static String getZodiacSign(double longitude) {
        String[] signs = {"Áries", "Touro", "Gêmeos", "Câncer", "Leão", "Virgem", "Libra", "Escorpião", "Sagitário", "Capricórnio", "Aquário", "Peixes"};
        return signs[(int) (longitude / 30)];
    }

    private static int getHouse(double planetLongitude, double[] cusps) {
        for (int house = 1; house <= 12; house++) {
            double start = cusps[house];
            double end = (house == 12) ? cusps[1] + 360 : cusps[house + 1];
            if (start > end) {
                if ((planetLongitude >= start && planetLongitude < 360) || (planetLongitude >= 0 && planetLongitude < end))
                    return house;
            } else {
                if (planetLongitude >= start && planetLongitude < end)
                    return house;
            }
        }
        return 0;
    }

    @Override
    public Uni<BirthChartResponseDTO> getBirthChart(BirthChartRequestDTO requestDTO) {
        return Uni.createFrom().item(() -> calculateEphemeris(requestDTO));
    }

    @Override
    public Uni<TransitResponseDTO> getTransits(TransitCalculationRequestDTO transitCalculationRequestDTO) {
        return Uni.createFrom().item(() -> calculateTransits(transitCalculationRequestDTO));
    }

    private static double normalizeDegree(double degree) {
        return (degree % 360 + 360) % 360;
    }

}
