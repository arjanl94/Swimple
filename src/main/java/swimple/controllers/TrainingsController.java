package swimple.controllers;

import swimple.filters.Authenticated;
import swimple.filters.AuthenticatedUser;
import swimple.filters.AuthenticatedUserDetails;
import swimple.models.Comment;
import swimple.models.Training;
import swimple.models.User;
import swimple.services.CommentService;
import swimple.services.TrainingService;
import swimple.services.UserService;

import javax.inject.Inject;
import javax.json.JsonObject;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import java.security.Principal;

@Path("/trainings")
@Produces("application/json")
@Authenticated
public class TrainingsController {

    @Inject
    private TrainingService trainingService;

    @Inject
    private CommentService commentService;

    @Inject
    private UserService userService;

    @Context
    SecurityContext context;

    @GET
    public Response index() {
        return Response.ok(trainingService.getAll()).build();
    }

    @POST
    public Response create(Training training) {
        return Response.ok(trainingService.create(training)).build();
    }

    @GET
    @Path("{id}")
    public Response show(@PathParam("id") int id) {
        return Response.ok(trainingService.get(id)).build();
    }

    @GET
    @Path("{training_id}/comments")
    public Response showComments(@PathParam("training_id") int id) {
        return Response.ok(commentService.getForTraining(id)).build();
    }

    @POST
    @Path("{training_id}/comments")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createComment(@PathParam("training_id") int id, JsonObject params) {
        Training training = trainingService.get(id);
        Principal userPrincipal = context.getUserPrincipal();
        String userName = userPrincipal.getName();
        User user = userService.findByEmail(userName);

        Comment comment = new Comment();
        comment.setTraining(training);
        comment.setUser(user);
        comment.setBody(params.getString("body"));


        return Response.ok(commentService.create(comment)).build();
    }


    @PUT
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response update(@PathParam("id") String id, Training training) {
        training.setId(Integer.parseInt(id));
        return Response.ok(trainingService.update(training)).build();
    }

}
