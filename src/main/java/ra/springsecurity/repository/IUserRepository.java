package ra.springsecurity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ra.springsecurity.model.entity.User;

import java.util.Optional;

@Repository
public interface IUserRepository extends JpaRepository<User,String> {
//    @Query("select u from User u where u.username=?1 or u.email=?1")
//   User loadByUsername(String username);
    Optional<User> findByUsernameOrEmail(String username,String email);
}
