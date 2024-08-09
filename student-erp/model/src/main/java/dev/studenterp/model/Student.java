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
    private String regID;
    private String firstName;
    private String lastName;

    // enrolled courses
    @OneToOne()
    @JoinColumn(name = "course_no")
    private Course course;
    // recent education
    @OneToOne(mappedBy = "student")
    private Education education;
    // student details
    @OneToOne(mappedBy = "student")
    private Details details;
}
