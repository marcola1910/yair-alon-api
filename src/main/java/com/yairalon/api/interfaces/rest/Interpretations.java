package com.yairalon.api.interfaces.rest;

import com.yairalon.api.application.dto.BirthChartRequestDTO;
import com.yairalon.api.application.service.EphemerisService;
import com.yairalon.api.application.service.TransitsInterpretationService;
import com.yairalon.api.application.service.factories.EphemeridesFactory;
import io.smallrye.mutiny.Uni;
import jakarta.annotation.security.PermitAll;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@PermitAll
@Path("/interpretations")
public class Interpretations {

    @Inject
    TransitsInterpretationService transitService;


    @Path("/transitsinterpretations")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Uni<Response> calculateBirthChart(@QueryParam("birthAstro") String birthAstro, @QueryParam("transitAstro") String transitAstro, @QueryParam("aspect") String aspect, @QueryParam("house") int house  ) {

        return transitService.getInterpretarion(birthAstro, transitAstro, aspect, house)
                .onItem().transform( t -> Response.ok(t).build() )
                .onFailure().recoverWithItem(Response.status(Response.Status.INTERNAL_SERVER_ERROR).build());


    }

}
