package lt.codeacademy.blogas.controller;

import lt.codeacademy.blogas.model.exception.BlogRecordNotFoundException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ControllerAdvaice {



    /**
     *      Prisidedam exception handlerį
     *
     *      1. Nežinau ar panaudosiu normaliai.
     *      Šita vieta veikia tik neišspausdina į puslapį pagrindinį.
     *
     * */

    @ExceptionHandler(BlogRecordNotFoundException.class)
    public String blogRecordNotFound(BlogRecordNotFoundException e, Model model){
        model.addAttribute("blogID",e.getMessage());
        return "redirect:/records/public/all";
    }
}
