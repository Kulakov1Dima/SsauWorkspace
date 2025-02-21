package com.hahaton.hahaton.Entities;


import com.hahaton.hahaton.Entities.DTO.AdditionalFileDTO;
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
public class AdditionalFile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private FileInRepository fileInRepository;
    @ManyToOne
    private Task task;

    public AdditionalFile(AdditionalFileDTO additionalFileDTO, FileInRepository fileInRepository, Task task){
        this.id = additionalFileDTO.getId();
        this.fileInRepository = fileInRepository;
        this.task = task;
    }

    public AdditionalFile() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public FileInRepository getFileInRepository() {
        return fileInRepository;
    }

    public void setFileInRepository(FileInRepository fileInRepository) {
        this.fileInRepository = fileInRepository;
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }
}
