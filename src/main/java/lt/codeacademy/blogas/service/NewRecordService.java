package lt.codeacademy.blogas.service;

import lt.codeacademy.blogas.model.BlogRecord;

import java.util.List;
import java.util.UUID;

public interface NewRecordService {

    void addRecord(BlogRecord blogRecord);

    BlogRecord getRecord(UUID id);

    List<BlogRecord> getBlogRecords();


}
