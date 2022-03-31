package jslozano.thesis.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "username")
    private String userName;
    private String password;
    private String firstName;
    private String lastName;
    private Long identificationNumber;
    private String address;
    private String zipCode; // First Digit may be 0
    private String state;
    private String country;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private Set<Message> messages = new HashSet<>();
}
