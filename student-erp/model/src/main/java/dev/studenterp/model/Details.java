package dev.studenterp.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "student_details")
@AllArgsConstructor
@NoArgsConstructor
@Setter
public class Details {
    @Id @Getter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int detailsID;
    @Getter
    private String fatherName;
    @Getter
    private String motherName;
    @Getter
    private String fatherOccupation;
    @Getter
    private String motherOccupation;
    @Getter
    private String address;

    @OneToOne
    @JoinColumn(name = "rollno")
    private Student student;
}
