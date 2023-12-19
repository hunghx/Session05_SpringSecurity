package ra.springsecurity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ra.springsecurity.model.entity.Role;
import ra.springsecurity.model.entity.RoleName;

import java.util.Optional;

@Repository
public interface IRoleRepository extends JpaRepository<Role,Long> {
    Optional<Role> findByRoleName(RoleName roleName);
}
