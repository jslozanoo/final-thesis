package jslozano.thesis.controller;

import jslozano.thesis.command.MessageCommand;
import jslozano.thesis.command.UserBasic;
import jslozano.thesis.model.Type;
import jslozano.thesis.model.User;
import jslozano.thesis.service.MessageService;
import jslozano.thesis.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MessageController {
    private final MessageService messageService;
    private final UserService userService;


    public MessageController(MessageService messageService, UserService userService) {
        this.messageService = messageService;
        this.userService = userService;
    }
    @RequestMapping("/mail/show")
    public String showInboxMessage(@ModelAttribute UserBasic userBasic, Model model){
        try{
            User user = userService.findById(userBasic.getId());
            model.addAttribute("user", userBasic);
            if (userBasic.getType().equals("INBOX")){
                model.addAttribute("type", "INBOX");
                model.addAttribute("messages", messageService.
                        getInboxMessages(user.getId(), Type.INBOX));
            }
            else if (userBasic.getType().equals("SENT")){
                model.addAttribute("type", "SENT");
                model.addAttribute("messages", messageService.
                        getInboxMessages(user.getId(), Type.SENT));
            }
            return "message/showMail";
        }catch (Exception e){
            return "error";
        }
    }
    @RequestMapping("/mail/{messageId}/show")
    public String showById(@PathVariable Long messageId, @ModelAttribute UserBasic userBasic ,
                          Model model){
        model.addAttribute("message", messageService.
                findMessageById(userBasic.getId() , messageId));
        return "message/showMessage";
    }
    @RequestMapping("/mail/{messageId}/delete")
    public String deleteById(@PathVariable Long messageId, @ModelAttribute UserBasic userBasic,
                             Model model){
        messageService.deleteById(userBasic.getId(), messageId);
        model.addAttribute("user", userBasic);
        if (userBasic.getType().equals("INBOX")){
            model.addAttribute("type", "INBOX");
            model.addAttribute("messages", messageService.
                    getInboxMessages(userBasic.getId(), Type.INBOX));
        }
        else if (userBasic.getType().equals("SENT")){
            model.addAttribute("type", "SENT");
            model.addAttribute("messages", messageService.
                    getInboxMessages(userBasic.getId(), Type.SENT));
        }
        return "message/showMail";
    }
    @RequestMapping("/message/new")
    public String newMessage(@ModelAttribute UserBasic userBasic, Model model){
        // userBasic has the id property of user, to maintain logged
        model.addAttribute("message", new MessageCommand());
        model.addAttribute("user", userBasic);
        return "message/messageform";
    }
    @RequestMapping("/message/create")
    public String saveMessage(@ModelAttribute UserBasic userBasic,
                              @ModelAttribute MessageCommand message){
        // userBasic has the id of the sender, in to of the message is the name of the receiver
        // Implement in the service the SavedMessageCommand and have in mind that I have
        // To clone the message for the receiver.

        return "error";

    }

}