package lt.codeacademy.blogas.repository;

import lt.codeacademy.blogas.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {

    List<User> findByName(String name);

//  05-07  Panašu kad paprasčiau bus pasinaudoti paprastais queriais, nei ieškoti kas padaryta su JPARepo.
}
