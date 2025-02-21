package com.hahaton.hahaton.Controllers;

import com.hahaton.hahaton.Entities.DTO.AcademicSubjectDTO;
import com.hahaton.hahaton.Services.AcademicSubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/academic")
public class AcademicSubjectController {

    @Autowired
    private AcademicSubjectService academicSubjectService;

    @GetMapping()
    public ResponseEntity<?> getAllSubject(){
        return ResponseEntity.status(HttpStatus.OK).body(academicSubjectService.getAllSubject());
    }

    @GetMapping("/{subjectId}")
    public ResponseEntity<?> getSubject(@PathVariable(name = "subjectId")Long id){
        return ResponseEntity.status(HttpStatus.OK).body(academicSubjectService.getSubjectByIdDTO(id));
    }

    @PostMapping()
    public ResponseEntity<?> postSubject(@RequestBody AcademicSubjectDTO academicSubjectDTO){
        academicSubjectDTO = academicSubjectService.saveSubject(academicSubjectDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(academicSubjectDTO);
    }

    @PutMapping("/{subjectId}")
    public ResponseEntity<?> putSubject(@PathVariable(name = "subjectId")Long id,@RequestBody AcademicSubjectDTO academicSubjectDTO){
        academicSubjectDTO.setId(id);
        academicSubjectDTO = academicSubjectService.updateSubject(academicSubjectDTO);
        return ResponseEntity.status(HttpStatus.OK).body(academicSubjectDTO);
    }
    @DeleteMapping("/{subjectId}")
    public ResponseEntity<?> deleteSubject(@PathVariable(name = "subjectId")Long id){
        AcademicSubjectDTO academicSubjectDTO = academicSubjectService.getSubjectByIdDTO(id);
        academicSubjectService.deleteSubject(academicSubjectDTO);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Объект успешно удален");
    }
}
