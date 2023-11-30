package com.da.business;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.da.data.UserDataAccess;
import com.da.data.entity.UserEntity;
import com.da.model.UserModel;

@Service
public class UserBusinessService implements UserBusinessServiceInterface {

    @Autowired
    UserDataAccess userDataService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    UserModel userModel;

    @Override
    /**
     * Retrieves a list of all users and transforms them into UserModels.
     *
     * @return List<UserModel> - List of UserModel instances representing users.
     */
    public List<UserModel> getUsers() {
        List<UserEntity> userEntities = userDataService.findAll();

        List<UserModel> userDomain = new ArrayList<UserModel>();
        for (UserEntity entity : userEntities) {
            userDomain.add(new UserModel(
                    entity.getId(),
                    entity.getUsername(),
                    entity.getPassword(),
                    entity.getFirstName(),
                    entity.getLastName()));
        }
        // Return list of Domain Users
        return userDomain;
    }

    @Override
    /**
     * Creates a new user in the system by encoding the password and storing user data.
     *
     * @param userModel - The UserModel instance representing the user to be created.
     * @return int - Status code indicating the success of the operation.
     */
    public int createUser(UserModel userModel) {

        // Encode the user's password before storing it in the database
        String encodedPassword = passwordEncoder.encode(userModel.getCredentials().getPassword());

        // Create a UserEntity with the encoded password and other user details
        UserEntity userEntity = new UserEntity(userModel.getId(),
                userModel.getCredentials().getUsername(),
                encodedPassword,
                userModel.getFirstName(),
                userModel.getLastName());

        // Call the data service to create the user
        userDataService.create(userEntity);

        // Return success status code
        return 0;
    }

    @Override
    /**
     * Verifies if a given username already exists in the system.
     *
     * @param userModel - The UserModel instance representing the user to be verified.
     * @return int - Status code indicating the result of the verification (0 if the username is available, 1 otherwise).
     */
    public int verifyUsername(UserModel userModel) {
        // Check if the provided username already exists in the system
        if (userDataService.findByUsername(userModel.getCredentials().getUsername()) == null) {
            // Username is available
            return 0;
        }
        // Username is already taken
        return 1;
    }

}
