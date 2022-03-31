package jslozano.thesis.controller;

import jslozano.thesis.command.UserBasic;
import jslozano.thesis.command.UserCommand;
import jslozano.thesis.model.User;
import jslozano.thesis.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }
    @RequestMapping("user/new")
    public String newUser(Model model){
        model.addAttribute("user", new UserCommand());
        return "user/userform";
    }
    @PostMapping("user")
    public String saveOrUpdate(@ModelAttribute UserCommand userCommand){
        UserCommand savedCommand = userService.saveUserCommand(userCommand);

        return "redirect:";
    }
    @RequestMapping("user/update")
    public String updateUser(@ModelAttribute UserBasic userBasic, Model model){
        User user = userService.findById(userBasic.getId());
        model.addAttribute("user", userService.findCommandById(userBasic.getId()));
        return "user/userform";
    }
}
