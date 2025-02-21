package com.hahaton.hahaton.Entities;

import com.hahaton.hahaton.Entities.DTO.AcademicSubjectDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class AcademicSubject {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private  String urlToRoom;
    @ManyToOne
    private Teacher teacher;

    public AcademicSubject(AcademicSubjectDTO academicSubjectDTO, Teacher teacher){
        this.id = academicSubjectDTO.getId();
        this.name = academicSubjectDTO.getName();
        this.urlToRoom = academicSubjectDTO.getUrlToRoom();
        this.teacher = teacher;
    }

    public AcademicSubject() {
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

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }
}
