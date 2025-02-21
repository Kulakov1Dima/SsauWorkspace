package com.hahaton.hahaton.Entities.DTO;

import com.hahaton.hahaton.Entities.FileInRepository;
import com.hahaton.hahaton.Entities.PointInTask;
import com.hahaton.hahaton.Entities.SendTask;
import com.hahaton.hahaton.Entities.Student;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class SendTaskDTO {

    private Long id;
    private Long student;
    private Long pointInTask;
    private Long fileInRepository;

    public SendTaskDTO(SendTask sendTask){
        this.id = sendTask.getId();
        this.student = sendTask.getStudent().getId();
        this.pointInTask = sendTask.getPointInTask().getId();
        this.fileInRepository = sendTask.getFileInRepository().getId();
    }

    public SendTaskDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getStudent() {
        return student;
    }

    public void setStudent(Long student) {
        this.student = student;
    }

    public Long getPointInTask() {
        return pointInTask;
    }

    public void setPointInTask(Long pointInTask) {
        this.pointInTask = pointInTask;
    }

    public Long getFileInRepository() {
        return fileInRepository;
    }

    public void setFileInRepository(Long fileInRepository) {
        this.fileInRepository = fileInRepository;
    }
}
