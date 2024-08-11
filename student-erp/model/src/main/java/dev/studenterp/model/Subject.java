package dev.studenterp.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "subject")
@Setter
public class Subject {
    @Id @Getter
    @Column(name = "subject_code", nullable = false)
    private int subjectCode;
    @Getter
    private byte credit;
    @Getter
    private String curriculum;

    @OneToOne(mappedBy = "subject") @Getter
    private Report report;
}
