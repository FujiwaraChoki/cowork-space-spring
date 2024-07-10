package ch.zli.m223.controller.user;

import java.security.Principal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ch.zli.m223.controller.user.dto.UserDto;
import ch.zli.m223.controller.user.dto.UserUpdateDto;
import ch.zli.m223.service.user.UserService;
import lombok.RequiredArgsConstructor;

/**
 * Access point for members to their own data
 */
@RestController
@RequestMapping("/api/v1/member/users")
@RequiredArgsConstructor
public class UserMemberController {
    
    private final UserService userService;

    /**
     * Access my user info
     * 
     * @param principal injected
     * @return HttpStatus.OK & myself as UserDto
     */
    @GetMapping("")
    UserDto getUser(Principal principal) {
        return new UserDto(userService.getUserByName(principal.getName()));
    }

    /**
     * Update myself
     * 
     * @param principal injected
     * @param data everything you want to be changed in your user object
     * @return (HttpStatus.OK & myself as UserDto) or HttpStatus.CONFLICT
     */
    @PatchMapping("")
    UserDto updateUser(
        Principal principal,
        @RequestBody UserUpdateDto data
    ) {
        return new UserDto(userService.updateUserByName(
            principal.getName(),
            data.firstName, data.lastName, 
            data.email, data.password, data.role
        ));
    }

    /**
     * Delete myself from the server
     * 
     * @param principal injected
     * @return HttpStatus.OK
     */
    @DeleteMapping("")
    void deleteUser(Principal principal) {
        userService.deleteUserByName(principal.getName());
    }
}
