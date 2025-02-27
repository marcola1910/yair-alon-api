

package com.yairalon.api.interfaces.rest;


import com.yairalon.api.application.dto.BirthChartRequestDTO;
import com.yairalon.api.application.dto.TransitCalculationRequestDTO;
import com.yairalon.api.application.service.EphemerisService;
import com.yairalon.api.application.service.factories.EphemeridesFactory;
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

    @Path("/calculateBirthChart")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Uni<Response> calculateBirthChart(BirthChartRequestDTO req) {
        EphemerisService ephservice = EphemeridesFactory.createEphemeridesService();
        return ephservice.getBirthChart(req)
                    .onItem().transform( t -> Response.ok(t).build() )
                    .onFailure().recoverWithItem(Response.status(Response.Status.INTERNAL_SERVER_ERROR).build());

    }

    @Path("/transitCalculation")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Uni<Response> transitCalculation(TransitCalculationRequestDTO req){
        EphemerisService ephsrvc = EphemeridesFactory.createEphemeridesService();

        return ephsrvc.getTransits(req)
                .onItem().transform( t -> Response.ok(t).build() )
                .onFailure().recoverWithItem(Response.status(Response.Status.INTERNAL_SERVER_ERROR).build());
    }

}
