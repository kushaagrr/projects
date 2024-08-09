package dev.studenterp.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "education")
@AllArgsConstructor
@NoArgsConstructor
@Setter
public class Education {
    @Id @Getter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int eduID;
    @Getter
    private String recent;
    @Getter
    private String grade;
    // proof

    @OneToOne
    @JoinColumn(name = "rollno")
    private Student student;
}
