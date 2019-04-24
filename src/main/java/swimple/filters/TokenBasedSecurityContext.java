package swimple.filters;

import javax.ws.rs.core.SecurityContext;
import java.security.Principal;
import java.util.Arrays;

public class TokenBasedSecurityContext implements SecurityContext {

    private final AuthenticatedUserDetails authenticatedUserDetails;
    private final boolean secure;

    public TokenBasedSecurityContext(AuthenticatedUserDetails authenticatedUserDetails, boolean secure) {
        this.authenticatedUserDetails = authenticatedUserDetails;
        this.secure = secure;
    }

    @Override
    public Principal getUserPrincipal() {
        return authenticatedUserDetails;
    }

    @Override
    public boolean isUserInRole(String role) {
        return Arrays.asList(authenticatedUserDetails.getRoles()).contains(role);
    }

    @Override
    public boolean isSecure() {
        return secure;
    }

    @Override
    public String getAuthenticationScheme() {
        return "Bearer";
    }
}
