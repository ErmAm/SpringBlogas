package lt.codeacademy.blogas.controller;

import lt.codeacademy.blogas.model.BlogRecord;
import lt.codeacademy.blogas.service.NewRecordService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/records")
public class NewRecordControler {

    private final NewRecordService newRecordService;

    public NewRecordControler(NewRecordService newRecordService) {
        this.newRecordService = newRecordService;
    }

    //    tiesiog atidarom controlerio formą
    @GetMapping("/createRecord")
    public String getSigngleBlogCreationPage(Model model) {

        model.addAttribute("blogRecord", new BlogRecord());
        return "blogRecord";
    }

//    gali būti tie patys patchai nes metodai skiriasi.
    @PostMapping("/createRecord")
    public String createProduct(BlogRecord blogRecord, Model model){
        model.addAttribute("blogRecord", new BlogRecord());
        model.addAttribute("success", "blogRecord save successfully");

        newRecordService.addRecord(blogRecord);

        return "blogRecord";
    }





}
