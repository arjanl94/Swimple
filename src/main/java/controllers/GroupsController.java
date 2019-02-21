package controllers;

import com.sun.xml.internal.xsom.impl.scd.Iterators;
import filters.Authenticated;
import models.Group;
import models.User;
import services.GroupService;

import javax.inject.Inject;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonValue;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.Iterator;
import java.util.List;

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
//        return Response.noContent().build();
        return Response.ok(groupService.create(object)).build();
    }
}
