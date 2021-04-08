package it.engineering.student.app.dto;

import it.engineering.student.app.entity.Exam;
import it.engineering.student.app.entity.Student;
import java.io.Serializable;
import java.util.Objects;

public class ExamRegistrationDto implements Serializable {

    private Long examRegistrationId;
    private Student student;
    private Exam exam;

    public ExamRegistrationDto(Long examRegistrationId, Student student, Exam exam) {
        this.examRegistrationId = examRegistrationId;
        this.student = student;
        this.exam = exam;
    }

    public ExamRegistrationDto(){}

    public Long getExamRegistrationId() {
        return examRegistrationId;
    }

    public void setExamRegistrationId(Long examRegistrationId) {
        this.examRegistrationId = examRegistrationId;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Exam getExam() {
        return exam;
    }

    public void setExam(Exam exam) {
        this.exam = exam;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ExamRegistrationDto that = (ExamRegistrationDto) o;
        return Objects.equals(examRegistrationId, that.examRegistrationId) &&
                Objects.equals(student, that.student) &&
                Objects.equals(exam, that.exam);
    }

    @Override
    public int hashCode() {
        return Objects.hash(examRegistrationId, student, exam);
    }

    @Override
    public String toString() {
        return "ExamRegistrationDto{" +
                "examRegistrationId=" + examRegistrationId +
                ", student=" + student +
                ", exam=" + exam +
                '}';
    }
}
