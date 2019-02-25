package filters;

import models.User;
import services.UserService;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.event.Observes;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;

@RequestScoped
public class AuthenticatedUserProducer {

    @Produces
    @RequestScoped
    @AuthenticatedUser
    private User authenticatedUser;

    @Inject
    UserService userService;


    public void handleAuthenticationEvent(@Observes @AuthenticatedUser String email) {
        this.authenticatedUser = userService.findByEmail(email);
    }

}
