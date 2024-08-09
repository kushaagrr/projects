package dev.studenterp.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "subject")
@Data
public class Subject {
    @Id
    @Column(name = "subject_code", nullable = false)
    private int subjectCode;
    private byte credit;
    private int obtainedScore;
    private int totalScore;
}
