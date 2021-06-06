package lt.codeacademy.blogas.service;


import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;


/**
 * 1. Šito serviso reikia kad perduotume stringus thymeafui.
 *    Susiradau žinutės šaltinį ir gaudau, apgal lokalę.
 *
 * */

@Service
public class MessageService {

   private final MessageSource messageSource;

    public MessageService(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    public String getMessage(String key){
        return messageSource.getMessage(key, null, LocaleContextHolder.getLocale());
    }
}
