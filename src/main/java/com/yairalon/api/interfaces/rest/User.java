package com.yairalon.api.interfaces.rest;

import com.yairalon.api.application.dto.BirthChartResponseDTO;
import com.yairalon.api.application.dto.UserOnboardRequestDTO;
import com.yairalon.api.application.service.UserService;
import io.smallrye.mutiny.Uni;
import jakarta.annotation.security.PermitAll;
import jakarta.annotation.security.RolesAllowed;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;


@Path("/user")
@ApplicationScoped
@PermitAll
public class User {

    @Inject
    UserService userservice;
    
    @Path("/onboardUser")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Uni<Response> onboardUser(UserOnboardRequestDTO req) {

        return userservice.userOnboard(req)
                .onItem().transform(result -> Response.ok(result).build())
                .onFailure().recoverWithItem(err -> {
                    if (err instanceof IllegalStateException && err.getMessage().contains("E-mail já cadastrado, por favor faça login.")) {
                        return Response.status(Response.Status.CONFLICT)
                                .entity("E-mail já cadastrado, por favor faça login.")
                                .build();
                    }
                    // Generic server error fallback
                    return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
                });
    }

    @Path("/userdata")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Uni<Response> userdata(@QueryParam("id") String id) {
        return userservice.getUserData(id)
                .onItem().transform(result -> Response.ok(result).build())
                .onFailure().recoverWithItem(Response.status(Response.Status.INTERNAL_SERVER_ERROR).build());

    }

    @Path("/userdata/userAuth")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Uni<Response> userDatabyAuthId(@QueryParam("userAuthUUID") String userAuthUUID) {
        return userservice.getUserDatabyAuthId(userAuthUUID)
                .onItem().transform(result -> Response.ok(result).build())
                .onFailure().recoverWithItem(Response.status(Response.Status.INTERNAL_SERVER_ERROR).build());

    }


    @Path("/userbirthmapregister")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Uni<Response> userBirthMapRegister(BirthChartResponseDTO req) {
        return userservice.birthChartRegister(req, null )
                .onItem().transform( t -> Response.ok(t).build() )
                .onFailure().recoverWithItem(Response.status(Response.Status.INTERNAL_SERVER_ERROR).build());

    }

}
