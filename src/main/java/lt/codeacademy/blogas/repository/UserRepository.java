package lt.codeacademy.blogas.repository;

import lt.codeacademy.blogas.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {

    @Query("SELECT u FROM User u WHERE u.username= :userName")
    User findUserByName(@Param("userName") String userName);

    @Query("SELECT u FROM User u JOIN FETCH u.roles WHERE u.username = :name")
    Optional<User> findByNameWithRoles(@Param("name") String name);

}
