package lt.codeacademy.blogas.controller;

import lt.codeacademy.blogas.model.BlogRecord;
import lt.codeacademy.blogas.service.NewRecordService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
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


//TODO 2021 05 03  Siunčiam  listą su recordais parodymui ant ekrano.
    @GetMapping("/all")
    public String getRecords(Model model){
        List<BlogRecord> blogContent = newRecordService.getRecords();
        model.addAttribute("blogRecordList", blogContent);

        return "blogMainPage";
    }

//    05-03 gauname vieną blogo įrašą
    @GetMapping("/{id}")
    public String getBlogRecord(@PathVariable final UUID id, Model model){

        BlogRecord blogRecord = newRecordService.getRecord(id);
        model.addAttribute("blogRecordToView", blogRecord);
        return "viewBlogRecord";

    }


//    // Single user page
//    @RequestMapping("/users/{userId}")
//    public String user(@PathVariable Long userId, Model model) {
//        User user = userService.findById(userId);
//        model.addAttribute("user", user);
//        return "user/details";
//    }

}
