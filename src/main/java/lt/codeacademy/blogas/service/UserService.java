package lt.codeacademy.blogas.service;

import lt.codeacademy.blogas.model.BlogRecord;
import lt.codeacademy.blogas.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

public interface UserService {

    void addUser(User user);

    BlogRecord getUser(UUID id);

    List<BlogRecord> getUsers();

    void update(User user);

    void delete(UUID uuid);

//    //    Atsirado poreikis rasti blogą pagal pvz useri
//    BlogRecord getByUsername(String name);

//    Page<BlogRecord> getBlogRecordsPaginated(Pageable pageable);

}
