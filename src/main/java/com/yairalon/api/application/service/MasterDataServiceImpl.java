package com.yairalon.api.application.service;

import com.yairalon.api.application.dto.AspectDTO;
import com.yairalon.api.application.dto.HouseDTO;
import com.yairalon.api.application.dto.PlanetDTO;
import com.yairalon.api.application.dto.SignDTO;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class MasterDataServiceImpl implements MasterDataService {

    @Override
    public Uni<AspectDTO> createAspect(AspectDTO aspect){
        return Uni.createFrom().item(() -> newAspect(aspect));
    }

    AspectDTO newAspect(AspectDTO requestDTO){
        return new AspectDTO();
    }

    @Override
    public Uni<AspectDTO> updateAspect(AspectDTO aspect){
        return Uni.createFrom().item(() -> setAspect(aspect));
    }


    AspectDTO setAspect(AspectDTO requestDTO){
        return new AspectDTO();
    }

    @Override
    public Uni<AspectDTO> getAspect(String id){

        return null;
    }

    @Override
    public Uni<String> removeAspect(String id){
        return Uni.createFrom().item(id);
    }



    @Override
    public Uni<HouseDTO> createhouse(HouseDTO house){
        return Uni.createFrom().item(() -> newHouse(house));
    }

    HouseDTO newHouse(HouseDTO requestDTO){
        return new HouseDTO();
    }

    @Override
    public Uni<HouseDTO> updateHouse(HouseDTO house){
        return Uni.createFrom().item(() -> setHouse(house));
    }


    HouseDTO setHouse(HouseDTO requestDTO){
        return new HouseDTO();
    }

    @Override
    public Uni<HouseDTO> getHouse(String id){

        return null;
    }

    @Override
    public Uni<String> removeHouse(String id){
        return Uni.createFrom().item(id);
    }



    @Override
    public Uni<PlanetDTO> createplanet(PlanetDTO planet){
        return Uni.createFrom().item(() -> newPlanet(planet));
    }

    PlanetDTO newPlanet(PlanetDTO requestDTO){
        return new PlanetDTO();
    }

    @Override
    public Uni<PlanetDTO> updatePlanet(PlanetDTO planet){
        return Uni.createFrom().item(() -> setHouse(planet));
    }


    PlanetDTO setHouse(PlanetDTO requestDTO){
        return new PlanetDTO();
    }

    @Override
    public Uni<PlanetDTO> getPlanet(String id){

        return null;
    }

    @Override
    public Uni<String> removePlanet(String id){
        return Uni.createFrom().item(id);
    }



    @Override
    public Uni<PlanetDTO> createSign(SignDTO sign){
        return Uni.createFrom().item(() -> newSign(sign));
    }

    PlanetDTO newSign(SignDTO requestDTO){
        return new PlanetDTO();
    }

    @Override
    public Uni<SignDTO> updateSign(SignDTO sign){
        return Uni.createFrom().item(() -> setSign(sign));
    }


    SignDTO setSign(SignDTO requestDTO){
        return new SignDTO();
    }

    @Override
    public Uni<SignDTO> getSign(String id){

        return null;
    }

    @Override
    public Uni<String> removeSign(String id){
        return Uni.createFrom().item(id);
    }


}
