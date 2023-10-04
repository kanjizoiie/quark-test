package org.acme.resources;

import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.acme.entities.UserObject;
import org.acme.repositories.UserRepository;

import java.util.List;

@Path("/user")
public class UserObjectResource {
    @Inject
    UserRepository userRepository;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public List<UserObject> getUsers() {
        return userRepository.listAll();
    }
}
