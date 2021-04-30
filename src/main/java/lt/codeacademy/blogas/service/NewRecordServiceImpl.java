package lt.codeacademy.blogas.service;

import lt.codeacademy.blogas.model.BlogRecord;
import lt.codeacademy.blogas.repository.NewRecordRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

/**
 * Servisas stumdo info arba atlieka matematiką.
 *
 * */

@Service
public class NewRecordServiceImpl implements NewRecordService {

    private final NewRecordRepository newRecordRepository;

    public NewRecordServiceImpl(NewRecordRepository newRecordRepository) {
        this.newRecordRepository = newRecordRepository;
    }

    @Override
    public void addRecord(BlogRecord blogRecord) {
        newRecordRepository.save(blogRecord);
    }

    @Override
    public BlogRecord getRecord(UUID id) {
        return newRecordRepository.getOne(id);
    }

    //    //    1. crudo operacija
//    @Override
//    public void addRecord(BlogRecord blogRecord) {
//
////        blogRecord.setId(UUID.randomUUID());
//        newRecordRepository.save(blogRecord);
//    }
//
//    @Override
//    public BlogRecord getRecord(UUID id) {
//        return newRecordRepository.findAll();
//    }
//
////    Gauname sąrašą visko ka turime savo laikinoje db.
//    @Override
//    public List<BlogRecord> getBlogRecords() {
//        return newRecordRepository.getBlogRecords();
//    }
}
