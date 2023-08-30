package model;

import lombok.*;

@Getter
@Setter
@ToString
@EqualsAndHashCode(callSuper = false)
public class Student extends User{
    private boolean active;

    public Student(int id, String firstName, String lastName, String mail, String password, String gender, boolean active) {
        super(id, firstName, lastName, mail, password, gender);
        this.active = active;
    }
}
