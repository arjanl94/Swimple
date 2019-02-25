package controllers;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import models.AuthResponse;
import models.Credentials;
import models.User;
import services.UserService;

import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

@Path("/authentication")
@Produces("application/json")
@Consumes("application/json")
public class AuthenticationsController {

    @Inject
    UserService userService;

    @POST
    public Response login(Credentials credentials) {
        try {
            User user = authenticate(credentials);
            String token = issueToken(credentials.getEmail());

            return Response.ok(new AuthResponse(user, token)).build();
        } catch (Exception e) {
            JsonObject errorObject = Json.createObjectBuilder().add("message", e.getLocalizedMessage()).build();
            return Response.status(Response.Status.UNAUTHORIZED).entity(errorObject).build();
        }
    }

    private User authenticate(Credentials credentials) throws Exception {
        User user = userService.findByEmail(credentials.getEmail());

        if (user == null) {
            throw new Exception("User does not exist");
        }

        if (!UserService.encodeSHA256(credentials.getPassword()).equals(user.getPassword())) {
            throw new Exception("Password not correct");
        }

        return user;
    }

    private String issueToken(String email) {
        Algorithm algorithm = Algorithm.HMAC256("secret");
        return JWT.create().withIssuer(email).sign(algorithm);
    }
}
