package edu.miu.cs.cs544.EAProject.service;

import edu.miu.cs.cs544.EAProject.domain.User;
import edu.miu.cs.cs544.EAProject.dto.TokenDto;
import edu.miu.cs.cs544.EAProject.dto.UserDto;
import edu.miu.cs.cs544.EAProject.i18n.DefaultMessageSource;
import edu.miu.cs.cs544.EAProject.repository.UserRepository;
import edu.miu.cs.cs544.EAProject.utils.SecurityUtils;
import edu.miu.cs.cs544.EAProject.security.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Objects;

@RequiredArgsConstructor
@Service
public class SecurityServiceImpl implements SecurityService {

    private final UserRepository userRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final PasswordEncoder passwordEncoder;
    private final MessageSourceAccessor messages = DefaultMessageSource.getAccessor();

    @Override
    public TokenDto issueToken(String username, String password) {

        User user = SecurityUtils.getCurrentUser();
        String token =  jwtTokenProvider.createToken(username, Objects.requireNonNull(user).getRoles());
        return new TokenDto(token, jwtTokenProvider.getTokenType(), jwtTokenProvider.getExpirySeconds());
    }

    @Override
    public void signup(UserDto userDto) {

        if (userRepository.findByUsername(userDto.getUsername()).isPresent()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, messages.getMessage("error.username.taken"));
        }

        User user = new User(userDto.getUsername(), passwordEncoder.encode(userDto.getPassword()), null, true);
        userRepository.save(user);
    }
}
