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
 */

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

    @Override
    public List<BlogRecord> getRecords() {

//        TODO Pasižiūrėti kaip padaryta kitose vietose.
        return null;
    }

    @Override
    public void update(BlogRecord blogRecord) {
        newRecordRepository.save(blogRecord);
    }

    @Override
    public void delete(UUID uuid) {
        newRecordRepository.deleteById(uuid);
    }

//    TODO biški nepagaunu šitos implimentacijos. Iš kur jis repo guana info?
    @Override
    public BlogRecord getByUsername(String name) {
        return newRecordRepository.findByUsername(name).get(0);
    }
}
