package com.hahaton.hahaton.Entities.DTO;

import com.hahaton.hahaton.Entities.Teacher;
import com.hahaton.hahaton.Entities.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class TeacherDTO {

    private Long id;
    private Long user;

    public TeacherDTO(Teacher teacher){
        this.id = teacher.getId();
        this.user = teacher.getUser().getId();
    }

    public TeacherDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUser() {
        return user;
    }

    public void setUser(Long user) {
        this.user = user;
    }
}
