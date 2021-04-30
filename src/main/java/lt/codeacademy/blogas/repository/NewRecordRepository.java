package lt.codeacademy.blogas.repository;

import lt.codeacademy.blogas.model.BlogRecord;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

//Čia viskas ko riekia, implimentinti JPArepositorija kuri atneša visas CRUD operacijas
public interface NewRecordRepository extends JpaRepository<BlogRecord, UUID> {
        List<BlogRecord> findByUsername(String name);
}
