package org.example;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/Todo")
public class TodoResource {
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        return "Din mamma";
    }
}
