package com.yairalon.api.repository;
import com.yairalon.api.model.UserMap;
import io.quarkus.mongodb.panache.reactive.ReactivePanacheMongoRepository;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;
@ApplicationScoped
public class UserMapRepository implements ReactivePanacheMongoRepository<UserMap> {
    public Uni<UserMap> findById(String id) {
        return find("id", id).firstResult();
    }

    public Uni<List<UserMap>> findByUUID(String useruuid) {
        return find("useruuid", useruuid).list();
    }

    public void create(UserMap map) {
        create(map);
    }

}
