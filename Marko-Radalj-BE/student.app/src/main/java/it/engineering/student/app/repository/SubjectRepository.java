package it.engineering.student.app.repository;

import it.engineering.student.app.entity.Subject;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubjectRepository extends JpaRepository<Subject,Long> {
}
