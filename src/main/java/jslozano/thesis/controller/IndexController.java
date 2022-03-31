package jslozano.thesis.controller;

import jslozano.thesis.command.UserBasic;
import jslozano.thesis.command.UserLoginCommand;
import jslozano.thesis.model.User;
import jslozano.thesis.service.MessageService;
import jslozano.thesis.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller
public class IndexController {
    private final UserService userService;
    private final MessageService messageService;

    public IndexController(UserService userService, MessageService messageService) {
        this.userService = userService;
        this.messageService = messageService;
    }

    @RequestMapping("")
    public String loginUser(Model model){
        model.addAttribute("user", new UserLoginCommand());
        return "loginform";
    }

    @RequestMapping("/validateLogin")
    public String validateLoginUser(@ModelAttribute UserLoginCommand user, Model model){
        try {
            Optional<User> userToLogin = userService.findUserByUsernameAndPassword
                    (user.getUserName(), user.getPassword());
            User userLogged = userToLogin.get();
            model.addAttribute("userBasicToCreateMessage", new UserBasic());
            model.addAttribute("userUpdate", new UserBasic());
            model.addAttribute("userBasic", new UserBasic());
            model.addAttribute("user", userLogged);
            model.addAttribute("messages",messageService.getMessages(userLogged.getId()));
            return "main";
        }catch (Exception e){
            return "error";
        }
    }
}
