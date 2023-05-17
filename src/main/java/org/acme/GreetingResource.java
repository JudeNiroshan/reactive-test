package org.acme;

import io.smallrye.mutiny.Uni;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/")
public class GreetingResource {

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
}
