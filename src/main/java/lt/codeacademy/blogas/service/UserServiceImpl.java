package lt.codeacademy.blogas.service;

import lt.codeacademy.blogas.model.BlogRecord;
import lt.codeacademy.blogas.model.User;
import lt.codeacademy.blogas.repository.UserRepository;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }



    @Override
    public void addUser(User newUser) {
        userRepository.save(newUser);
    }

    @Override
    public User getUser(UUID id) {
        return userRepository.findById(id)
                .orElseThrow(()->new UsernameNotFoundException(id.toString()));
    }

    @Override
    public User getUserByName(String username) {
        return userRepository.findUserByName(username);
    }

    @Override
    public List<User> getUsers() {
        return null;
    }

    @Override
    public void update(User user) {
       userRepository.save(user);
    }

    @Override
    public void delete(UUID uuid) {

    }
}
