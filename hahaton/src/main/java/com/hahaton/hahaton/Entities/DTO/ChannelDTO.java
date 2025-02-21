package com.hahaton.hahaton.Entities.DTO;

import com.hahaton.hahaton.Entities.AcademicSubject;
import com.hahaton.hahaton.Entities.Channel;
import com.hahaton.hahaton.Entities.Group;
import com.hahaton.hahaton.Entities.Teacher;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ChannelDTO {

    private Long id;
    private Long group;
    private Long academicSubject;
    private Long teacher;

    public ChannelDTO(Channel channel){
        this.id = channel.getId();
        this.group = channel.getGroup().getId();
        this.academicSubject = channel.getAcademicSubject().getId();
    }

    public ChannelDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getGroup() {
        return group;
    }

    public void setGroup(Long group) {
        this.group = group;
    }

    public Long getAcademicSubject() {
        return academicSubject;
    }

    public void setAcademicSubject(Long academicSubject) {
        this.academicSubject = academicSubject;
    }

    public Long getTeacher() {
        return teacher;
    }

    public void setTeacher(Long teacher) {
        this.teacher = teacher;
    }

    @Override
    public String toString() {
        return "ChannelDTO{" +
                "id=" + id +
                ", group=" + group +
                ", academicSubject=" + academicSubject +
                ", teacher=" + teacher +
                '}';
    }
}
