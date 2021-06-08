package lt.codeacademy.blogas.controller;

import lt.codeacademy.blogas.model.BlogRecord;
import lt.codeacademy.blogas.model.Comment;
import lt.codeacademy.blogas.model.exception.BlogRecordNotFoundException;
import lt.codeacademy.blogas.service.CommentService;
import lt.codeacademy.blogas.service.MessageService;
import lt.codeacademy.blogas.service.RecordService;

import lt.codeacademy.blogas.service.UserService;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;
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
    @GetMapping("/private/createRecord")
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    public String getSigngleBlogCreationPage(Model model) {
        model.addAttribute("blogRecord", new BlogRecord());

        return "blogRecord";
    }

//    TODO gali nereikėti modelio atributo.

    @PostMapping("/private/createRecord")
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    public String createProduct(@Valid BlogRecord blogRecord, BindingResult errors, Model model) {

        if (errors.hasErrors()) {
            return "blogRecord";
        }
        recordService.addRecord(blogRecord);


        return "redirect:/records/public/all/?message=blogRecord.created.success.message";
    }

//    *** 04-30

    //    ieškom produkto pagal pavadinimą.
    @GetMapping("public/findRecordByName")
    public String getRecordByName(@RequestParam String name, Model model) {
        model.addAttribute("blogRecord", recordService.getByUsername(name));
        return "blogRecord";
    }


    @GetMapping("public/all")
    public String getRecords(Pageable pageable, Model model, String message) {
//        List<BlogRecord> blogContent = recordService.getRecords();
        model.addAttribute("blogRecordListPage", recordService.getBlogRecordsPaginated(pageable));

//      Čia reikėjo sudėti žinutes apie sėkmingą operaciją.
        if (message != null) {
            model.addAttribute("blogRecordCreatedMsg", messageService.getMessage(message));
        }

        return "blogMainPage";
    }

    //    05-03 gauname vieną blogo įrašą
    @GetMapping("public/{id}")
//    @PreAuthorize("hasRole('ADMIN')")
    public String getBlogRecord(@PathVariable final UUID id, Model model) {

//      1.  Kai atidarau blogo įrašą reikia parisiusti prie jo esančius komentarus.
//        Todėl reikia traukti komentarų listą iš komentarų serviso, t.y reikia traukti komentarų lentą iš db.

        BlogRecord blogRecord = recordService.getRecord(id);
        if (!blogRecord.equals(null)) {

            model.addAttribute("associatedComments", commentService.filteredByBlogComments(id));
            model.addAttribute("blogRecordToView", blogRecord);
            return "viewBlogRecord";
        } else {
            return "404";
        }
    }

    /**
     * Pridedu delete mygtuką
     * <p>
     * 1. Sutvarkytas daugiau minčių nėra
     */


    @PostMapping("private/delete/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    public String deleteBlogRecord(@PathVariable final UUID id) {
        recordService.delete(id);
        return "redirect:/records/public/all";
    }

    /**
     * Updeitas
     */
    //   1. Patikrinu ar bent veikia
    @GetMapping("/private/update/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    public String updateRecord(@PathVariable UUID id, Model model) {
        BlogRecord blogRecord = recordService.getRecord(id);
        model.addAttribute("blogRecord", blogRecord);
        return "blogRecord";
    }

    @PostMapping("/private/update/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    public String updateRecord(@Valid BlogRecord blogRecord, Model model, BindingResult errors) {
        if (errors.hasErrors()) {
            return "blogRecord";
        }
        recordService.update(blogRecord);
        return "redirect:/records/public/all";
    }

    /**
     * Komentarų dalis. Nežinau tik dar kaip viską implimentinsiu.
     * <p>
     * 1. Prasitestavau blogo pridėjima ir atėmimą viskas veikia
     * <p>
     * 2. Reikia sukurti atskira kontrolerį komentarų vievinimui.
     */

//    reikia pasiūsti thymeleafui id kuriam įrašui darysime komentrarą.
    @GetMapping("private/addComment/{blog_id}")
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    public String createNewCommentView(@PathVariable UUID blog_id, Model model) {
        BlogRecord blogRecord = recordService.getRecord(blog_id);
        if (!blogRecord.equals(null)) {
            Comment newComent = new Comment();
            newComent.setBlogRecord(blogRecord);
            model.addAttribute("newComment", newComent);

            return "comment";
        } else {
            return "404";
        }

    }

    @PostMapping("private/addComment/{blog_id}")
    public String addComment(@Valid Comment comment, Model model) {
        model.addAttribute("newComment", new Comment());
        model.addAttribute("success", "comment was created successfully");

        String blogoID = comment.getBlogRecord().getId().toString();

        commentService.addComment(comment);
        return "redirect:/records/public/" + blogoID;
    }

//       @GetMapping("/private/update/{id}")
//    @PreAuthorize("hasAnyRole('ADMIN','USER')")
//    public String updateRecord(@PathVariable UUID id, Model model) {
//        BlogRecord blogRecord = recordService.getRecord(id);
//        model.addAttribute("blogRecord", blogRecord);
//        return "blogRecord";
//    }
//
//    @PostMapping("/private/update/{id}")
//    @PreAuthorize("hasAnyRole('ADMIN','USER')")
//    public String updateRecord(@Valid BlogRecord blogRecord, Model model, BindingResult errors) {
//        if (errors.hasErrors()){
//            return "blogRecord";
//        }
//        recordService.update(blogRecord);
//        return "redirect:/records/public/all";
//    }


    //    Pridedam updeitą
    @GetMapping("private/updateComment/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    public String updateComment(@PathVariable UUID id, Model model) {
        Comment commentToUpdate = commentService.getComment(id);
        model.addAttribute("newComment", commentToUpdate);
        return "comment";
    }

    @PostMapping("private/updateComment/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    public String updateComment(@Valid Comment comment) {
        String blogID = comment.getBlogRecord().getId().toString();
        commentService.update(comment);
        return "redirect:/records/public/" + blogID;
    }

    //    Trynimas done
    @PostMapping("/private/deleteComment/{id}")
    public String deleteComment(@PathVariable final UUID id) {

        String blogID = commentService.getComment(id).getBlogRecord().getId().toString();
        commentService.delete(id);
        return "redirect:/records/public/" + blogID;
    }


}
