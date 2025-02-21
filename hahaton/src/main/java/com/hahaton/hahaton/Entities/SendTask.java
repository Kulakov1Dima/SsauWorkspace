package com.hahaton.hahaton.Entities;


import com.hahaton.hahaton.Entities.DTO.SendTaskDTO;
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
public class SendTask {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private Student student;
    @ManyToOne
    private PointInTask pointInTask;
    @ManyToOne
    private FileInRepository fileInRepository;

    public SendTask(SendTaskDTO sendTaskDTO, Student student, PointInTask pointInTask, FileInRepository fileInRepository){
        this.id = sendTaskDTO.getId();
        this.student = student;
        this.pointInTask = pointInTask;
        this.fileInRepository = fileInRepository;
    }

    public SendTask() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public PointInTask getPointInTask() {
        return pointInTask;
    }

    public void setPointInTask(PointInTask pointInTask) {
        this.pointInTask = pointInTask;
    }

    public FileInRepository getFileInRepository() {
        return fileInRepository;
    }

    public void setFileInRepository(FileInRepository fileInRepository) {
        this.fileInRepository = fileInRepository;
    }
}
