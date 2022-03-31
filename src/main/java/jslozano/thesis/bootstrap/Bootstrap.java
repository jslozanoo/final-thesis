package jslozano.thesis.bootstrap;

import jslozano.thesis.model.Label;
import jslozano.thesis.model.Message;
import jslozano.thesis.model.Type;
import jslozano.thesis.model.User;
import jslozano.thesis.repositories.UserRepository;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class Bootstrap implements ApplicationListener<ContextRefreshedEvent> {
    private final UserRepository userRepository;

    public Bootstrap(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        userRepository.saveAll(getUsers());

    }

    private List<User> getUsers(){
        List<User> users = new ArrayList<>();

        User user1 = new User();
        user1.setUserName("jslozano");
        user1.setPassword("12345");
        user1.setFirstName("Juan");
        user1.setLastName("Lozano");
        user1.setIdentificationNumber(123456789L);
        user1.setAddress("13626 E Dakota Ave");
        user1.setZipCode("80012");
        user1.setState("Colorado");
        user1.setCountry("United States");

        User user2 = new User();
        user2.setUserName("kacostap");
        user2.setPassword("34567");
        user2.setFirstName("Katherin");
        user2.setLastName("Acosta");
        user2.setIdentificationNumber(1011121314L);
        user2.setAddress("124 S Elkhart St");
        user2.setZipCode("43004");
        user2.setState("Ohio");
        user2.setCountry("United States");

        User user3 = new User();
        user3.setUserName("fmartinez");
        user3.setPassword("qwerty");
        user3.setFirstName("Felipe");
        user3.setLastName("Martinez");
        user3.setIdentificationNumber(987654321L);
        user3.setAddress("Street 21 # 14-35");
        user3.setZipCode("110321");
        user3.setState("Bogota");
        user3.setCountry("Colombia");

        User user4 = new User();
        user4.setUserName("rrodriguez");
        user4.setPassword("kaju");
        user4.setFirstName("Ricardo");
        user4.setLastName("Rodriguez");
        user4.setIdentificationNumber(135792468L);
        user4.setAddress("Libertad 1154 Piso 02 Depto 102");
        user4.setZipCode("1602");
        user4.setState("Buenos Aires");
        user4.setCountry("Argentina");

        // Labels
        Label label1 = new Label();
        label1.setName("Studio");

        Label label2 = new Label();
        label2.setName("Project");

        Label label3 = new Label();
        label3.setName("TCBP");

        Label label4 = new Label();
        label4.setName("StarMeUp");

        Label label5 = new Label();
        label5.setName("PayRoll");

        Label label6 = new Label();
        label6.setName("Academy");


        // Message from user1 to user2
        Message message1 = new Message();
        message1.setSubject("Monday Task");
        message1.setBody("Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod" +
                        "tempor incididunt ut labore et dolore magna aliqua");
        message1.setAttachment("logo.jpg");
        message1.setUserSentOrToSend(user2.getUserName());
        message1.setType(Type.SENT);



        user1.getMessages().add(message1);
        message1.setUser(user1);

        //Clone message for user 2, one message can´t have two IDs
        Message message1Sent = new Message();
        message1Sent.setSubject("Monday Task");
        message1Sent.setBody("Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod" +
                "tempor incididunt ut labore et dolore magna aliqua");
        message1Sent.setAttachment("logo.jpg");
        message1Sent.setUserSentOrToSend(user1.getUserName());
        message1Sent.setType(Type.INBOX);

        user2.getMessages().add(message1Sent);
        message1Sent.setUser(user2);

        // Set Relationship between message and labels

        message1Sent.getLabels().add(label1);
        label1.setMessage(message1Sent);
        message1Sent.getLabels().add(label2);
        label2.setMessage(message1Sent);
        message1Sent.getLabels().add(label3);
        label3.setMessage(message1Sent);


        // Message from user1 to user 3
        Message message2 = new Message();
        message2.setSubject("Tuesday Task");
        message2.setBody("Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris " +
                "nisi ut aliquip ex ea commodo consequat");
        message2.setAttachment("globant.pdf");
        message2.setUserSentOrToSend(user3.getUserName());

        message2.setType(Type.SENT);

        user1.getMessages().add(message2);
        message2.setUser(user1);

        Message message2Sent = new Message();
        message2Sent.setType(Type.INBOX);
        message2Sent.setSubject("Tuesday Task");
        message2Sent.setBody("Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris " +
                "nisi ut aliquip ex ea commodo consequat");
        message2Sent.setAttachment("globant.pdf");
        message2Sent.setUserSentOrToSend(user1.getUserName());

        user3.getMessages().add(message2Sent);
        message2Sent.setUser(user3);

        // Set relationship with labels

        message2Sent.getLabels().add(label4);
        label4.setMessage(message2Sent);
        message2Sent.getLabels().add(label5);
        label5.setMessage(message2Sent);
        message2Sent.getLabels().add(label6);
        label6.setMessage(message2Sent);

        // Message from user2 to user3 without CC
        Message message3 = new Message();
        message3.setSubject("Wednesday Task");
        message3.setBody("Duis aute irure dolor in reprehenderit in voluptate velit esse " +
                "cillum dolore eu fugiat nulla pariatur");
        message3.setAttachment("project.txt");
        message3.setUserSentOrToSend(user3.getUserName());
        message3.setType(Type.SENT);

        user2.getMessages().add(message3);
        message3.setUser(user2);

        Message message3Sent = new Message();
        message3Sent.setSubject("Wednesday Task");
        message3Sent.setBody("Duis aute irure dolor in reprehenderit in voluptate velit esse " +
                "cillum dolore eu fugiat nulla pariatur");
        message3Sent.setAttachment("project.txt");
        message3Sent.setUserSentOrToSend(user2.getUserName());
        message3Sent.setType(Type.INBOX);

        user3.getMessages().add(message3Sent);
        message3Sent.setUser(user3);

        // Set relationship with labels

        message3Sent.getLabels().add(label2);
        label2.setMessage(message3Sent);
        message3Sent.getLabels().add(label4);
        label4.setMessage(message3Sent);
        message3Sent.getLabels().add(label6);
        label6.setMessage(message3Sent);

        users.add(user1);
        users.add(user2);
        users.add(user3);
        users.add(user4);
        return users;

    }
}
