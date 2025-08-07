package com.yairalon.api.application.service;

import com.yairalon.api.application.dto.TransitsInterpretationsDTO;
import com.yairalon.api.repository.TransitsInterpretationsRepository;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class TransitsInterpretationsServiceImpl implements TransitsInterpretationService {

    @Inject
    TransitsInterpretationsRepository transitsInterpretationsRepository;

    @Inject
    public TransitsInterpretationsServiceImpl(  TransitsInterpretationsRepository transitsInterpretationsRepository ) {
        this.transitsInterpretationsRepository = transitsInterpretationsRepository;
    }

    @Override
    public Uni<TransitsInterpretationsDTO> getInterpretarion(String birthAstro, String transitAstro, String aspect, int house) {
        return transitsInterpretationsRepository.findByAstrosAndAspect(birthAstro, transitAstro, aspect, house)
                .onItem().transform(t -> new TransitsInterpretationsDTO( t.getUUID(), t.getBirthAstro(), t.getTransitAstro(), t.getAspect(), t.getHouse(), t.getText(), t.getText_en(), t.getText_es(), t.getText_he()))
                .onFailure().invoke(ex -> {
                    System.err.println("❌ Error fetching interpretation");
                    ex.printStackTrace(); // Full stack trace in console
                });
    }
}
