package edu.miu.cs.cs544.EAProject.security;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "security.jwt")
public class SecurityProperties {

    private String secretKey;
    private long timeoutMillis;
}
