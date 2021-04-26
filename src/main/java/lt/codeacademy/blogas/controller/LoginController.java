package lt.codeacademy.blogas.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class LoginController {

// ==   Pridedu testavimo metodą pasitikrinti ar važiuoja saitas

    @GetMapping("/{text}")
    public String hello(@PathVariable String text, Model model){

//        reikia returninti templeito adresą.
        return "hello";
    }

}
