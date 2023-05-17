package org.acme;

import io.vertx.core.json.JsonObject;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.MediaType;

import java.util.List;

@Path("/")
public class FruitResource {
    @Inject
    FruitService fruitService;

    @GET
    @Path("fruits")
    public List<Fruit> list() {
        return fruitService.list();
    }

    @POST
    @Path("fruits")
    @Consumes(MediaType.APPLICATION_JSON)
    public List<Fruit> add(JsonObject fruit) {
        Fruit model = new Fruit(fruit.getString("name"), fruit.getString("description"));
        fruitService.add(model);
        return list();
    }
}
