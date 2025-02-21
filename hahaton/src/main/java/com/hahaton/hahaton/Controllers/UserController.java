package com.hahaton.hahaton.Controllers;

import com.hahaton.hahaton.Entities.DTO.StudentDTO;
import com.hahaton.hahaton.Entities.DTO.TeacherDTO;
import com.hahaton.hahaton.Entities.DTO.UserDTO;
import com.hahaton.hahaton.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping()
    public ResponseEntity<?> getAllUser(){
        return ResponseEntity.status(HttpStatus.OK).body(userService.getAllUser());
    }

    @GetMapping("/{userId}")
    public ResponseEntity<?> getUser(@PathVariable(name = "userId")Long id){
        return ResponseEntity.status(HttpStatus.OK).body(userService.getUserById(id));
    }

    @GetMapping("/student")
    public ResponseEntity<?> getAllStudents(){
        return ResponseEntity.status(HttpStatus.OK).body(userService.getAllStudent());
    }

    @GetMapping("/student/{studentId}")
    public ResponseEntity<?> getStudent(@PathVariable(name = "studentId")Long id){
        return ResponseEntity.status(HttpStatus.OK).body(userService.getStudentByIdDTO(id));
    }

    @GetMapping("/teacher")
    public ResponseEntity<?> getAllTeachers(){
        return ResponseEntity.status(HttpStatus.OK).body(userService.getAllTeacher());
    }

    @GetMapping("/teacher/{teacherId}")
    public ResponseEntity<?> getTeacher(@PathVariable(name = "teacherId")Long id){
        return ResponseEntity.status(HttpStatus.OK).body(userService.getTeacherByIdDTO(id));
    }

    @PostMapping()
    public ResponseEntity<?> postUser(@RequestBody UserDTO userDTO){
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.saveUser(userDTO));
    }

    @PostMapping("/student")
    public ResponseEntity<?> postStudent(@RequestBody StudentDTO studentDTO){
        System.out.println("!!!" + studentDTO);
        studentDTO = userService.saveStudent(studentDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(studentDTO);
    }
    @PostMapping("/teacher")
    public ResponseEntity<?> postTeacher(@RequestBody TeacherDTO teacherDTO){
        teacherDTO = userService.saveTeacher(teacherDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(teacherDTO);
    }
    @PutMapping("/student/{studentId}")
    public ResponseEntity<?> putStudent(@PathVariable(name = "studentId")Long id, @RequestBody StudentDTO studentDTO){
        studentDTO.setId(id);
        studentDTO = userService.updateStudent(studentDTO);
        return ResponseEntity.status(HttpStatus.OK).body(studentDTO);
    }
    @PutMapping("/teacher/{teacherId}")
    public ResponseEntity<?> putTeacher(@PathVariable(name = "teacherId")Long id, @RequestBody TeacherDTO teacherDTO){
        teacherDTO.setId(id);
        teacherDTO = userService.updateTeacher(teacherDTO);
        return ResponseEntity.status(HttpStatus.OK).body(teacherDTO);
    }
    @DeleteMapping("/student/{studentId}")
    public ResponseEntity<?> deleteStudent(@PathVariable(name = "studentId")Long id){
        StudentDTO studentDTO = userService.getStudentByIdDTO(id);
        userService.deleteStudent(studentDTO);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Объект успешно удален");
    }
    @DeleteMapping("/teacher/{teacherId}")
    public ResponseEntity<?> deleteTeacher(@PathVariable(name = "teacherId")Long id){
        TeacherDTO teacherDTO = userService.getTeacherByIdDTO(id);
        userService.deleteTeacher(teacherDTO);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Объект успешно удален");
    }
}
