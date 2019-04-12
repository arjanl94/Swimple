package swimple.controllers;

import swimple.filters.Authenticated;
import swimple.services.GroupService;

import javax.inject.Inject;
import javax.json.JsonObject;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;

@Path("/groups")
@Produces("application/json")
@Authenticated
public class GroupsController {

    @Inject
    private GroupService groupService;

    @GET
    public Response index() {
        return Response.ok(groupService.getAll()).build();
    }

    @POST
    @Consumes("application/json")
    public Response create(JsonObject object) {
        return Response.ok(groupService.create(object)).build();
    }
}
