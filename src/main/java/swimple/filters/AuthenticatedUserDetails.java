package swimple.filters;

import swimple.models.User;

import java.security.Principal;

public class AuthenticatedUserDetails implements Principal {

    private final User user;
    private final String[] roles;

    public AuthenticatedUserDetails(User user, String[] roles) {
        this.user = user;
        this.roles = roles;
    }

    public User getUser() {
        return user;
    }

    public String[] getRoles() {
        return roles;
    }

    @Override
    public String getName() {
        return user.getEmail();
    }
}
