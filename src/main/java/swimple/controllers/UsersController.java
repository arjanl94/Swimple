package swimple.controllers;

import swimple.models.AuthResponse;
import swimple.models.User;
import swimple.services.JwtService;
import swimple.services.UserService;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

@Path("/users")
@Produces("application/json")
public class UsersController {

    @Inject
    private UserService userService;

    @Inject
    JwtService jwt;

    @Context
    SecurityContext context;

    @GET
    @RolesAllowed({"admin", "coach"})
    public Response index() {
       return Response.ok(userService.getAll()).build();
    }

    @GET
    @Path("/me")
    public Response show() {
        return Response.ok(context.getUserPrincipal().getName()).build();
    }

    @POST
    @PermitAll
    public Response create(User user) {
        User savedUser = userService.create(user);

        return Response.ok(new AuthResponse(savedUser, jwt.issueToken(savedUser))).build();
    }
}
