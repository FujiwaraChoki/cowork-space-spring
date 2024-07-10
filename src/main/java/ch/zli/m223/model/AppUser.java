package ch.zli.m223.model;

import org.springframework.security.core.userdetails.UserDetails;

public interface AppUser extends UserDetails {
    Long getId();
    String getUserName(); //his email
    String getFirstName();
    String getLastName();
    String getPassword();
    Role getRole();
}
