package com.yairalon.api.repository;

import com.yairalon.api.model.UserData;
import io.quarkus.mongodb.panache.reactive.ReactivePanacheMongoRepository;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;

@ApplicationScoped
public class UserDataRepository implements ReactivePanacheMongoRepository<UserData> {
    public Uni<UserData> findById(String id) {
        return find("id", id).firstResult();
    }

    public Uni<List<UserData>> findByEmail(String email) {
        return find("email", email).list();
    }

    public void create(UserData user) {
        create(user);
    }

}
