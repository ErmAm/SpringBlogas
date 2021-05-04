package lt.codeacademy.blogas.controller;

import lt.codeacademy.blogas.model.BlogRecord;
import lt.codeacademy.blogas.service.RecordService;

import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/records")
public class RecordControler {

    private final RecordService recordService;

    public RecordControler(RecordService newRecordService) {
        this.recordService = newRecordService;
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

        recordService.addRecord(blogRecord);
//        System.out.println(blogRecord);

        return "redirect:/records/createRecord";
    }

//    *** 04-30

//    ieškom produkto pagal pavadinimą.
    @GetMapping("/findRecordByName")
    public String getRecordByName(@RequestParam String name, Model model){
        model.addAttribute("blogRecord", recordService.getByUsername(name));
     return "blogRecord";
    }


//    Updeitianam dar reikia posta pridėti updeitui.
     @GetMapping("/update")
    public String updateRecord(@RequestParam UUID id, Model model){
        BlogRecord blogRecord = recordService.getRecord(id);
        model.addAttribute("blogRecord", blogRecord);
        return "blogRecord";
     }



    @GetMapping("/all")
    public String getRecords(Pageable pageable, Model model){
//        List<BlogRecord> blogContent = recordService.getRecords();
        model.addAttribute("blogRecordList", recordService.getBlogRecordsPaginated(pageable));

        return "blogMainPage";
    }

//    05-03 gauname vieną blogo įrašą
    @GetMapping("/{id}")
    public String getBlogRecord(@PathVariable final UUID id, Model model){

        BlogRecord blogRecord = recordService.getRecord(id);
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
