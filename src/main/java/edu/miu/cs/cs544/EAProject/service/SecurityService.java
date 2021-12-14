package edu.miu.cs.cs544.EAProject.service;

import edu.miu.cs.cs544.EAProject.dto.TokenDto;
import edu.miu.cs.cs544.EAProject.dto.UserDto;

public interface SecurityService {

    /**
     * Assuming this is called from secured context, it issues JWT token
     */
    TokenDto issueToken(String username, String password);

    /**
     * Validates and creates a new user account
     */
    void signup(UserDto userDto);
}
