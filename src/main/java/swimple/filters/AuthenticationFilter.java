package swimple.filters;

import com.auth0.jwt.JWT;
import com.auth0.jwt.exceptions.JWTDecodeException;
import swimple.models.User;
import swimple.services.JwtService;
import swimple.services.UserService;

import javax.annotation.Priority;
import javax.enterprise.context.Dependent;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.ext.Provider;
import java.io.IOException;
import java.nio.file.AccessDeniedException;

@Authenticated
@Provider
@Priority(Priorities.AUTHENTICATION)
public class AuthenticationFilter implements ContainerRequestFilter {

    private static final String REALM = "swimple";
    private static final String AUTHENTICATION_SCHEME = "Bearer";

    @Inject
    JwtService jwt;

    @Inject
    UserService userService;

    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {
        String authorizationHeader = requestContext.getHeaderString(HttpHeaders.AUTHORIZATION);

        if(!isTokenBasedAuthentication(authorizationHeader)) {
            throw new AccessDeniedException("Access denied");
        }

        String token = authorizationHeader.substring(AUTHENTICATION_SCHEME.length()).trim();

        try {
            String email = validateToken(token);
            User user = userService.findByEmail(email);
            AuthenticatedUserDetails userDetails = new AuthenticatedUserDetails(user, user.getRoles());

            boolean isSecure = requestContext.getSecurityContext().isSecure();
            SecurityContext securityContext = new TokenBasedSecurityContext(userDetails, isSecure);
            requestContext.setSecurityContext(securityContext);
//            userAuthenticatedEvent.fire(email);
        } catch (Exception e) {
            throw new AccessDeniedException("Your not allowed");
        }
    }

    private boolean isTokenBasedAuthentication(String authorizationHeader) {
        return authorizationHeader != null && authorizationHeader.toLowerCase()
                .startsWith(AUTHENTICATION_SCHEME.toLowerCase() + " ");
    }

    private void abortWithUnauthorized(ContainerRequestContext requestContext) {
        requestContext.abortWith(
                Response.status(Response.Status.UNAUTHORIZED)
                        .header(HttpHeaders.WWW_AUTHENTICATE,
                                AUTHENTICATION_SCHEME + " realm=\"" + REALM + "\"")
                        .build());
    }

    private String validateToken(String token) throws Exception {
        String email =  jwt.validateToken(token);

        if (email == null) {
            throw new Exception("Couldn't validate JWT");
        }

        return email;
    }
}
