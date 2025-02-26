package com.yairalon.api.application.service;

import com.yairalon.api.application.dto.BirthChartRequestDTO;
import com.yairalon.api.application.dto.BirthChartResponseDTO;
import io.smallrye.mutiny.Uni;

public interface EphemerisService {

    Uni<BirthChartResponseDTO> getBirthChart(BirthChartRequestDTO requestDTO);


}
