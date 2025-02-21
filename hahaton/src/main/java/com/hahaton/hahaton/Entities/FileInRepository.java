package com.hahaton.hahaton.Entities;

import com.hahaton.hahaton.Entities.DTO.FileInRepositoryDTO;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class FileInRepository {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String directory;

    public FileInRepository(FileInRepositoryDTO fileInRepositoryDTO){
        this.id = fileInRepositoryDTO.getId();
        this.directory = fileInRepositoryDTO.getDirectory();
    }

    public FileInRepository() {
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
