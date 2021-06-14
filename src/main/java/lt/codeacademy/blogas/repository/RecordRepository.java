package lt.codeacademy.blogas.repository;

import lt.codeacademy.blogas.model.BlogRecord;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface RecordRepository extends JpaRepository<BlogRecord, UUID> {
        List<BlogRecord> findByUsername(String name);
}
