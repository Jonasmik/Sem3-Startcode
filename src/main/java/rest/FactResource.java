package rest;

import com.google.gson.Gson;
import utils.HttpUtils;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.io.IOException;
import java.util.concurrent.ExecutionException;

@Path("/facts")
public class FactResource {
    Gson gson = new Gson();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @RolesAllowed("user")
    public String getFacts() throws InterruptedException, ExecutionException, IOException {
        String result = gson.toJson(HttpUtils.fetchFacts());
        return result;
    }
}