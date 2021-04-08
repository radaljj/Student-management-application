package it.engineering.student.app.entity;
import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name ="subject")
public class Subject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="subject_id",unique = true)
    private Long idSubject;

    @Column(name="subject_name",nullable = false)
    private String name;

    @Column(name="subject_description")
    private String description;

    @Column(name="subject_semester")
    private String semester;

    @Column(name = "esp",nullable = false)
    private Long esp;

    @Column(name = "year_of_study")
    private Long yearOfStudy;

    public Subject(Long idSubject, String name, String description, String semester, Long esp, Long yearOfStudy) {
        this.idSubject = idSubject;
        this.name = name;
        this.description = description;
        this.semester = semester;
        this.esp = esp;
        this.yearOfStudy = yearOfStudy;
    }

    public Subject(){}

    public Long getIdSubject() {
        return idSubject;
    }

    public void setIdSubject(Long idSubject) {
        this.idSubject = idSubject;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    public Long getEsp() {
        return esp;
    }

    public void setEsp(Long esp) {
        this.esp = esp;
    }

    public Long getYearOfStudy() {
        return yearOfStudy;
    }

    public void setYearOfStudy(Long yearOfStudy) {
        this.yearOfStudy = yearOfStudy;
    }

    @Override
    public String toString() {
        return "Subject{" +
                "idSubject=" + idSubject +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", semester='" + semester + '\'' +
                ", esp=" + esp +
                ", yearOfStudy=" + yearOfStudy +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Subject subject = (Subject) o;
        return Objects.equals(idSubject, subject.idSubject) &&
                Objects.equals(name, subject.name) &&
                Objects.equals(description, subject.description) &&
                Objects.equals(semester, subject.semester) &&
                Objects.equals(esp, subject.esp) &&
                Objects.equals(yearOfStudy, subject.yearOfStudy);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idSubject, name, description, semester, esp, yearOfStudy);
    }
}
