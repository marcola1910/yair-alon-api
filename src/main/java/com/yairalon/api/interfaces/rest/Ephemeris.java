

package com.yairalon.api.interfaces.rest;


import io.smallrye.mutiny.Uni;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import swisseph.DblObj;
import swisseph.SweConst;
import swisseph.SweDate;
import swisseph.SwissEph;


import java.util.HashMap;
import java.util.List;
import java.util.Map;



@Path("/ephemeris")
public class Ephemeris {

    @Path("/hello")
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public Uni<String> hello() {

        main();

        return Uni.createFrom().item("Hello, World!");
    }

    public static void main() {
        // 📅 Data e hora de nascimento: 13 de agosto de 1983, 10:46 AM (Manaus, Brasil)
        int year = 1983, month = 8, day = 13;
        double hour = 10 + (46 / 60.0) + 4; // Convertendo para UTC-0 (Manaus UTC-4 → UTC-0)
        double longitude = -60.0217; // Longitude de Manaus
        double latitude = -3.1019; // Latitude de Manaus

        // Carregar Swiss Ephemeris
        SwissEph swe = new SwissEph("./ephe");

        // Converter data/hora para dia juliano (UTC)
        double julianDay = SweDate.getJulDay(year, month, day, hour, true);

        // Calcular casas astrológicas (Placidus)
        double[] cusps = new double[13];
        double[] ascmc = new double[10];
        swe.swe_houses(julianDay, SweConst.SEFLG_SPEED, latitude, longitude, 'P', cusps, ascmc);
        double ascendant = ascmc[0];

        // Lista de planetas
        int[] planets = {
                SweConst.SE_SUN, SweConst.SE_MOON, SweConst.SE_MERCURY, SweConst.SE_VENUS, SweConst.SE_MARS,
                SweConst.SE_JUPITER, SweConst.SE_SATURN, SweConst.SE_URANUS, SweConst.SE_NEPTUNE, SweConst.SE_PLUTO
        };
        String[] planetNames = {
                "Sol", "Lua", "Mercúrio", "Vênus", "Marte",
                "Júpiter", "Saturno", "Urano", "Netuno", "Plutão"
        };

        // Armazenar posições dos planetas
        double[] planetPositions = new double[planets.length];

        // 🔥 Resultado do Ascendente
        System.out.printf("♏ Ascendente (Casa 1): %.2f° %s\n", ascendant, getZodiacSign(ascendant));

        // Calcular planetas
        for (int i = 0; i < planets.length; i++) {
            double[] result = new double[6];
            StringBuffer errorMsg = new StringBuffer();
            int ret = swe.swe_calc_ut(julianDay, planets[i], SweConst.SEFLG_SWIEPH, result, errorMsg);
            if (ret == SweConst.ERR) {
                System.out.println("Erro ao calcular " + planetNames[i] + ": " + errorMsg);
                continue;
            }

            double planetLongitude = result[0];
            planetPositions[i] = planetLongitude;
            int house = getHouse(planetLongitude, cusps);
            String zodiacSign = getZodiacSign(planetLongitude);

            System.out.printf("%s: Longitude %.2f° - %s - Casa %d\n", planetNames[i], planetLongitude, zodiacSign, house);
        }

        // Calcular aspectos
        System.out.println("\n🔹 Aspectos Planetários 🔹");
        for (int i = 0; i < planets.length; i++) {
            for (int j = i + 1; j < planets.length; j++) {
                checkAspect(planetNames[i], planetPositions[i], planetNames[j], planetPositions[j]);
            }
        }
    }

    // Retorna o signo zodiacal baseado na longitude
    private static String getZodiacSign(double longitude) {
        String[] signs = {
                "Áries", "Touro", "Gêmeos", "Câncer", "Leão", "Virgem",
                "Libra", "Escorpião", "Sagitário", "Capricórnio", "Aquário", "Peixes"
        };
        return signs[(int) (longitude / 30)];
    }

    // Determina a casa astrológica do planeta
    private static int getHouse(double planetLongitude, double[] cusps) {
        for (int house = 1; house <= 12; house++) {
            double start = cusps[house];
            double end = (house == 12) ? cusps[1] + 360 : cusps[house + 1];

            if (start > end) { // Lidar com a transição de 0° Áries
                if ((planetLongitude >= start && planetLongitude < 360) || (planetLongitude >= 0 && planetLongitude < end))
                    return house;
            } else {
                if (planetLongitude >= start && planetLongitude < end)
                    return house;
            }
        }
        return 0;
    }

    // Verifica e imprime aspectos planetários
    private static void checkAspect(String planet1, double pos1, String planet2, double pos2) {
        double angle = Math.abs(pos1 - pos2);
        angle = angle > 180 ? 360 - angle : angle; // Ajustar para máximo 180°
        String aspect = null;

        if (Math.abs(angle - 0) <= 8) aspect = "Conjunção (0°)";
        else if (Math.abs(angle - 180) <= 8) aspect = "Oposição (180°)";
        else if (Math.abs(angle - 120) <= 8) aspect = "Trígono (120°)";
        else if (Math.abs(angle - 90) <= 8) aspect = "Quadratura (90°)";
        else if (Math.abs(angle - 60) <= 6) aspect = "Sextil (60°)";

        if (aspect != null) {
            System.out.printf("%s (%s) ↔ %s (%s) → %s\n",
                    planet1, String.format("%.2f°", pos1),
                    planet2, String.format("%.2f°", pos2),
                    aspect);
        }
    }

}
