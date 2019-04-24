package swimple.controllers;

import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.filter.RolesAllowedDynamicFeature;
import swimple.filters.AuthenticationFilter;

import javax.ws.rs.ApplicationPath;

@ApplicationPath("/api")
public class Application extends javax.ws.rs.core.Application {
//
//    public Application() {
//        packages("swimple");
//
//        register(JacksonFeature.class);
//        register(RolesAllowedDynamicFeature.class);
//        register(AuthenticationFilter.class);
//    }
}
