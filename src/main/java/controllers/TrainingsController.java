package controllers;

import models.Training;
import services.TrainingService;

import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

@Path("/trainings")
public class TrainingsController {

    @Inject
    private TrainingService trainingService;

    @GET
    @Produces("application/json")
    public Response index() {
        return Response.ok(trainingService.getAll()).build();
    }

    @GET
    @Path("{id}")
    @Produces("application/json")
    public Response show(@PathParam("id") String id) {
        return Response.ok(trainingService.get(id)).build();
    }

}
