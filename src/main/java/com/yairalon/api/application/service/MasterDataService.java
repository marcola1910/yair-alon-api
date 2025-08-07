package com.yairalon.api.application.service;

import com.yairalon.api.application.dto.AspectDTO;
import com.yairalon.api.application.dto.HouseDTO;
import com.yairalon.api.application.dto.PlanetDTO;
import com.yairalon.api.application.dto.SignDTO;
import io.smallrye.mutiny.Uni;

public interface MasterDataService {
    Uni<AspectDTO> createAspect(AspectDTO aspect);

    Uni<AspectDTO> updateAspect(AspectDTO aspect);

    Uni<AspectDTO> getAspect(String id);

    Uni<String> removeAspect(String id);

    Uni<HouseDTO> createhouse(HouseDTO house);

    Uni<HouseDTO> updateHouse(HouseDTO house);

    Uni<HouseDTO> getHouse(String id);

    Uni<String> removeHouse(String id);

    Uni<PlanetDTO> createplanet(PlanetDTO planet);

    Uni<PlanetDTO> updatePlanet(PlanetDTO planet);

    Uni<PlanetDTO> getPlanet(String id);

    Uni<String> removePlanet(String id);

    Uni<PlanetDTO> createSign(SignDTO sign);

    Uni<SignDTO> updateSign(SignDTO sign);

    Uni<SignDTO> getSign(String id);

    Uni<String> removeSign(String id);
}
