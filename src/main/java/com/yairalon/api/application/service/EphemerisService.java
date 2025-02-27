package com.yairalon.api.application.service;

import com.yairalon.api.application.dto.BirthChartRequestDTO;
import com.yairalon.api.application.dto.BirthChartResponseDTO;
import com.yairalon.api.application.dto.TransitCalculationRequestDTO;
import com.yairalon.api.application.dto.TransitResponseDTO;
import io.smallrye.mutiny.Uni;

public interface EphemerisService {

    Uni<BirthChartResponseDTO> getBirthChart(BirthChartRequestDTO requestDTO);
    Uni<TransitResponseDTO> getTransits(TransitCalculationRequestDTO TransitCalculationRequestDTO);

}
