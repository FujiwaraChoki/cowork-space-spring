package ch.zli.m223.service.user.impl;

import java.util.List;
import java.util.ArrayList;

import org.springframework.stereotype.Service;

import ch.zli.m223.model.AppUser;
import ch.zli.m223.model.Role;
import ch.zli.m223.service.user.UserService;
import ch.zli.m223.service.user.exception.*;
import lombok.RequiredArgsConstructor;
import ch.zli.m223.repository.RoleRepository;
import ch.zli.m223.repository.UserRepository;
import ch.zli.m223.roles.UserRoles;

import org.springframework.security.crypto.password.PasswordEncoder;


@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    
    @Override
    public List<AppUser> getUserList() {
        return new ArrayList<>(userRepository.findAll());
    }

    @Override
    public AppUser getUser(Long id) {
        if (id == null) {
            throw new InvalidIdException();
        }
        return userRepository.findById(id)
            .orElseThrow(() -> new UserNotFoundException());
    }

    @Override
    public AppUser addUser(
        String firstName, String shureName, 
        String email, String password
    ) {
        if (
            email == null || email.isBlank() || 
            password == null || password.isBlank()
        ) {
            throw new InvalidEmailOrPasswordException();
        }

        if (userRepository.findByEmail(email).isPresent()) {
            throw new UserAllredyExistsException();
        }

        // Special rule:
        // The first user is admin
        String roleName = UserRoles.member;
        if (getUserList().isEmpty()) {
            roleName = UserRoles.admin;
        }
        
        Role role = getRole(roleName);
        password = passwordEncoder.encode(password);
        return userRepository.insertUser(firstName, shureName, email, password, role);
    }

    private Role getRole(String role) {
        return roleRepository.findRoleByRole(role.trim())
            .orElseThrow(RoleNotFoundException::new);
    }

    @Override
    public void deleteUser(Long id) {
        if (id == null) {
            throw new InvalidIdException();
        }
        userRepository.deleteById(id);
    }

    @Override
    public AppUser updateUser(Long id, 
        String firstName, String shureName, 
        String email, String password, 
        String role
    ) {
        var user = getUser(id);

        if (email != null) {
            email = email.trim();
            var res = userRepository.findByEmail(email);
            if (res.isPresent() &&
                res.get().getId() != user.getId())
            {
                throw new UserAllredyExistsException();
            }
        }

        if (password != null) {
            password = passwordEncoder.encode(password);
        }
        
        return userRepository.update(
            user, 
            firstName, shureName, email, password, 
            role != null ? getRole(role) : null
        );
    }

    @Override
    public AppUser setUserRole(Long id, String role) {
        var user = getUser(id);
        return userRepository.update(
            user, 
            null, null, null, null, 
            getRole(role)
        );
    }

    @Override
    public AppUser getUserByName(String userName) {
        return getUser(getUserIdByName(userName));
    }

    @Override
    public AppUser updateUserByName(String userName, String firstName, String lastName, String email, String password,
            String role) {
        return updateUser(getUserIdByName(userName), firstName, lastName, email, password, role);
    }
 
    @Override
    public void deleteUserByName(String userName) {
        deleteUser(getUserIdByName(userName));
    }

    private Long getUserIdByName(String userName) {
        AppUser user = userRepository.findByEmail(userName)
            .orElseThrow(() -> new UserNotFoundException());
        return user.getId();
    }

}
