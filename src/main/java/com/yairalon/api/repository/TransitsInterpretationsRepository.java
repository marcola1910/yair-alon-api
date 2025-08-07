package com.yairalon.api.repository;

import com.yairalon.api.model.TransitsInterpretations;
import com.yairalon.api.model.UserMap;
import io.quarkus.mongodb.panache.reactive.ReactivePanacheMongoRepository;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;

@ApplicationScoped
public class TransitsInterpretationsRepository implements ReactivePanacheMongoRepository<TransitsInterpretations>  {
    public Uni<TransitsInterpretations> findById(String id) {
        return find("id", id).firstResult();
    }

    public Uni<List<TransitsInterpretations>> findByUUID(String uuid) {
        return find("UUID", uuid).list();
    }

    public void create(TransitsInterpretations transitsInterpretations) {
        create(transitsInterpretations);
    }

    public Uni<TransitsInterpretations> findByAstrosAndAspect(String birthAstro, String transitAstro, String aspect, int house) {
        return find("birthAstro = ?1 and transitAstro = ?2 and aspect = ?3 and house = ?4", birthAstro, transitAstro, aspect, house)
                .firstResult();
    }
}
