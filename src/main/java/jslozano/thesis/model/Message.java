package jslozano.thesis.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@ToString
@Getter
@Setter
@Entity
public class Message {
    public Message(){

    }
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String subject;
    private String attachment;
    @Lob
    private String body;
    private String userSentOrToSend;

    @ManyToOne
    private User user;

    @Enumerated(value = EnumType.STRING)
    private Type type;


    @OneToMany(cascade = CascadeType.ALL, mappedBy = "message")
    private Set<Label> labels = new HashSet<>();

}
