package lt.codeacademy.blogas.controller;


import lt.codeacademy.blogas.model.BlogRecord;
import lt.codeacademy.blogas.model.User;
import lt.codeacademy.blogas.service.UserService;
import org.dom4j.rule.Mode;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

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


    // 2. randame susikurtą userį pagal vardą
//

    @GetMapping("/findByName")
    public String findUserByName(@RequestParam String username, Model model){
        model.addAttribute("userToWorkWith", userService.getUserByName(username));
//        System.out.println("raday userį: " + model.getAttribute("userToFind"));
        return "existingUser";
    }

//    TODO neapsisprendžiu ar reikia geto pagal id.


    // 3. leidžiame updeitinti userį.
    @GetMapping("/update")
    public String findUserByIdToUpdate(@RequestParam UUID id, Model model){
        User userToUpdate = userService.getUser(id);
        model.addAttribute("userToWorkWith", userToUpdate);
        return "existingUser";
    }

    @PutMapping("/update/{id}")
    public String updateUser(User user, Model model){
        userService.update(user);
//        model.addAttribute("userToWorkWith", use)
        return "existingUser";
    }


    // 4.deletiname userį.
}
