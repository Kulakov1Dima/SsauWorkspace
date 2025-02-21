package com.hahaton.hahaton.Entities.DTO;


import com.hahaton.hahaton.Entities.FileInRepository;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@AllArgsConstructor
@Getter
@Setter
public class FileInRepositoryDTO {

    private Long id;
    private String directory;

    public FileInRepositoryDTO(FileInRepository fileInRepository){
        this.id = fileInRepository.getId();
        this.directory = fileInRepository.getDirectory();
    }

    public FileInRepositoryDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDirectory() {
        return directory;
    }

    public void setDirectory(String directory) {
        this.directory = directory;
    }
}
