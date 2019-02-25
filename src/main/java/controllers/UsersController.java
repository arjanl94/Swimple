package controllers;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import filters.Authenticated;
import filters.AuthenticatedUser;
import models.AuthResponse;
import models.User;
import services.UserService;

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

    @GET
    @Authenticated
    public Response index() {
       return Response.ok(userService.getAll()).build();
    }

    @GET
    @Path("/me")
    @Authenticated
    public Response show() {
        User user = authenticatedUser;
        return Response.ok(user).build();
    }

    @POST
    public Response create(User user) {
        User savedUser = userService.create(user);

        return Response.ok(new AuthResponse(savedUser, issueToken(savedUser.getEmail()))).build();
    }

    private String issueToken(String email) {
        Algorithm algorithm = Algorithm.HMAC256("secret");
        return JWT.create().withIssuer(email).sign(algorithm);
    }
}
