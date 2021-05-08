package lt.codeacademy.blogas.controller;


import lt.codeacademy.blogas.model.BlogRecord;
import lt.codeacademy.blogas.model.User;
import lt.codeacademy.blogas.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;

@Controller
@RequestMapping("/registration")
public class userController {

    private final UserService userService;

    public userController(UserService userService) {
        this.userService = userService;
    }


    // 0. Atidarome create user formą

    @GetMapping("/createUser")
    public String createNewUser(Model model){
        model.addAttribute("newUser", new User());
        return "registration";
    }

    // 1. susikuriam naują userį.
    @PostMapping("/createUser")
    public String createNewUser(@Valid User user, Model model){
        model.addAttribute("user", new User());
        userService.addUser(user);

        return "redirect:/registration/createUser";
    }


    // 2. randame susikurtą userį pagal vardą.

    @GetMapping("/findUser")
    public String findUserByName(@RequestParam String userName, Model model){
        model.addAttribute("userToFind", userService.getUserByName(userName));
//        System.out.println("raday userį: " + model.getAttribute("userToFind"));
        return "existingUser";
    }


    // 3. leidžiame updeitinti userį.

    // 4.deletiname userį.
}
