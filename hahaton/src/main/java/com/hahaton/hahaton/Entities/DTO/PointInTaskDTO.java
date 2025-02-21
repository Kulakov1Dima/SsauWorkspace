package com.hahaton.hahaton.Entities.DTO;

import com.hahaton.hahaton.Entities.PointInTask;
import com.hahaton.hahaton.Entities.Task;
import com.hahaton.hahaton.Entities.TypeWorkClass;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PointInTaskDTO {

    private Long id;
    private Long typeWorkClass;
    private Long task;

    public PointInTaskDTO(PointInTask pointInTask){
        this.id = pointInTask.getId();
        this.typeWorkClass = pointInTask.getTypeWorkClass().getId();
        this.task = pointInTask.getTask().getId();
    }

    public PointInTaskDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getTypeWorkClass() {
        return typeWorkClass;
    }

    public void setTypeWorkClass(Long typeWorkClass) {
        this.typeWorkClass = typeWorkClass;
    }

    public Long getTask() {
        return task;
    }

    public void setTask(Long task) {
        this.task = task;
    }
}
