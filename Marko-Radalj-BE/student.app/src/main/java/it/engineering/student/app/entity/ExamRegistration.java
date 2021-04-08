package it.engineering.student.app.entity;


import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name="exam_registration")
public class ExamRegistration {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "exam_registration_id", unique = true)
    private Long examRegistrationId;


    @ManyToOne
    @JoinColumn(name = "exam_id")
    private Exam exam;

    @OneToOne
    @JoinColumn(name = "student_id")
    private Student student;

    public ExamRegistration(Long examRegistrationId, Exam exam, Student student) {
        this.examRegistrationId = examRegistrationId;
        this.exam = exam;
        this.student = student;
    }

    public ExamRegistration(){}

    public Long getExamRegistrationId() {
        return examRegistrationId;
    }

    public void setExamRegistrationId(Long examRegistrationId) {
        this.examRegistrationId = examRegistrationId;
    }

    public Exam getExam() {
        return exam;
    }

    public void setExam(Exam exam) {
        this.exam = exam;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    @Override
    public String toString() {
        return "ExamRegistration{" +
                "examRegistrationId=" + examRegistrationId +
                ", exam=" + exam +
                ", student=" + student +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ExamRegistration that = (ExamRegistration) o;
        return Objects.equals(examRegistrationId, that.examRegistrationId) &&
                Objects.equals(exam, that.exam) &&
                Objects.equals(student, that.student);
    }

    @Override
    public int hashCode() {
        return Objects.hash(examRegistrationId, exam, student);
    }
}
