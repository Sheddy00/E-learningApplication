package model;

import lombok.*;

@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class User {
    private int id;
    private String firstName;
    private String lastName;
    private String mail;
    private String password;
    private String gender;
}
