package lt.codeacademy.blogas.repository;

import lt.codeacademy.blogas.model.BlogRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;


/**
 * Repo skirta info saugojimui
 */

@Repository
public class NewRecordRepositoryImpl {
    //   Kuriam laikiną laikmeną
    private final Map<UUID, BlogRecord> blogRecords;

    public NewRecordRepositoryImpl() {
        blogRecords = new HashMap<>();
    }

    public void save(BlogRecord blogRecord){
        blogRecords.put(blogRecord.getId(),blogRecord);
    }

    public BlogRecord getBlogRecord(UUID id){
        return blogRecords.get(id);
    }


    public BlogRecord getRecord(UUID id) {
        return blogRecords.get(id);
    }

//  Kad žinoti ka pakeisti reikia guati visą lista savo dalyku
    public List<BlogRecord> getBlogRecords(){

        return null;
    }



}
