package ch.zli.m223.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import ch.zli.m223.model.AppUser;
import ch.zli.m223.model.Role;
import ch.zli.m223.model.impl.AppUserImpl;
import ch.zli.m223.model.impl.RoleImpl;

public interface UserRepository extends JpaRepository<AppUserImpl, Long> {

    public default AppUser insertUser(String firstName, String shureName, String email, String password, Role role) {
        return save(new AppUserImpl(firstName, shureName, email, password, (RoleImpl)role));
    }

    public Optional<AppUser> findByEmail(String email);

    public default AppUser update(
        AppUser user, 
        String firstName, String shureName, 
        String email, String password,
        Role role) {
            AppUserImpl userImpl = (AppUserImpl) user;
            if (firstName != null) userImpl.setFirstName(firstName);
            if (shureName != null) userImpl.setLastName(shureName);
            if (email != null) userImpl.setEmail(email);
            if (password != null) userImpl.setPassword(password);
            if (role != null) userImpl.setRole((RoleImpl)role);
            return save(userImpl);
        }
}
