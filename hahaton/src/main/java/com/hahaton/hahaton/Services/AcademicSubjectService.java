package com.hahaton.hahaton.Services;

import com.hahaton.hahaton.Entities.AcademicSubject;
import com.hahaton.hahaton.Entities.DTO.AcademicSubjectDTO;
import com.hahaton.hahaton.Repositories.AcademicSubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AcademicSubjectService {

    @Autowired
    private ClassConverter classConverter;

    @Autowired
    private AcademicSubjectRepository academicSubjectRepository;
    @Autowired
    private UserService userService;

    public List<AcademicSubjectDTO> getAllSubject(){
        List<AcademicSubjectDTO> academicSubjectDTOList = new ArrayList<>();
        Iterable<AcademicSubject> academicSubjectIterable = academicSubjectRepository.findAll();
        for(AcademicSubject academicSubject: academicSubjectIterable){
            academicSubjectDTOList.add(new AcademicSubjectDTO(academicSubject));
        }
        return academicSubjectDTOList;
    }

    public AcademicSubjectDTO getSubjectByIdDTO(Long id){
        AcademicSubject academicSubject = academicSubjectRepository.findById(id).get();
        return new AcademicSubjectDTO(academicSubject);
    }

    public AcademicSubject getSubjectById(Long id){
        AcademicSubject academicSubject = academicSubjectRepository.findById(id).get();
        return academicSubject;
    }

    public AcademicSubjectDTO saveSubject(AcademicSubjectDTO academicSubjectDTO){
        AcademicSubject academicSubject = classConverter.academicSubjectConverter(academicSubjectDTO);
        return new AcademicSubjectDTO(academicSubjectRepository.save(academicSubject));
    }

    public AcademicSubjectDTO updateSubject(AcademicSubjectDTO newSubjectDTO){
        AcademicSubject academicSubject = classConverter.academicSubjectConverter(newSubjectDTO);
        return new AcademicSubjectDTO(academicSubjectRepository.save(academicSubject));
    }

    public void deleteSubject(AcademicSubjectDTO academicSubjectDTO){
        AcademicSubject academicSubject = academicSubjectRepository.findById(academicSubjectDTO.getId()).get();
        academicSubjectRepository.delete(academicSubject);
    }
}
