package lt.codeacademy.blogas.service;

import lt.codeacademy.blogas.model.BlogRecord;
import lt.codeacademy.blogas.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;
import java.util.UUID;

public interface UserService {

    void addUser(User user);

    User getUserById(UUID id);

    User getUserByName(String username);

    List<User> getUsers();

    void update(User user);

    void delete(UUID uuid);

    UserDetails loadUserByUsername(String username);

}
