package lt.codeacademy.blogas.service;

import lt.codeacademy.blogas.repository.NewRecordRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Servisas stumdo info arba atlieka matematiką.
 *
 * */

@Service
public class NewRecordService {

    private final NewRecordRepositoryImpl repository;

    @Autowired
    public NewRecordService(NewRecordRepositoryImpl repository) {
        this.repository = repository;
    }
}
