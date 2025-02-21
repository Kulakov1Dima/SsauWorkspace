package com.hahaton.hahaton.Entities.DTO;

import com.hahaton.hahaton.Entities.Group;
import com.hahaton.hahaton.Entities.Student;
import com.hahaton.hahaton.Entities.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class StudentDTO {

    private Long id;
    private Long group;
    private Long user;

    public StudentDTO(Student student){
        this.id = student.getId();
        this.group = student.getGroup().getId();
        this.user = student.getUser().getId();
    }

    public StudentDTO() {
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

    public Long getUser() {
        return user;
    }

    public void setUser(Long user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "StudentDTO{" +
                "id=" + id +
                ", group=" + group +
                ", user=" + user +
                '}';
    }
}
