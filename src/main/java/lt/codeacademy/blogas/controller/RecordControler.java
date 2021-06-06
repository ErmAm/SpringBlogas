package lt.codeacademy.blogas.controller;

import lt.codeacademy.blogas.model.BlogRecord;
import lt.codeacademy.blogas.model.Comment;
import lt.codeacademy.blogas.service.CommentService;
import lt.codeacademy.blogas.service.MessageService;
import lt.codeacademy.blogas.service.RecordService;

import lt.codeacademy.blogas.service.UserService;
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
    private final UserService userService;

    private final MessageService messageService;

    public RecordControler(RecordService newRecordService,
                           CommentService commentService,
                           UserService userService,
                           MessageService messageService) {
        this.recordService = newRecordService;
        this.commentService = commentService;
        this.userService = userService;
        this.messageService = messageService;
    }

    //    sukuriam vartotojoą
    @GetMapping("/createRecord")
    public String getSigngleBlogCreationPage(Model model) {
        model.addAttribute("blogRecord", new BlogRecord());

        return "blogRecord";
    }


//   TODO Redirektas turi eiti į agrindinį blogų lisinimo meniu.

    @PostMapping("/createRecord")
    public String createProduct(@Valid BlogRecord blogRecord) {
//        model.addAttribute("blogRecord", new BlogRecord());
//        model.addAttribute("success", "blogRecord save successfully");
        recordService.addRecord(blogRecord);

        return "redirect:/records/all/?message=blogRecord.created.success.message";
    }

//    *** 04-30

    //    ieškom produkto pagal pavadinimą.
    @GetMapping("/findRecordByName")
    public String getRecordByName(@RequestParam String name, Model model) {
        model.addAttribute("blogRecord", recordService.getByUsername(name));
        return "blogRecord";
    }


    //    Updeitianam dar reikia posta pridėti updeitui.
    @GetMapping("/update")
    public String updateRecord(@RequestParam UUID id, Model model) {
        BlogRecord blogRecord = recordService.getRecord(id);
        model.addAttribute("blogRecord", blogRecord);
        return "blogRecord";
    }

    @PostMapping("/update")
    public String updateRecord(BlogRecord blogRecord, Model model) {
        recordService.update(blogRecord);
        return "redirect:/records/all";
    }


    @GetMapping("/all")
    public String getRecords(Pageable pageable, Model model, String message) {
//        List<BlogRecord> blogContent = recordService.getRecords();
        model.addAttribute("blogRecordListPage", recordService.getBlogRecordsPaginated(pageable));

//      Čia reikėjo sudėti žinutes apie sėkmingą operaciją.
        if (message != null){
            model.addAttribute("blogRecordCreatedMsg", messageService.getMessage(message));
        }


        return "blogMainPage";
    }

    //    05-03 gauname vieną blogo įrašą
    @GetMapping("/{id}")
    public String getBlogRecord(@PathVariable final UUID id, Model model) {

//      1.  Kai atidarau blogo įrašą reikia parisiusti prie jo esančius komentarus.
//        Todėl reikia traukti komentarų listą iš komentarų serviso, t.y reikia traukti komentarų lentą iš db.

        BlogRecord blogRecord = recordService.getRecord(id);
        if (!blogRecord.equals(null)){

            model.addAttribute("associatedComments", commentService.filteredByBlogComments(id));
            model.addAttribute("blogRecordToView", blogRecord);
            return "viewBlogRecord";
        } else {
            return "404";
        }
    }


    /**
     * Komentarų dalis. Nežinau tik dar kaip viską implimentinsiu.
     * <p>
     * 1. Prasitestavau blogo pridėjima ir atėmimą viskas veikia
     * <p>
     * 2. Reikia sukurti atskira kontrolerį komentarų vievinimui.
     */

//    reikia pasiūsti thymeleafui id kuriam įrašui darysime komentrarą.


//    ar to reikia
    @GetMapping("/comment/addComment/{blog_id}")
    public String createNewCommentView(@PathVariable UUID blog_id, Model model) {
//        1. Čia tricky dalis reikia susirasti ar blogo irašas egzistuoja
        BlogRecord blogRecord = recordService.getRecord(blog_id);
        if (!blogRecord.equals(null)) {
            Comment newComent = new Comment();
            newComent.setBlogRecord(blogRecord);
//                1. TODO Reikia su useriu sutvarkyti šią vietą
//                newComent.setUser();

            model.addAttribute("newComment", newComent);

            return "comment";
        } else {
            return "404";
        }

    }

    @PostMapping("/comment/addComment/{blog_id}")
    public String addComment(@Valid Comment comment, Model model) {
        model.addAttribute("newComment", new Comment());
        model.addAttribute("success", "comment was created successfully");
        commentService.addComment(comment);
        return "redirect:/records/all";
    }


//    2. Gaunam visus komentarus susijusius su šiuo įrašu.
//    2.1 siunčiam užklausą servisui ten ir turi atvfiltruoti

//    @GetMapping("/comment/viewCommentsOfBlog/{blog_id}")
//    public String viewBlogComments(@PathVariable UUID blog_id, Model model){
//
//
////        Susitvarkom viev blogpost templeitą
//
//    }


}
