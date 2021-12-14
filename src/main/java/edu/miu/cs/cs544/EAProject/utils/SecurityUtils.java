package edu.miu.cs.cs544.EAProject.utils;

import edu.miu.cs.cs544.EAProject.domain.User;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

@Slf4j
public final class SecurityUtils {

    public static final String ROLE_STUDENT = "student";
    public static final String ROLE_FACULTY = "faculty";
    public static final String ROLE_ADMIN = "admin";

    public static User getCurrentUser() {

        try {

            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            Object principal = authentication.getPrincipal();

            return principal instanceof User ? (User) principal : null;
        } catch (Exception ex) {
            log.warn("Failed to find current user", ex);
            return null;
        }
    }

    public static Pair<String, String> extractUsernamePassword(String authentication) {
        try {
            String pair=new String(Base64.decodeBase64(authentication.substring(6)));
            String username=pair.split(":")[0];
            String password=pair.split(":")[1];
            return new Pair<>(username, password);
        } catch (Exception ex) {
            log.warn("Failed to extract username & password");
        }
        return null;
    }
}
