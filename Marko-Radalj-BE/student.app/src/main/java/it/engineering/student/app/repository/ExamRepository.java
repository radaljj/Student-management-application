package it.engineering.student.app.repository;

import it.engineering.student.app.entity.Exam;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExamRepository extends JpaRepository<Exam,Long> {
}
