package swimple.controllers;

import swimple.filters.Authenticated;
import swimple.filters.AuthenticatedUser;
import swimple.models.AuthResponse;
import swimple.models.User;
import swimple.services.JwtService;
import swimple.services.UserService;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

@Path("/users")
@Produces("application/json")
public class UsersController {

    @Inject
    private UserService userService;

    @Inject
    @AuthenticatedUser
    User authenticatedUser;


    @Inject
    JwtService jwt;

    @GET
    @Authenticated
    public Response index() {
       return Response.ok(userService.getAll()).build();
    }

    @GET
    @Path("/me")
    @Authenticated
    public Response show() {
        return Response.ok(authenticatedUser).build();
    }

    @POST
    public Response create(User user) {
        User savedUser = userService.create(user);

        return Response.ok(new AuthResponse(savedUser, jwt.issueToken(savedUser.getEmail()))).build();
    }
}
