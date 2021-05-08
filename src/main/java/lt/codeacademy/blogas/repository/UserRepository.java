package lt.codeacademy.blogas.repository;

import lt.codeacademy.blogas.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {

//    Reikia naudoti query ir negaloti

    @Query("SELECT u FROM User u WHERE u.username= :userName")
    User findUserByName(@Param("userName") String userName);


//  05-07  Panašu kad paprasčiau bus pasinaudoti paprastais queriais, nei ieškoti kas padaryta su JPARepo.
}
