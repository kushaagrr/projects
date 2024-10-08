package dev.studenterp.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "course")
@NoArgsConstructor
@AllArgsConstructor
@Setter
public class Course {
    @Id @Getter
    @Column(name = "course_no", nullable = false)
    private int courseNo;
    @Getter
    private String name;
    @Getter
    private byte credits;
    @Getter
    private String type;
    @Getter
    private String brochure;

    @OneToOne(mappedBy = "course") @Getter
    private Enrollment enrollment;
}
