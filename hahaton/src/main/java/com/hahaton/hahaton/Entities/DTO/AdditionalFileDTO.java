package com.hahaton.hahaton.Entities.DTO;

import com.hahaton.hahaton.Entities.AdditionalFile;
import com.hahaton.hahaton.Entities.FileInRepository;
import com.hahaton.hahaton.Entities.Task;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class AdditionalFileDTO {

    private Long id;
    private Long fileInRepository;
    private Long task;

    public AdditionalFileDTO(AdditionalFile additionalFile){
        this.id = additionalFile.getId();
        this.fileInRepository = additionalFile.getFileInRepository().getId();
        this.task = additionalFile.getTask().getId();
    }

    public AdditionalFileDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getFileInRepository() {
        return fileInRepository;
    }

    public void setFileInRepository(Long fileInRepository) {
        this.fileInRepository = fileInRepository;
    }

    public Long getTask() {
        return task;
    }

    public void setTask(Long task) {
        this.task = task;
    }
}
