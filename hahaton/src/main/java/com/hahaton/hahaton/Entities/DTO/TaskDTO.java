package com.hahaton.hahaton.Entities.DTO;

import com.hahaton.hahaton.Entities.Channel;
import com.hahaton.hahaton.Entities.Task;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class TaskDTO {

    private Long id;
    private String description;
    private Long chanel;

    public TaskDTO(Task task){
        this.id = task.getId();
        this.description = task.getDescription();
        this.chanel = task.getChannel().getId();
    }

    public TaskDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getChanel() {
        return chanel;
    }

    public void setChanel(Long chanel) {
        this.chanel = chanel;
    }
}
