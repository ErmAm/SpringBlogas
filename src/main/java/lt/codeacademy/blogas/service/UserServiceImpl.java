package lt.codeacademy.blogas.service;

import lt.codeacademy.blogas.model.BlogRecord;
import lt.codeacademy.blogas.model.User;
import lt.codeacademy.blogas.repository.UserRepository;
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
    public BlogRecord getUser(UUID id) {
        return null;
    }

    @Override
    public List<BlogRecord> getUsers() {
        return null;
    }

    @Override
    public void update(User user) {

    }

    @Override
    public void delete(UUID uuid) {

    }
}
