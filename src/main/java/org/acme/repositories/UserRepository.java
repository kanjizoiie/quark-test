package org.acme.repositories;


import io.quarkus.hibernate.orm.panache.PanacheRepository;

import jakarta.enterprise.context.ApplicationScoped;
import org.acme.entities.UserObject;

import java.util.List;

@ApplicationScoped
public class UserRepository implements PanacheRepository<UserObject> {

    public List<UserObject> findAlive(){
        return listAll();
    }
}
