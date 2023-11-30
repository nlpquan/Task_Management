package com.da.business;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.da.data.UserDataAccess;
import com.da.data.entity.UserEntity;
import com.da.model.UserModel;

@Service
public class MainBusinessService implements MainBusinessServiceInterface {

    @Autowired
    UserDataAccess userDataService;

    @Autowired
    UserModel userModel;

    @Override
    /**
     * This method updates user data that comes from the database and updates the instance of a UserModel with the current user.
     * It retrieves user information from the database based on the provided username,
     * updates the UserModel instance with the retrieved data, and returns the updated UserModel.
     *
     * @param username - The username of the user for whom the session is being created.
     * @return UserModel - The UserModel instance with updated data based on the user from the database.
     */
    public UserModel createUserSession(String username) {
        // Find the user entity in the database based on the provided username
        UserEntity userEntity = userDataService.findByUsername(username);

        // Update the UserModel instance with data from the retrieved UserEntity
        userModel.setId(userEntity.getId());
        userModel.getCredentials().setUsername(userEntity.getUsername());
        userModel.getCredentials().setPassword(userEntity.getPassword());
        userModel.setFirstName(userEntity.getFirstName());
        userModel.setLastName(userEntity.getLastName());

        // Return the updated UserModel
        return userModel;
    }
}
