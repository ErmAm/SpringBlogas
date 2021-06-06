package lt.codeacademy.blogas.service;

import lt.codeacademy.blogas.model.BlogRecord;
import lt.codeacademy.blogas.model.exception.BlogRecordNotFoundException;
import lt.codeacademy.blogas.repository.RecordRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

/**
 * Servisas stumdo info arba atlieka matematiką.
 */

@Service
public class RecordServiceImpl implements RecordService {

    public static final String RECORD_NOT_EXISTS = "Tokio blogo įrašo nėra";
    private final RecordRepository recordRepository;

    public RecordServiceImpl(RecordRepository newRecordRepository) {
        this.recordRepository = newRecordRepository;
    }

    @Override
    public void addRecord(BlogRecord blogRecord) {
        recordRepository.save(blogRecord);
    }

    @Override
    public BlogRecord getRecord(UUID id) {

//   05-02     Čia yra bėdų gali grazina optionalą todėl riekia biški pakeisitmų padaryti.
        return recordRepository.findById(id)
                .orElseThrow(() -> new BlogRecordNotFoundException(id.toString()));

    }




    @Override
    public List<BlogRecord> getRecords() {  // 05-03 suveike
        return recordRepository.findAll();
    }

    @Override
    public void update(BlogRecord blogRecord) {
        recordRepository.save(blogRecord);
    }

    @Override
    public void delete(UUID uuid) {
        recordRepository.deleteById(uuid);
    }

//    TODO biški nepagaunu šitos implimentacijos. Iš kur jis repo guana info?
    @Override
    public BlogRecord getByUsername(String name) {
        return recordRepository.findByUsername(name).get(0);
    }


    // 05-04 Pridedu paginationa
    @Override
    public Page<BlogRecord> getBlogRecordsPaginated(Pageable pageable) {
        return recordRepository.findAll(pageable);
    }
}
