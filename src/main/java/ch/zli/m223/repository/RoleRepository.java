package ch.zli.m223.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import ch.zli.m223.model.Role;
import ch.zli.m223.model.impl.RoleImpl;

public interface RoleRepository extends JpaRepository<RoleImpl, Long>{
    
    public default Role addRole(String role) {
        return save(new RoleImpl(role));
    }

    Optional<Role> findRoleByRole(String role);
}
