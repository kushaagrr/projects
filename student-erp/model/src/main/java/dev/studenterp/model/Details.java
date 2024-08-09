package dev.studenterp.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "student_details")
@AllArgsConstructor
@NoArgsConstructor
@Setter
public class Details {
    @Id
    private int detailsID;
    private String fatherName;
    private String motherName;
    private String fatherOccupation;
    private String motherOccupation;
    // documents
    // address

    @OneToOne
    @JoinColumn(name = "rollno")
    private Student student;
}
