package swimple.controllers;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import swimple.annotation.PropertiesFromFile;
import swimple.filters.Authenticated;
import swimple.filters.AuthenticatedUser;
import swimple.models.AuthResponse;
import swimple.models.User;
import swimple.services.UserService;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import java.util.Properties;

@Path("/users")
@Produces("application/json")
public class UsersController {

    @Inject
    private UserService userService;

    @Inject
    @AuthenticatedUser
    User authenticatedUser;

    @Inject
    @PropertiesFromFile
    Properties props;

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
        Algorithm algorithm = Algorithm.HMAC256(props.getProperty("secret_key"));
        return JWT.create().withIssuer(email).sign(algorithm);
    }
}
