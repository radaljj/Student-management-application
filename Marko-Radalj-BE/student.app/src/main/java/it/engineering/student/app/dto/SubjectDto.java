package it.engineering.student.app.dto;

public class SubjectDto {
    private Long idSubject;
    private String name;
    private String description;
    private String semester;
    private Long esp;
    private Long yearOfStudy;

    public SubjectDto(Long idSubject, String name, String description, String semester, Long esp, Long yearOfStudy) {
        this.idSubject = idSubject;
        this.name = name;
        this.description = description;
        this.semester = semester;
        this.esp = esp;
        this.yearOfStudy = yearOfStudy;
    }

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
        return "SubjectDto{" +
                "idSubject=" + idSubject +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", semester='" + semester + '\'' +
                ", esp=" + esp +
                ", yearOfStudy=" + yearOfStudy +
                '}';
    }
}
