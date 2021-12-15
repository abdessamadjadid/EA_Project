package edu.miu.cs.cs544.EAProject.service;

import edu.miu.cs.cs544.EAProject.dto.UserDetailsDto;

public interface AccountRegistrationService {

    /**
     * Validates and adds student role to an already existing user account
     */
    UserDetailsDto registerStudent(int userId, String studentId, String name, String email, Integer mailingAddressId, Integer homeAddressId);

    /**
     * Validates and adds faculty role to an already existing user account
     */
    UserDetailsDto registerFaculty(int userId, String name, String email, String title);

    /**
     * Validates and adds admin role to an already existing user account
     */
    UserDetailsDto registerAdmin(int userId);

    /**
     * Finds user by its id
     */
    UserDetailsDto findUserById(int userId);
}
