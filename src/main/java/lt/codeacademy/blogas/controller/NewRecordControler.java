package lt.codeacademy.blogas.controller;

import lt.codeacademy.blogas.model.BlogRecord;
import lt.codeacademy.blogas.service.NewRecordService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

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





}
