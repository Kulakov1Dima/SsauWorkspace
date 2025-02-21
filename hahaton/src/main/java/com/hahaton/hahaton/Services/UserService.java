package com.hahaton.hahaton.Services;

import com.hahaton.hahaton.Entities.DTO.GroupDTO;
import com.hahaton.hahaton.Entities.DTO.StudentDTO;
import com.hahaton.hahaton.Entities.DTO.TeacherDTO;
import com.hahaton.hahaton.Entities.DTO.UserDTO;
import com.hahaton.hahaton.Entities.Group;
import com.hahaton.hahaton.Entities.Student;
import com.hahaton.hahaton.Entities.Teacher;
import com.hahaton.hahaton.Entities.User;
import com.hahaton.hahaton.Repositories.GroupRepository;
import com.hahaton.hahaton.Repositories.StudentRepository;
import com.hahaton.hahaton.Repositories.TeacherRepository;
import com.hahaton.hahaton.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private TeacherRepository teacherRepository;
    @Autowired
    private GroupRepository groupRepository;
    @Autowired
    private ClassConverter classConverter;

    public List<UserDTO> getAllUser(){
        List<UserDTO> userDTOList = new ArrayList<>();
        Iterable<User> userIterable = userRepository.findAll();
        for(User user: userIterable){
            userDTOList.add(new UserDTO(user));
        }
        return userDTOList;
    }

    public UserDTO getUserByIdDTO(Long id){
        if(userRepository.existsById(id)){
            User user = userRepository.findById(id).get();
            return new UserDTO(user);
        }
        return null;
    }

    public User getUserById(Long id){
        if(userRepository.existsById(id)){
            return userRepository.findById(id).get();
        }
        return null;
    }

    public UserDTO saveUser(UserDTO userDTO){
        User user = classConverter.userConverter(userDTO);
        user = userRepository.save(user);
        return new UserDTO(user);
    }

    public UserDTO updateUser(UserDTO newUser){
        if(!userRepository.existsById(newUser.getId()))
            return null;
        User user = userRepository.findById(newUser.getId()).get();
        user.setLogin(newUser.getLogin());
        user.setPassword(newUser.getPassword());
        user.setName(newUser.getName());
        user.setSurname(newUser.getSurname());
        user.setMiddleName(newUser.getMiddleName());
        return new UserDTO(userRepository.save(user));
    }

    public void deleteUser(UserDTO userDTO){
        if(userRepository.existsById(userDTO.getId())){
            User user = userRepository.findById(userDTO.getId()).get();
            userRepository.delete(user);
        }
    }

    public StudentDTO getStudentByIdDTO(Long id){
        if(!studentRepository.existsById(id))
            return null;
        return new StudentDTO(studentRepository.findById(id).get());
    }

    public Student getStudentById(Long id){
        if(!studentRepository.existsById(id))
            return null;
        return studentRepository.findById(id).get();
    }

    public List<StudentDTO> getAllStudent(){
        List<StudentDTO> studentDTOList = new ArrayList<>();
        Iterable<Student> studentIterable = studentRepository.findAll();
        for(Student student: studentIterable){
            studentDTOList.add(new StudentDTO(student));
        }
        return studentDTOList;
    }

    public StudentDTO saveStudent(StudentDTO studentDTO){
        Group group = groupRepository.findById(studentDTO.getGroup()).get();
        Student student = classConverter.studentConverter(studentDTO);
        studentRepository.save(student);
        return studentDTO;
    }

    public StudentDTO updateStudent(StudentDTO newStudentDTO){
        if(!studentRepository.existsById(newStudentDTO.getId()))
            return null;
        Student student = studentRepository.findById(newStudentDTO.getId()).get();
        student.setGroup(groupRepository.findById(newStudentDTO.getGroup()).get());
        student.setUser(getUserById(newStudentDTO.getUser()));
        return new StudentDTO(studentRepository.save(student));
    }

    public void deleteStudent(StudentDTO studentDTO){
        if(studentRepository.existsById(studentDTO.getId())){
            Student student = studentRepository.findById(studentDTO.getId()).get();
            studentRepository.delete(student);
        }

    }

    public List<TeacherDTO> getAllTeacher(){
        List<TeacherDTO> teacherDTOList = new ArrayList<>();
        Iterable<Teacher> teacherIterable = teacherRepository.findAll();
        for(Teacher teacher: teacherIterable){
            teacherDTOList.add(new TeacherDTO(teacher));
        }
        return teacherDTOList;
    }

    public TeacherDTO getTeacherByIdDTO(Long id){
        if(!teacherRepository.existsById(id))
            return null;
        return new TeacherDTO(teacherRepository.findById(id).get());
    }

    public Teacher getTeacherById(Long id){
        if(!teacherRepository.existsById(id))
            return null;
        return teacherRepository.findById(id).get();
    }

    public TeacherDTO saveTeacher(TeacherDTO teacherDTO){
        Teacher teacher = classConverter.teacherConverter(teacherDTO);
        return new TeacherDTO(teacherRepository.save(teacher));
    }

    public TeacherDTO updateTeacher(TeacherDTO newTeacherDTO){
        if(!teacherRepository.existsById(newTeacherDTO.getId()))
            return null;
        Teacher teacher = teacherRepository.findById(newTeacherDTO.getId()).get();
        teacher.setId(newTeacherDTO.getId());
        teacher.setUser(getUserById(newTeacherDTO.getUser()));
        return new TeacherDTO(teacherRepository.save(teacher));
    }

    public void deleteTeacher(TeacherDTO teacherDTO){
        if(teacherRepository.existsById(teacherDTO.getId())){
            Teacher teacher = teacherRepository.findById(teacherDTO.getId()).get();
            teacherRepository.delete(teacher);
        }
    }
}
