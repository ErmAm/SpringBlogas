package lt.codeacademy.blogas.service;

import lt.codeacademy.blogas.model.BlogRecord;
import lt.codeacademy.blogas.repository.NewRecordRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

/**
 * Servisas stumdo info arba atlieka matematiką.
 *
 * */

@Service
public class NewRecordServiceImpl implements NewRecordService{

    private final NewRecordRepositoryImpl repository;

    @Autowired
    public NewRecordServiceImpl(NewRecordRepositoryImpl repository) {
        this.repository = repository;
    }

//    1. crudo operacija
    @Override
    public void addRecord(BlogRecord blogRecord) {

        blogRecord.setId(UUID.randomUUID());
        repository.save(blogRecord);
    }

    @Override
    public BlogRecord getRecord(UUID id) {
        return repository.getRecord(id);
    }

//    Gauname sąrašą visko ka turime savo laikinoje db.
    @Override
    public List<BlogRecord> getBlogRecords() {
        return repository.getBlogRecords();
    }
}
