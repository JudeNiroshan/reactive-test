package org.acme;

import io.smallrye.mutiny.Uni;
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
    public Uni<List<Fruit>> add(JsonObject jsonObject) {

        // Non reactive [data validations]
        if (jsonObject.getString("name") == null ||
                jsonObject.getString("description") == null) {
            return Uni.createFrom().failure(new IllegalArgumentException());
        }
        Fruit model = new Fruit(jsonObject.getString("name"),
                jsonObject.getString("description"));

        // Reactive [data persistence]
        return Uni.createFrom().item(model)
                .map(fruit -> {
                    fruitService.add(model);
                    return null;})
                .onItem()
                .transform(ignored -> list());
    }
}
