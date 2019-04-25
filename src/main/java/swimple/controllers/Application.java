package swimple.controllers;

import org.eclipse.microprofile.auth.LoginConfig;
import org.glassfish.jersey.jackson.JacksonFeature;

import javax.annotation.security.DeclareRoles;
import javax.ws.rs.ApplicationPath;
import java.util.HashSet;
import java.util.Set;

@ApplicationPath("/api")
@LoginConfig(authMethod = "MP-JWT", realmName = "jwt-jaspi")
@DeclareRoles({"user", "admin", "coach"})
public class Application extends javax.ws.rs.core.Application {
//
//    public Application() {
//        packages("swimple");
//
//        register(JacksonFeature.class);
//        register(RolesAllowedDynamicFeature.class);
//        register(AuthenticationFilter.class);
//    }


    @Override
    public Set<Class<?>> getClasses() {
        final Set<Class<?>> classes = new HashSet<>();

        classes.add(AuthenticationsController.class);
        classes.add(CommentsController.class);
        classes.add(GroupsController.class);
        classes.add(TrainingsController.class);
        classes.add(UsersController.class);

        classes.add(JacksonFeature.class);

        return classes;
    }
}
