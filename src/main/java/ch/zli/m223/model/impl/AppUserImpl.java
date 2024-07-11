package ch.zli.m223.model.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import jakarta.persistence.*;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import ch.zli.m223.model.AppUser;
import ch.zli.m223.model.Role;

@Entity(name="AppUser")
public class AppUserImpl implements AppUser {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String passwordHash;

    @Setter
    private String firstName;

    private String lastName;

    @Column(
        nullable = false, 
        unique = true)
    private String email;

    @ManyToOne
    @JoinColumn(name = "role_id", nullable = false)
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
    public String getEmail() {
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
    public String getUsername() { return getEmail(); }

    @Override
    public boolean isAccountNonExpired()  { return true; }

    @Override
    public boolean isAccountNonLocked() { return true; }

    @Override
    public boolean isCredentialsNonExpired() { return true; }

    @Override
    public boolean isEnabled() { return true; }
}
