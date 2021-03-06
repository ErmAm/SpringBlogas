package lt.codeacademy.blogas.controller;


import lt.codeacademy.blogas.model.User;
import lt.codeacademy.blogas.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@Controller
@RequestMapping("/public/registration")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/createUser")
    public String createNewUser(Model model){
        model.addAttribute("newUser", new User());
        return "registration";
    }

    @PostMapping("/createUser")
    public String createNewUser(@Valid User user, BindingResult errors){
        if (errors.hasErrors()) {
            return "registration";
        }
        userService.addUser(user);
        return "redirect:/records/public/all/?message=user.created.success.message";
    }

    @GetMapping("/findByName")
    public String findUserByName(@RequestParam String username, Model model){
        model.addAttribute("userToWorkWith", userService.getUserByName(username));
        return "existingUser";
    }

    @GetMapping("/update")
    public String findUserByIdToUpdate(@RequestParam UUID id, Model model){
        User userToUpdate = userService.getUserById(id);
        model.addAttribute("userToWorkWith", userToUpdate);
        return "existingUser";
    }

    @GetMapping("/delete")
    public String deketeUser(@RequestParam UUID id){
        userService.delete(id);
        return "redirect:/registration/createUser";
    }

}
