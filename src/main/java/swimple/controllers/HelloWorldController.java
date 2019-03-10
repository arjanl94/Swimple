package swimple.controllers;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Path("hello")
public class HelloWorldController {

    @GET
    public String index() {
        return "Hello World!";
    }
}
