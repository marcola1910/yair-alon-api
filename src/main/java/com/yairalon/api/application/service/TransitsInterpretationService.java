package com.yairalon.api.application.service;

import com.yairalon.api.application.dto.TransitsInterpretationsDTO;
import io.smallrye.mutiny.Uni;

public interface TransitsInterpretationService {

    Uni<TransitsInterpretationsDTO> getInterpretarion(String birthAstro, String transitAstro, String aspect, int house);

}
