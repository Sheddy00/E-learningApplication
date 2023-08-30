package model;

import lombok.*;

@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class Course {
    private int id;
    private String name;
    private String code;
    private String duration;
}
