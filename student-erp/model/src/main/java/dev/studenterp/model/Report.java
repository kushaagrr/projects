package dev.studenterp.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "report")
@Setter
public class Report {
    @Id @Getter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int reportID;
    @Getter
    private int marksObtained;
    @Getter
    private int credits;

    @OneToOne
    @JoinColumn(name = "subject_code")
    private Subject subject;
    @OneToOne
    @JoinColumn(name = "enrollment_id")
    private Enrollment enrollment;
}
