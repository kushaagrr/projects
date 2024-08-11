package dev.studenterp.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "enrollment")
@Setter
public class Enrollment {
    @Id @Getter
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int enrollmentID;
    @Getter
    private Date enrolledDate;
    @Getter
    private int semester;
    @Getter
    private int year;

    @OneToOne(mappedBy = "enrollment") @Getter
    private Report report;

    @OneToOne
    @JoinColumn(name = "rollno")
    private Student student;
    @OneToOne
    @JoinColumn(name = "course_no")
    private Course course;
}
