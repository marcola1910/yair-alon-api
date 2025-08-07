package com.yairalon.api.interfaces.rest;

import com.yairalon.api.application.dto.*;
import com.yairalon.api.application.service.MasterDataService;
import com.yairalon.api.application.service.factories.MasterDataFactory;
import io.smallrye.mutiny.Uni;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/masterdata")
public class MasterData {

    @Path("/aspect")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Uni<Response> createAspect(AspectDTO req) {
        MasterDataService masterdataservice = MasterDataFactory.createMasterData();
        return masterdataservice.createAspect(req)
                .onItem().transform( t -> Response.ok(t).build() )
                .onFailure().recoverWithItem(Response.status(Response.Status.INTERNAL_SERVER_ERROR).build());

    }

    @Path("/aspect")
    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    public Uni<Response> updateAspect(AspectDTO req) {
        MasterDataService masterdataservice = MasterDataFactory.createMasterData();
        return masterdataservice.updateAspect(req)
                .onItem().transform( t -> Response.ok(t).build() )
                .onFailure().recoverWithItem(Response.status(Response.Status.INTERNAL_SERVER_ERROR).build());

    }

    @GET
    @Path("/aspect/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Uni<Response> getAspect(@PathParam("id") String id) {
        MasterDataService masterDataservice = MasterDataFactory.createMasterData();
        return masterDataservice.getAspect(id)
                .onItem().transform(aspect -> Response.ok(aspect).build())
                .onFailure().recoverWithItem(err ->
                        Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(err.getMessage()).build()
                );
    }


    @Path("/aspect/{id}")
    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    public Uni<Response> removeAspect(@PathParam("id") String id) {
        MasterDataService masterDataservice = MasterDataFactory.createMasterData();
        return masterDataservice.removeAspect(id)
                .onItem().transform(aspect -> Response.ok(aspect).build())
                .onFailure().recoverWithItem(err ->
                        Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(err.getMessage()).build()
                );
    }

    @Path("/house")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Uni<Response> createhouse(HouseDTO req) {
        MasterDataService masterdataservice = MasterDataFactory.createMasterData();
        return masterdataservice.createhouse(req)
                .onItem().transform( t -> Response.ok(t).build() )
                .onFailure().recoverWithItem(Response.status(Response.Status.INTERNAL_SERVER_ERROR).build());

    }

    @Path("/house")
    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    public Uni<Response> updateHouse(HouseDTO req) {
        MasterDataService masterdataservice = MasterDataFactory.createMasterData();
        return masterdataservice.updateHouse(req)
                .onItem().transform( t -> Response.ok(t).build() )
                .onFailure().recoverWithItem(Response.status(Response.Status.INTERNAL_SERVER_ERROR).build());

    }

    @GET
    @Path("/house/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Uni<Response> gethouse(@PathParam("id") String id) {
        MasterDataService masterDataservice = MasterDataFactory.createMasterData();
        return masterDataservice.getHouse(id)
                .onItem().transform(house -> Response.ok(house).build())
                .onFailure().recoverWithItem(err ->
                        Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(err.getMessage()).build()
                );
    }

    @Path("/house/{id}")
    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    public Uni<Response> removeHouse(@PathParam("id") String id) {
        MasterDataService masterDataservice = MasterDataFactory.createMasterData();
        return masterDataservice.removeHouse(id)
                .onItem().transform(aspect -> Response.ok(aspect).build())
                .onFailure().recoverWithItem(err ->
                        Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(err.getMessage()).build()
                );
    }

    @Path("/planet")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Uni<Response> createplanet(PlanetDTO req) {
        MasterDataService masterdataservice = MasterDataFactory.createMasterData();
        return masterdataservice.createplanet(req)
                .onItem().transform( t -> Response.ok(t).build() )
                .onFailure().recoverWithItem(Response.status(Response.Status.INTERNAL_SERVER_ERROR).build());

    }

    @Path("/planet")
    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    public Uni<Response> updateplanet(PlanetDTO req) {
        MasterDataService masterdataservice = MasterDataFactory.createMasterData();
        return masterdataservice.updatePlanet(req)
                .onItem().transform( t -> Response.ok(t).build() )
                .onFailure().recoverWithItem(Response.status(Response.Status.INTERNAL_SERVER_ERROR).build());

    }

    @GET
    @Path("/planet/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Uni<Response> getplanet(@PathParam("id") String id) {
        MasterDataService masterDataservice = MasterDataFactory.createMasterData();
        return masterDataservice.getPlanet(id)
                .onItem().transform(house -> Response.ok(house).build())
                .onFailure().recoverWithItem(err ->
                        Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(err.getMessage()).build()
                );
    }

    @Path("/planet/{id}")
    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    public Uni<Response> removePlanet(@PathParam("id") String id) {
        MasterDataService masterDataservice = MasterDataFactory.createMasterData();
        return masterDataservice.removePlanet(id)
                .onItem().transform(aspect -> Response.ok(aspect).build())
                .onFailure().recoverWithItem(err ->
                        Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(err.getMessage()).build()
                );
    }

    @Path("/sign")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Uni<Response> createsign(SignDTO req) {
        MasterDataService masterdataservice = MasterDataFactory.createMasterData();
        return masterdataservice.createSign(req)
                .onItem().transform( t -> Response.ok(t).build() )
                .onFailure().recoverWithItem(Response.status(Response.Status.INTERNAL_SERVER_ERROR).build());

    }

    @Path("/sign")
    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    public Uni<Response> updatesign(SignDTO req) {
        MasterDataService masterdataservice = MasterDataFactory.createMasterData();
        return masterdataservice.updateSign(req)
                .onItem().transform( t -> Response.ok(t).build() )
                .onFailure().recoverWithItem(Response.status(Response.Status.INTERNAL_SERVER_ERROR).build());

    }

    @GET
    @Path("/sign/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Uni<Response> getSign(@PathParam("id") String id) {
        MasterDataService masterDataservice = MasterDataFactory.createMasterData();
        return masterDataservice.getSign(id)
                .onItem().transform(house -> Response.ok(house).build())
                .onFailure().recoverWithItem(err ->
                        Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(err.getMessage()).build()
                );
    }

    @Path("/sign/{id}")
    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    public Uni<Response> removeSign(@PathParam("id") String id) {
        MasterDataService masterDataservice = MasterDataFactory.createMasterData();
        return masterDataservice.removeSign(id)
                .onItem().transform(aspect -> Response.ok(aspect).build())
                .onFailure().recoverWithItem(err ->
                        Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(err.getMessage()).build()
                );
    }

}
