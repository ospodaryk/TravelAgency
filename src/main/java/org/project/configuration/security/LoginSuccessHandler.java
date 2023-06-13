package org.project.configuration.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@Component
public class LoginSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {
        Object principal = authentication.getPrincipal();

        if (!(principal instanceof Security)) {
            throw new IllegalArgumentException("Principal must be an instance of Security!");
        }

        Security userDetails = (Security) principal;

        String redirectURL = request.getContextPath();

        if (userDetails.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("STAFF"))) {
            redirectURL += "/user/";
        } else {
            redirectURL += "/hotel/";
        }
        getRedirectStrategy().sendRedirect(request, response, redirectURL);
    }

}
