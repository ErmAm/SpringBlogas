package lt.codeacademy.blogas.controller;

import lt.codeacademy.blogas.model.BlogRecord;
import lt.codeacademy.blogas.model.Comment;
import lt.codeacademy.blogas.model.User;
import lt.codeacademy.blogas.model.exception.BlogRecordNotFoundException;
import lt.codeacademy.blogas.service.CommentService;
import lt.codeacademy.blogas.service.MessageService;
import lt.codeacademy.blogas.service.RecordService;

import lt.codeacademy.blogas.service.UserService;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
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

    //    sukuriam  puslapį
    @GetMapping("/private/createRecord")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public String getSigngleBlogCreationPage(Model model, User user) {
        model.addAttribute("blogRecord", new BlogRecord());
        model.addAttribute("currentUser", user);
        return "blogRecord";
    }

    @PostMapping("/private/createRecord")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public String createProduct(@Valid BlogRecord blogRecord, BindingResult errors) {

        if (errors.hasErrors()) {
            return "blogRecord";
        }
        recordService.addRecord(blogRecord);

        return "redirect:/records/public/all/?message=blogRecord.created.success.message";
    }


    //    ieškom produkto pagal pavadinimą.
    @GetMapping("public/findRecordByName")
    public String getRecordByName(@RequestParam String name, Model model) {
        model.addAttribute("blogRecord", recordService.getByUsername(name));
        return "blogRecord";
    }


    @GetMapping("public/all")
    public String getRecords(Pageable pageable, Model model, String message) {


//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        String currentPrincipalName = authentication.getName();
//        System.out.println("***********************" + currentPrincipalName);

        model.addAttribute("blogRecordListPage", recordService.getBlogRecordsPaginated(pageable));

        if (message != null) {
            model.addAttribute("blogRecordCreatedMsg", messageService.getMessage(message));
        }

        return "blogMainPage";
    }

    //    05-03 gauname vieną blogo įrašą
    @GetMapping("public/{id}")
//    @PreAuthorize("hasRole('ADMIN')")
    public String getBlogRecord(@PathVariable final UUID id, Model model
     ) {

//      1.  Kai atidarau blogo įrašą reikia parisiusti prie jo esančius komentarus.
//        Todėl reikia traukti komentarų listą iš komentarų serviso, t.y reikia traukti komentarų lentą iš db.
//        SecurityContextHolder.getContext().getAuthentication();
//        model.addAttribute("principalas", principal);
//        model.addAttribute("userDetail", user);
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
    @PreAuthorize("hasAnyRole('ADMIN')")
    public String deleteBlogRecord(@PathVariable final UUID id) {
        recordService.delete(id);
        return "redirect:/records/public/all";
    }

    /**
     * Updeitas
     */

    @GetMapping("/private/update/{id}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public String updateRecord(@PathVariable UUID id, Model model) {
        BlogRecord blogRecord = recordService.getRecord(id);
        model.addAttribute("blogRecord", blogRecord);
        return "blogRecord";
    }

    @PostMapping("/private/update/{id}")
    @PreAuthorize("hasAnyRole('ADMIN')")
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

    @GetMapping("private/updateComment/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    public String updateComment(@PathVariable UUID id, Model model,Principal principal) {

        Comment commentToUpdate = commentService.getComment(id);

//        1.1 patikrinu komentaro autorių. Išmetė null vadinasi tusčias. Reikia perdaryti į optional.
//        if(commentToUpdate.getUser().getId().equals(principal.getName())){
//            System.out.println("Sutapo pavadinimas");
//            System.out.println("*******************" + commentToUpdate.getUser().toString());
//        }

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

    @PostMapping("/private/deleteComment/{id}")
    public String deleteComment(@PathVariable final UUID id) {
        String blogID = commentService.getComment(id).getBlogRecord().getId().toString();
        commentService.delete(id);
        return "redirect:/records/public/" + blogID;
    }


}
