package model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@EqualsAndHashCode
public class Teacher extends User{
    private boolean availability;

    public Teacher(int id, String firstName, String lastName, String mail, String password, String gender, boolean availability) {
        super(id, firstName, lastName, mail, password, gender);
        this.availability = availability;
    }
}
