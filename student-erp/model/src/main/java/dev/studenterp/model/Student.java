package dev.studenterp.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "student")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Student {
    @Id
    private int rollno;
    private String firstName;
    private String lastName;

    @OneToOne(mappedBy = "student")
    private Enrollment enrollment;
    @OneToOne(mappedBy = "student")
    private Education education;
    @OneToOne(mappedBy = "student")
    private Details details;
}
