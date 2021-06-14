package lt.codeacademy.blogas.service;

import lt.codeacademy.blogas.model.BlogRecord;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

/**
 *  Čia visu reikalingų servisų proverstinis generatorius.
 * */
public interface RecordService {

    void addRecord(BlogRecord blogRecord);

    BlogRecord getRecord(UUID id);

    List<BlogRecord> getRecords();

    void update(BlogRecord blogRecord);

    void delete(UUID uuid);

    BlogRecord getByUsername(String name);

    Page<BlogRecord> getBlogRecordsPaginated(Pageable pageable);



}
