package ch.zli.m223.service.user;

import java.util.List;

import ch.zli.m223.model.AppUser;

public interface UserService {

    /**
     * @return a possibly empty list of users
     */
    List<AppUser> getUserList();

    /**
     * Get a single user by id
     * @param id the users id (required)
     * @return the user
     * @throws InvalidIdException, UserNotFoundException
     */
    AppUser getUser(Long id);

    /**
     * Add a new user
     * @param firstName first name
     * @param shureName last name
     * @param email an email (required)
     * @param password a plaintext password (required)
     * @return the newly made user
     * @throws InvalidEmailOrPasswordException, UserAllredyExistsException
     */
    AppUser addUser(
        String firstName, String shureName, 
        String email, String password
    );

    /**
     * Delete a user
     * @param id the users id (required)
     * @throws InvalidIdException
     */
    void deleteUser(Long id);

    /**
     * Update a user
     * 
     * @param id the users id (required)
     * @param firstName first name (optional)
     * @param shureName last name (optional)
     * @param email an email (optional)
     * @param password a new password (optional)
     * @param role a new role (optional)
     * @return the changed user
     * @throws InvalidIdException, UserNotFoundException, UserAllredyExistsException
     */
    AppUser updateUser(
        Long id,
        String firstName, String shureName,
        String email, String password,
        String role
    );

    /**
     * Set the users role
     * @param id the users id (required)
     * @param role the new role
     * @return the changed user
     * @throws InvalidIdException, UserNotFoundException
     */
    AppUser setUserRole(Long id, String role);

    
    /**
     * @see UserService#getUser(Long id)
     * 
     * @param userName the users email
     * @return a user
     */
    AppUser getUserByName(String userName);

    /**
     * @see UserService#updateUser(
        Long id,
        String firstName, String shureName,
        String email, String password,
        String role
    )
     * 
     * @param userName the users email
     * @return the modified user
     */
    AppUser updateUserByName(
        String userName, 
        String firstName, String lastName, 
        String email, String password,
        String role
    );

    /**
     * @see UserService#deleteUser(Long id)
     * 
     * @param userName the users email
     */
    void deleteUserByName(String userName);
    
}
