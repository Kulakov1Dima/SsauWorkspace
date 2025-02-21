package com.hahaton.hahaton.Entities;

import com.hahaton.hahaton.Entities.DTO.ChannelDTO;
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
public class Channel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private Group group;
    @ManyToOne
    private AcademicSubject academicSubject;
    @ManyToOne
    private Teacher teacher;

    public Channel(ChannelDTO channelDTO,  Group group, AcademicSubject academicSubject){
        this.id = channelDTO.getId();
        this.group = group;
        this.academicSubject = academicSubject;
    }

    public Channel() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public AcademicSubject getAcademicSubject() {
        return academicSubject;
    }

    public void setAcademicSubject(AcademicSubject academicSubject) {
        this.academicSubject = academicSubject;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }
}
