package ch.zli.m223.model.impl;

import ch.zli.m223.model.Role;
import jakarta.persistence.*;

@Entity(name="Role")
public class RoleImpl implements Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String role;

    public RoleImpl(String role) {
        this.role = role;
    }

    /** For JPA use only */
    protected RoleImpl() {}

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public String getRole() {
        return role;
    }
}
