package lt.codeacademy.blogas.service;

import lt.codeacademy.blogas.model.BlogRecord;

import java.util.List;
import java.util.UUID;

/**
 *  Čia visu reikalingų servisų proverstinis generatorius.
 * */
public interface NewRecordService {

    void addRecord(BlogRecord blogRecord);

    BlogRecord getRecord(UUID id);



}
