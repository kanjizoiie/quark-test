package org.acme.resources;

import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import org.acme.entities.Greeting;
import org.acme.entities.UserObject;
import org.acme.repositories.UserRepository;

import java.util.List;

@Path("/hello")
public class GreetingResource {

    @Inject
    UserRepository userRepository;


    @GET
    @Transactional
    @Produces(MediaType.TEXT_PLAIN)
    public String hello(@QueryParam("name") String name) {
        Greeting greeting = new Greeting();
        greeting.name = name;
        greeting.persist();
        return "Hello " + name;
    }


    @GET
    @Path("names")
    @Transactional
    @Produces(MediaType.APPLICATION_JSON)
    public List<UserObject> names() {
        return userRepository.listAll();
    }
}
