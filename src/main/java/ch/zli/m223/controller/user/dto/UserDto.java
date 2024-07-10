package ch.zli.m223.controller.user.dto;

import ch.zli.m223.model.AppUser;
import ch.zli.m223.model.Role;

public class UserDto {
    public Long id;
    public String firstName;
    public String lastName;
    public String email;
    public Role role;

    public UserDto(AppUser user) {
        id = user.getId();
        firstName = user.getFirstName();
        lastName = user.getLastName();
        email = user.getUserName();
        role = user.getRole();
    }
}
