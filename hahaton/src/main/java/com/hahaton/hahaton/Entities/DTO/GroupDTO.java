package com.hahaton.hahaton.Entities.DTO;

import com.hahaton.hahaton.Entities.Group;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@AllArgsConstructor
@Getter
@Setter
public class GroupDTO {

    private Long id;
    private String name;

    public GroupDTO(Group group){
        this.id = group.getId();
        this.name = group.getName();
    }

    public GroupDTO() {
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
}
