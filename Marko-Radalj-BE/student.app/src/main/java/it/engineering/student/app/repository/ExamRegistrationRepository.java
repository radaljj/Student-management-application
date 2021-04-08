package it.engineering.student.app.repository;

import it.engineering.student.app.entity.ExamRegistration;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExamRegistrationRepository extends JpaRepository<ExamRegistration,Long> {
}
