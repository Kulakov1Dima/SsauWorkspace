package com.hahaton.hahaton.Services;

import com.hahaton.hahaton.Entities.DTO.*;
import com.hahaton.hahaton.Entities.FileInRepository;
import com.hahaton.hahaton.Repositories.AdditionalFileRepository;
import com.hahaton.hahaton.Repositories.FileInRepositoryRepository;
import com.hahaton.hahaton.Repositories.SendTaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class FileService {

    @Autowired
    private ClassConverter classConverter;
    @Autowired
    private FileInRepositoryRepository fileInRepositoryRepository;

    public FileInRepositoryDTO getFileInRepoById(Long id){
        FileInRepository fileInRepository = fileInRepositoryRepository.findById(id).get();
        return new FileInRepositoryDTO(fileInRepository);
    }

    public List<FileInRepositoryDTO> getAllFileInRepository(){
        List<FileInRepositoryDTO> fileInRepositoryDTOList = new ArrayList<>();
        Iterable<FileInRepository> fileInRepositoryIterable = fileInRepositoryRepository.findAll();
        for(FileInRepository fileInRepository: fileInRepositoryIterable){
            fileInRepositoryDTOList.add(classConverter.fileInRepositoryConverter(fileInRepository));
        }
        return fileInRepositoryDTOList;
    }

    public FileInRepositoryDTO saveFileInRepo(FileInRepositoryDTO fileInRepositoryDTO){
        FileInRepository fileInRepository = classConverter.fileInRepositoryConverter(fileInRepositoryDTO);
        return new FileInRepositoryDTO(fileInRepositoryRepository.save(fileInRepository));
    }

    public FileInRepositoryDTO updateFileInRepo(FileInRepositoryDTO newFileInRepo){
        FileInRepository fileInRepository = fileInRepositoryRepository.findById(newFileInRepo.getId()).get();
        fileInRepository.setDirectory(newFileInRepo.getDirectory());
        return new FileInRepositoryDTO(fileInRepositoryRepository.save(fileInRepository));
    }

    public void deleteFileInRepo(FileInRepositoryDTO fileInRepositoryDTO){
        FileInRepository fileInRepository = fileInRepositoryRepository.findById(fileInRepositoryDTO.getId()).get();
        fileInRepositoryRepository.delete(fileInRepository);
    }
}
