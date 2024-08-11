package dev.studenterp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.studenterp.model.Student;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer>{
}
