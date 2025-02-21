package com.hahaton.hahaton.Entities;


import com.hahaton.hahaton.Entities.DTO.PointInTaskDTO;
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
public class PointInTask {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private TypeWorkClass typeWorkClass;
    @ManyToOne
    private Task task;

    public PointInTask(PointInTaskDTO pointInTaskDTO, TypeWorkClass typeWorkClass, Task task){
        this.id = pointInTaskDTO.getId();
        this.typeWorkClass = typeWorkClass;
        this.task = task;
    }

    public PointInTask() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TypeWorkClass getTypeWorkClass() {
        return typeWorkClass;
    }

    public void setTypeWorkClass(TypeWorkClass typeWorkClass) {
        this.typeWorkClass = typeWorkClass;
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }
}
