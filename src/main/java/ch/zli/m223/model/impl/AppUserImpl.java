package ch.zli.m223.model.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import ch.zli.m223.model.AppUser;
import ch.zli.m223.model.Role;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity(name="AppUser")
public class AppUserImpl implements AppUser {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String passwordHash;

    private String firstName;

    private String lastName;

    @Column(
        nullable = false, 
        unique = true)
    private String email;

    @ManyToOne
    private RoleImpl role;

    public AppUserImpl(
        String firstName, String shureName,
        String email, String encriptedPassword,
        RoleImpl role
    ) {
        this.firstName  = firstName;
        this.lastName  = shureName;
        this.email  = email;
        setPassword(encriptedPassword);
        this.role = role;
    }

    /** For JPA use only */
    protected AppUserImpl() {}

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public String getUserName() {
        return email;
    }

    @Override
    public String getPassword() {
        return passwordHash;
    }
    
    @Override
    public String getFirstName() {
        return firstName;
    }

    @Override
    public String getLastName() {
        return lastName;
    }
    
    @Override
    public Role getRole() {
        return role;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String shureName) {
        this.lastName = shureName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String encriptedPassword) {
        passwordHash = encriptedPassword;
    }

    public void setRole(RoleImpl role) {
        this.role = role;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> list = new ArrayList<>();
        list.add(new SimpleGrantedAuthority(role.getRole()));
        return list;
    }

    @Override
    public String getUsername() { return getUserName(); }

    @Override
    public boolean isAccountNonExpired()  { return true; }

    @Override
    public boolean isAccountNonLocked() { return true; }

    @Override
    public boolean isCredentialsNonExpired() { return true; }

    @Override
    public boolean isEnabled() { return true; }
}
