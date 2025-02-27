package com.yairalon.api.application.service;

import com.yairalon.api.application.dto.*;
import io.smallrye.mutiny.Uni;
import swisseph.*;
import java.time.LocalDateTime;

public class EphemerisServiceImpl implements EphemerisService {

    private static final int[] PLANETS = {
            SweConst.SE_SUN, SweConst.SE_MOON, SweConst.SE_MERCURY, SweConst.SE_VENUS, SweConst.SE_MARS,
            SweConst.SE_JUPITER, SweConst.SE_SATURN, SweConst.SE_URANUS, SweConst.SE_NEPTUNE, SweConst.SE_PLUTO
    };
    private static final String[] PLANET_NAMES = {
            "Sol", "Lua", "Mercúrio", "Vênus", "Marte",
            "Júpiter", "Saturno", "Urano", "Netuno", "Plutão"
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
        double[] cusps = new double[13];
        double[] ascmc = new double[10];
        swe.swe_houses(julianDay, SweConst.SEFLG_SPEED, latitude, longitude, 'P', cusps, ascmc);
        responseDTO.setAscendent(new AscDTO(ascmc[0], getZodiacSign(ascmc[0])));

        for (int i = 0; i < PLANETS.length; i++) {
            double[] result = new double[6];
            int ret = swe.swe_calc_ut(julianDay, PLANETS[i], SweConst.SEFLG_SWIEPH, result, new StringBuffer());
            if (ret == SweConst.ERR) continue;
            boolean retrograde = (result[3] < 0);
            responseDTO.getPlanets().add(new PlanetsDTO(PLANET_NAMES[i], result[0], getZodiacSign(result[0]), getHouse(result[0], cusps), retrograde));
        }
        calculateAspects(responseDTO.getPlanets(), responseDTO.getAspects());
        return responseDTO;
    }

    public TransitResponseDTO calculateTransits(TransitCalculationRequestDTO transit) {
        double julianNow = SweDate.getJulDay(transit.getDateTime().getYear(), transit.getDateTime().getMonthValue(), transit.getDateTime().getDayOfMonth(), 0, true);

        TransitResponseDTO transitResponse = new TransitResponseDTO();
        transitResponse.setNatalChart(transit.getBirthChart());

        for (PlanetsDTO natalPlanet : transit.getBirthChart().getPlanets()) {
            for (int i = 0; i < PLANETS.length; i++) {
                double[] result = new double[6];
                swe.swe_calc_ut(julianNow, PLANETS[i], SweConst.SEFLG_SWIEPH, result, new StringBuffer());
                AspectsDTO aspect = checkAspect(natalPlanet.getPlanetName(), natalPlanet.getPlanetLongitude(), PLANET_NAMES[i], result[0], natalPlanet.getHouse());
                if (aspect != null) transitResponse.getTransitAspects().add(aspect);
            }
        }
        return transitResponse;
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
        if (Math.abs(angle - 0) <= 8) aspect = "Conjunção (0°)";
        else if (Math.abs(angle - 180) <= 8) aspect = "Oposição (180°)";
        else if (Math.abs(angle - 120) <= 8) aspect = "Trígono (120°)";
        else if (Math.abs(angle - 90) <= 8) aspect = "Quadratura (90°)";
        else if (Math.abs(angle - 60) <= 6) aspect = "Sextil (60°)";
        return aspect != null ? new AspectsDTO(planet1, planet2, aspect, house ) : null;
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

}
