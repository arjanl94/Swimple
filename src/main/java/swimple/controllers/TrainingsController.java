package swimple.controllers;

import swimple.models.Comment;
import swimple.models.Training;
import swimple.models.User;
import swimple.services.CommentService;
import swimple.services.TrainingService;
import swimple.services.UserService;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.json.JsonObject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/trainings")
@Produces("application/json")
public class TrainingsController {

    @Inject
    private TrainingService trainingService;

    @Inject
    private CommentService commentService;

    @Inject
    private UserService userService;

    @Inject
    User currentUser;

    @GET
    @RolesAllowed({"user", "coach", "admin"})
    public Response index() {
        return Response.ok(trainingService.getAll()).build();
    }

    @POST
    @RolesAllowed({"coach", "admin"})
    public Response create(Training training) {
        return Response.ok(trainingService.create(training)).build();
    }

    @GET
    @Path("{id}")
    @RolesAllowed({"user", "coach", "admin"})
    public Response show(@PathParam("id") int id) {
        return Response.ok(trainingService.get(id)).build();
    }

    @GET
    @Path("{training_id}/comments")
    @RolesAllowed({"user", "coach", "admin"})
    public Response showComments(@PathParam("training_id") int id) {
        return Response.ok(commentService.getForTraining(id)).build();
    }

    @POST
    @Path("{training_id}/comments")
    @Consumes(MediaType.APPLICATION_JSON)
    @RolesAllowed({"user", "coach", "admin"})
    public Response createComment(@PathParam("training_id") int id, JsonObject params) {
        Training training = trainingService.get(id);

        Comment comment = new Comment();
        comment.setTraining(training);
        comment.setUser(userService.findByEmail(currentUser.getEmail()));
        comment.setBody(params.getString("body"));


        return Response.ok(commentService.create(comment)).build();
    }


    @PUT
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @RolesAllowed({"coach", "admin"})
    public Response update(@PathParam("id") String id, Training training) {
        training.setId(Integer.parseInt(id));
        return Response.ok(trainingService.update(training)).build();
    }

}
