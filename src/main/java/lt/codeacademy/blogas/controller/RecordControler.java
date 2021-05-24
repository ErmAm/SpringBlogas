package lt.codeacademy.blogas.controller;

import lt.codeacademy.blogas.model.BlogRecord;
import lt.codeacademy.blogas.model.Comment;
import lt.codeacademy.blogas.service.CommentService;
import lt.codeacademy.blogas.service.RecordService;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@Controller
@RequestMapping("/records")
public class RecordControler {

    private final RecordService recordService;
    private final CommentService commentService;

    public RecordControler(RecordService newRecordService, CommentService commentService) {
        this.recordService = newRecordService;
        this.commentService = commentService;
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

//        TODO po posto nukreipiam į main puslapį.
//        aba paliekam stebėti savus blogo įrašus. .
        return "redirect:/records/all";
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

    @PostMapping("/update")
    public String updateRecord(BlogRecord blogRecord, Model model){
        recordService.update(blogRecord);
        return "redirect:/records/all";
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

//      1.  Kai atidarau blogo įrašą reikia parisiusti prie jo esančius komentarus.
//        Todėl reikia traukti komentarų listą iš komentarų serviso, t.y reikia traukti komentarų lentą iš db.


        BlogRecord blogRecord = recordService.getRecord(id);
        model.addAttribute("blogRecordToView", blogRecord);
        return "viewBlogRecord";

    }


    /**
     *
     *  Komentarų dalis. Nežinau tik dar kaip viską implimentinsiu.
     *
     * */

//    reikia pasiūsti thymeleafui id kuriam įrašui darysime komentrarą.

    @GetMapping("/comment/addComment")
    public String createNewCommentView(@RequestParam(required=false) UUID userId, Model model){
        model.addAttribute("newComment", new Comment());
//      TODO  reikia pridėti userio id.
//        model.addAttribute();

        return "comment";
    }

    @PostMapping("/comment/addComment")
    public String addComment(@Valid Comment comment, Model model){
        model.addAttribute("newComment", new Comment());
        model.addAttribute("success", "comment was created successfully");
        commentService.addComment(comment);
        return "redirect:/records/all";
    }


//    2. Gaunam visus commentarus.

//    @PostMapping("/createRecord")
//    public String createProduct(@Valid BlogRecord blogRecord, Model model){
//        model.addAttribute("blogRecord", new BlogRecord());
//        model.addAttribute("success", "blogRecord save successfully");
//        recordService.addRecord(blogRecord);
//        return "redirect:/records/all";
//    }


//    // Single user page
//    @RequestMapping("/users/{userId}")
//    public String user(@PathVariable Long userId, Model model) {
//        User user = userService.findById(userId);
//        model.addAttribute("user", user);
//        return "user/details";
//    }

}
