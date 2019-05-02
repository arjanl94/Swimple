package swimple.controllers;

import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.filter.RolesAllowedDynamicFeature;
import swimple.filters.Authenticated;
import swimple.filters.AuthenticationFilter;

import javax.ws.rs.ApplicationPath;
import java.util.HashSet;
import java.util.Set;

@ApplicationPath("/api")
public class Application extends javax.ws.rs.core.Application {

    @Override
    public Set<Class<?>> getClasses() {
        final Set<Class<?>> classes = new HashSet<>();

        classes.add(AuthenticationsController.class);
        classes.add(CommentsController.class);
        classes.add(GroupsController.class);
        classes.add(TrainingsController.class);
        classes.add(UsersController.class);
        classes.add(AuthenticationFilter.class);
        classes.add(Authenticated.class);

        classes.add(JacksonFeature.class);

        return classes;
    }
//
//    public Application() {
//        packages("swimple");
//
//        register(JacksonFeature.class);
//        register(RolesAllowedDynamicFeature.class);
//        register(AuthenticationFilter.class);
//    }
}
