package edu.miu.cs.cs544.EAProject.controller;

import edu.miu.cs.cs544.EAProject.dto.TokenDto;
import edu.miu.cs.cs544.EAProject.dto.UserDto;
import edu.miu.cs.cs544.EAProject.service.SecurityService;
import edu.miu.cs.cs544.EAProject.utils.Pair;
import edu.miu.cs.cs544.EAProject.utils.SecurityUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.security.config.annotation.web.configurers.HttpBasicConfigurer;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RequiredArgsConstructor
@RestController
public class SecurityController {

    private final SecurityService securityService;

    /**
     * This endpoint is already protected by http basic auth via {@link HttpBasicConfigurer}
     * @param auth Authorization header
     * @return JWT token
     */
    @PostMapping("/login")
    public TokenDto login(@RequestHeader(name = HttpHeaders.AUTHORIZATION) String auth) {

        Pair<String, String> usernamePassword = Objects.requireNonNull(SecurityUtils.extractUsernamePassword(auth));
        return securityService.issueToken(usernamePassword.getLeft(), usernamePassword.getRight());
    }

    @PostMapping("/signup")
    public void signup(@RequestBody UserDto userDto) {
        securityService.signup(userDto);
    }
}
