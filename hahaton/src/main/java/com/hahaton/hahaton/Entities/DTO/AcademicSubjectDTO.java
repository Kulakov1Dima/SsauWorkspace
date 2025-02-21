package com.hahaton.hahaton.Entities.DTO;

import com.hahaton.hahaton.Entities.AcademicSubject;
import com.hahaton.hahaton.Entities.Teacher;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class AcademicSubjectDTO {
    private Long id;
    private String name;
    private String urlToRoom;
    private Long teacher;

    public AcademicSubjectDTO(AcademicSubject academicSubject){
        this.id = academicSubject.getId();
        this.name = academicSubject.getName();
        this.urlToRoom = academicSubject.getUrlToRoom();
        this.teacher = academicSubject.getTeacher().getId();
    }

    public AcademicSubjectDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrlToRoom() {
        return urlToRoom;
    }

    public void setUrlToRoom(String urlToRoom) {
        this.urlToRoom = urlToRoom;
    }

    public Long getTeacher() {
        return teacher;
    }

    public void setTeacher(Long teacher) {
        this.teacher = teacher;
    }
}
