package controllers;

import services.GroupService;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

@Path("/groups")
@Produces("application/json")
public class GroupsController {

    @Inject
    private GroupService groupService;

    @GET
    public Response index() {
        return Response.ok(groupService.getAll()).build();
    }
}
