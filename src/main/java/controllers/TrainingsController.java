package controllers;

import models.Training;
import services.TrainingService;

import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

@Path("/trainings")
public class TrainingsController {

    @Inject
    private TrainingService trainingService;

    @GET
    @Produces("application/json")
    public JsonArray getAll() {
        JsonArrayBuilder builder = Json.createArrayBuilder();
        for (Training training : trainingService.getAll()) {
            builder.add(
                    Json.createObjectBuilder().add("id", training.getId())
                            .add("description", training.getDescription())
                            .add("date", training.getDate().toString())
            );
        }
        return builder.build();
    }

}
