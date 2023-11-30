package com.da.business;

import com.da.model.UserModel;

/**
 * This interface defines the contract for the main business service,
 * specifically for operations related to user sessions.
 */
public interface MainBusinessServiceInterface {

    /**
     * Creates a user session by retrieving user data based on the provided username
     * and updating a UserModel instance with the current user's information.
     *
     * @param username - The username of the user for whom the session is being created.
     * @return UserModel - The UserModel instance with updated data based on the user from the database.
     */
    public UserModel createUserSession(String username);
}
