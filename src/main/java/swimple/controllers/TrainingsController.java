package swimple.controllers;

import swimple.filters.Authenticated;
import swimple.models.Training;
import swimple.services.TrainingService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/trainings")
@Produces("application/json")
@Authenticated
public class TrainingsController {

    @Inject
    private TrainingService trainingService;

    @GET
    public Response index() {
        return Response.ok(trainingService.getAll()).build();
//        return Response.ok(trainingService.getAll()).build();
    }

    @POST
    public Response create(Training training) {
        return Response.ok(trainingService.create(training)).build();
    }

    @GET
    @Path("{id}")
    public Response show(@PathParam("id") String id) {
        return Response.ok(trainingService.get(id)).build();
    }


    @PUT
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response update(@PathParam("id") String id, Training training) {
        training.setId(Integer.parseInt(id));
        return Response.ok(trainingService.update(training)).build();
    }

}
