package it.engineering.student.app.repository;

import it.engineering.student.app.entity.Title;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface TitleRepository extends JpaRepository<Title,Long> {
}
