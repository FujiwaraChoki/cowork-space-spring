package ch.zli.m223.controller.user;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ch.zli.m223.controller.user.dto.RoleDto;
import ch.zli.m223.controller.user.dto.UserDto;
import ch.zli.m223.controller.user.dto.UserUpdateDto;
import ch.zli.m223.service.user.UserService;
import lombok.RequiredArgsConstructor;

/**
 * Acess point for administrators to the user data
 */
@RestController
@RequestMapping("/api/v1/admin/users")
@RequiredArgsConstructor
public class UserAdminController {
    
    private final UserService userService;

    /**
     * Get list of all existing users
     * 
     * @return al list of users & HttpStatus.OK
     */
    @GetMapping()
    List<UserDto> getUserList() {
        return userService.getUserList().stream()
        .map( user -> new UserDto(user))
        .collect(Collectors.toList());
    }

    /**
     * Get a user by its Id
     * 
     * @param id the users id
     * @return (HttpStatus.OK & the user as UserDto) or HttpStatus.BAD_REQUEST if user not found
     */
    @GetMapping("/{id}")
    UserDto getUser(@PathVariable("id") Long id) {
            return new UserDto(userService.getUser(id));
    }

    /**
     * Update a user given by its Id
     * 
     * @param id the users id
     * @param data everything you want to be changed in the user object
     * @return (HttpStatus.OK & the user as UserDto) or HttpStatus.CONFLICT or HttpStatus.BAD_REQUEST  if user not found
     */
    @PatchMapping("/{id}")
    UserDto updateUser(
        @PathVariable("id") Long id,
        @RequestBody UserUpdateDto data
    ) {
        return new UserDto(userService.updateUser(
            id,
            data.firstName, data.lastName, 
            data.email, data.password, data.role
        ));
    }

    /**
     * Delete a user by Id from the server
     * 
     * @param id the users id
     * @return HttpStatus.OK
     */
    @DeleteMapping("/{id}")
    void deleteUser(@PathVariable("id") Long id) {
        userService.deleteUser(id);
    }

    /**
     * Change users role by id
     * @param id the users id
     * @param role any role you wish, no chech if its a valid role inside the application
     * @return (HttpStatus.OK & the user as UserDto) or HttpStatus.BAD_REQUEST if user not found
     */
    @PutMapping("/{id}/role")
    UserDto setUserRole(@PathVariable("id") Long id, @RequestBody RoleDto role) {
        return new UserDto(userService.setUserRole(id, role.role));
    }
}
