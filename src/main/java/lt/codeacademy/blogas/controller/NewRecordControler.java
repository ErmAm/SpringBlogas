package lt.codeacademy.blogas.controller;

import lt.codeacademy.blogas.model.BlogRecord;
import lt.codeacademy.blogas.service.NewRecordService;
import org.dom4j.rule.Mode;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.UUID;

@Controller
@RequestMapping("/records")
public class NewRecordControler {

    private final NewRecordService newRecordService;

    public NewRecordControler(NewRecordService newRecordService) {
        this.newRecordService = newRecordService;
    }

//    sukuriam vartotojoą
    @GetMapping("/createRecord")
    public String getSigngleBlogCreationPage(Model model) {

        model.addAttribute("blogRecord", new BlogRecord());
        return "blogRecord";
    }


//   TODO Redirektas turi eiti į agrindinį blogų lisinimo meniu.

    @PostMapping("/createRecord")
    public String createProduct(@Valid BlogRecord blogRecord, Model model){
        model.addAttribute("blogRecord", new BlogRecord());
        model.addAttribute("success", "blogRecord save successfully");

        newRecordService.addRecord(blogRecord);
//        System.out.println(blogRecord);

        return "redirect:/records/createRecord";
    }

//    *** 04-30

//    ieškom produkto pagal pavadinimą.
    @GetMapping("/findRecordByName")
    public String getRecordByName(@RequestParam String name, Model model){
        model.addAttribute("blogRecord", newRecordService.getByUsername(name));
     return "blogRecord";
    }


//    Updeitianam dar reikia posta pridėti updeitui.
     @GetMapping("/update")
    public String updateRecord(@RequestParam UUID id, Model model){
        BlogRecord blogRecord = newRecordService.getRecord(id);
        model.addAttribute("blogRecord", blogRecord);
        return "blogRecord";
     }


}
