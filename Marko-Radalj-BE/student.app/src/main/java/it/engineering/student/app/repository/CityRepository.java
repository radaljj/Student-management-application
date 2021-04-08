package it.engineering.student.app.repository;

import it.engineering.student.app.entity.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CityRepository  extends JpaRepository<City,Long> {
}
