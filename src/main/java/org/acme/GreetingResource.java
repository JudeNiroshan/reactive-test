package org.acme;

import io.smallrye.mutiny.Uni;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

import java.util.List;

@Path("/")
public class GreetingResource {

    @Inject
    FruitService fruitService;

    @GET
    @Path("classic")
    @Produces(MediaType.TEXT_PLAIN)
    public String classic() {
        return "Hello RESTEasy";
    }

    @GET
    @Path("reactive")
    @Produces(MediaType.TEXT_PLAIN)
    public Uni<String> reactive() {
        return Uni.createFrom().item("Hello RESTEasy");
    }

    @GET
    @Path("fruits")
    public List<Fruit> list() {
        return fruitService.list();
    }

    @POST
    @Path("fruits")
    public List<Fruit> add(Fruit fruit) {
        fruitService.add(fruit);
        return list();
    }
}
