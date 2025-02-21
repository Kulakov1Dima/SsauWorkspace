package com.hahaton.hahaton.Services;

import com.hahaton.hahaton.Entities.*;
import com.hahaton.hahaton.Entities.DTO.*;
import com.hahaton.hahaton.Repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClassConverter {

    @Autowired
    private CommentaryRepository commentaryRepository;
    @Autowired
    private AcademicSubjectRepository academicSubjectRepository;
    @Autowired
    private AdditionalFileRepository additionalFileRepository;
    @Autowired
    private ChannelRepository channelRepository;
    @Autowired
    private FeedBackRepository feedBackRepository;
    @Autowired
    private FileInRepositoryRepository fileInRepositoryRepository;
    @Autowired
    private GroupRepository groupRepository;
    @Autowired
    private MessageRepository messageRepository;
    @Autowired
    private PointInTaskRepository pointInTaskRepository;
    @Autowired
    private SendTaskRepository sendTaskRepository;
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private TaskRepository taskRepository;
    @Autowired
    private TeacherRepository teacherRepository;
    @Autowired
    private TypeWorkClassRepository typeWorkClassRepository;
    @Autowired
    private UserInChannelRepository userInChannelRepository;
    @Autowired
    private UserRepository userRepository;

    public AcademicSubjectDTO academicSubjectConverter(AcademicSubject academicSubject){
        return new AcademicSubjectDTO(academicSubject);
    }

    public AcademicSubject academicSubjectConverter(AcademicSubjectDTO academicSubjectDTO){
        if(academicSubjectDTO.getId() != null && academicSubjectRepository.existsById(academicSubjectDTO.getId()))
            return academicSubjectRepository.findById(academicSubjectDTO.getId()).get();
        AcademicSubject academicSubject = new AcademicSubject();
        academicSubject.setName(academicSubjectDTO.getName());
        academicSubject.setUrlToRoom(academicSubjectDTO.getUrlToRoom());
        academicSubject.setTeacher(teacherRepository.findById(academicSubjectDTO.getTeacher()).get());
        return academicSubject;
    }

    public AdditionalFileDTO additionalFileConverter(AdditionalFile additionalFile){
        return new AdditionalFileDTO(additionalFile);
    }

    public AdditionalFile additionalFileConverter(AdditionalFileDTO additionalFileDTO){
        if(additionalFileDTO.getId() != null &&additionalFileRepository.existsById(additionalFileDTO.getId()))
            return additionalFileRepository.findById(additionalFileDTO.getId()).get();
        AdditionalFile additionalFile = new AdditionalFile();
        additionalFile.setTask(taskRepository.findById(additionalFileDTO.getTask()).get());
        additionalFile.setFileInRepository(fileInRepositoryRepository.findById(additionalFileDTO.getFileInRepository()).get());
        return additionalFile;
    }

    public ChannelDTO channelConverter(Channel channel){
        return new ChannelDTO(channel);
    }

    public Channel channelConverter(ChannelDTO channelDTO){
        if(channelDTO.getId() != null && channelRepository.existsById(channelDTO.getId()))
            return channelRepository.findById(channelDTO.getId()).get();
        Channel channel = new Channel();
        channel.setGroup(groupRepository.findById(channelDTO.getGroup()).get());
        channel.setAcademicSubject(academicSubjectRepository.findById(channelDTO.getAcademicSubject()).get());
        channel.setTeacher(teacherRepository.findById(channelDTO.getTeacher()).get());
        return channel;
    }

    public CommentaryDTO commentaryConverter(Commentary commentary){
        return new CommentaryDTO(commentary);
    }

    public Commentary commentaryConverter(CommentaryDTO commentaryDTO){
        if(commentaryDTO.getId() != null && commentaryRepository.existsById(commentaryDTO.getId()))
            return commentaryRepository.findById(commentaryDTO.getId()).get();
        Commentary commentary = new Commentary();
        commentary.setMessage(messageRepository.findById(commentaryDTO.getMessage()).get());
        commentary.setUser(userRepository.findById(commentaryDTO.getUser()).get());
        commentary.setText(commentaryDTO.getText());
        return commentary;
    }

    public FeedBackDTO feedBackConverter(FeedBack feedBack){
        return new FeedBackDTO(feedBack);
    }

    public FeedBack feedBackConverter(FeedBackDTO feedBackDTO){
        if(feedBackDTO.getId() != null && feedBackRepository.equals(feedBackDTO.getId()))
            return feedBackRepository.findById(feedBackDTO.getId()).get();
        FeedBack feedBack = new FeedBack();
        feedBack.setSendTask(sendTaskRepository.findById(feedBackDTO.getSendTask()).get());
        feedBack.setScore(feedBackDTO.getScore());
        feedBack.setText(feedBackDTO.getText());
        return feedBack;


    }

    public FileInRepositoryDTO fileInRepositoryConverter(FileInRepository fileInRepository){
        return new FileInRepositoryDTO(fileInRepository);
    }

    public FileInRepository fileInRepositoryConverter(FileInRepositoryDTO fileInRepositoryDTO){
        if(fileInRepositoryDTO.getId() != null && fileInRepositoryRepository.existsById(fileInRepositoryDTO.getId()))
            return fileInRepositoryRepository.findById(fileInRepositoryDTO.getId()).get();
        FileInRepository fileInRepository = new FileInRepository();
        fileInRepository.setDirectory(fileInRepository.getDirectory());
        return fileInRepository;
    }

    public GroupDTO groupConverter(Group group){
        return new GroupDTO(group);
    }

    public Group groupConverter(GroupDTO groupDTO){
        if(groupDTO.getId() != null && groupRepository.existsById(groupDTO.getId()))
            return groupRepository.findById(groupDTO.getId()).get();
        Group group = new Group();
        group.setName(groupDTO.getName());
        return group;
    }

    public MessageDTO messageConverter(Message message){
        return new MessageDTO(message);
    }

    public Message messageConverter(MessageDTO messageDTO){
        if(messageDTO.getId() != null && messageRepository.existsById(messageDTO.getId()))
            return messageRepository.findById(messageDTO.getId()).get();
        Message message = new Message();
        message.setChannel(channelRepository.findById(messageDTO.getChannel()).get());
        message.setUser(userRepository.findById(messageDTO.getUser()).get());
        message.setText(messageDTO.getText());
        return message;
    }

    public PointInTaskDTO pointInTaskConverter(PointInTask pointInTask){
        return new PointInTaskDTO(pointInTask);
    }

    public PointInTask pointInTaskConverter(PointInTaskDTO pointInTaskDTO){
        if(pointInTaskDTO.getId() != null && pointInTaskRepository.existsById(pointInTaskDTO.getId()))
            return pointInTaskRepository.findById(pointInTaskDTO.getId()).get();
        PointInTask pointInTask = new PointInTask();
        pointInTask.setTypeWorkClass(typeWorkClassRepository.findById(pointInTaskDTO.getTypeWorkClass()).get());
        pointInTask.setTask(taskRepository.findById(pointInTaskDTO.getTask()).get());
        return pointInTask;
    }

    public SendTaskDTO sendTaskConverter(SendTask sendTask){
        return new SendTaskDTO(sendTask);
    }

    public SendTask sendTaskConverter(SendTaskDTO sendTaskDTO){
        if (sendTaskDTO.getId() != null && sendTaskRepository.existsById(sendTaskDTO.getId()))
            return sendTaskRepository.findById(sendTaskDTO.getId()).get();
        SendTask sendTask = new SendTask();
        sendTask.setStudent(studentRepository.findById(sendTaskDTO.getStudent()).get());
        sendTask.setPointInTask(pointInTaskRepository.findById(sendTaskDTO.getPointInTask()).get());
        sendTask.setFileInRepository(fileInRepositoryRepository.findById(sendTaskDTO.getFileInRepository()).get());
        return sendTask;
    }

    public StudentDTO studentConverter(Student student){
        return new StudentDTO(student);
    }

    public Student studentConverter(StudentDTO studentDTO){
        if(studentDTO.getId() != null && studentRepository.existsById(studentDTO.getId()))
            return studentRepository.findById(studentDTO.getId()).get();
        Student student = new Student();
        student.setGroup(groupRepository.findById(studentDTO.getGroup()).get());
        student.setUser(userRepository.findById(studentDTO.getUser()).get());
        return student;
    }

    public TaskDTO taskConverter(Task task){
        return new TaskDTO(task);
    }

    public Task taskConverter(TaskDTO taskDTO){
        if(taskDTO.getId() != null && taskRepository.existsById(taskDTO.getId()))
            return taskRepository.findById(taskDTO.getId()).get();
        Task task = new Task();
        task.setDescription(taskDTO.getDescription());
        task.setChannel(channelRepository.findById(taskDTO.getChanel()).get());
        return task;
    }

    public TeacherDTO teacherConverter(Teacher teacher){
        return new TeacherDTO(teacher);
    }

    public Teacher teacherConverter(TeacherDTO teacherDTO){
        if(teacherDTO.getId() != null && teacherRepository.existsById(teacherDTO.getId()))
            return teacherRepository.findById(teacherDTO.getId()).get();
        Teacher teacher = new Teacher();
        teacher.setUser(userRepository.findById(teacherDTO.getUser()).get());
        return teacher;
    }

    public TypeWorkClassDTO typeWorkClassConverter(TypeWorkClass typeWorkClass){
        return new TypeWorkClassDTO(typeWorkClass);
    }

    public TypeWorkClass typeWorkClassConverter(TypeWorkClassDTO typeWorkClassDTO){
        if(typeWorkClassDTO.getId() != null && typeWorkClassRepository.existsById(typeWorkClassDTO.getId()))
            return typeWorkClassRepository.findById(typeWorkClassDTO.getId()).get();
        TypeWorkClass typeWorkClass = new TypeWorkClass();
        typeWorkClass.setTypeWork(typeWorkClassDTO.getTypeWorkEnum());
        return typeWorkClass;
    }

    public UserDTO userConverter(User user){
        return new UserDTO(user);
    }

    public User userConverter(UserDTO userDTO){
        if(userDTO.getId() != null && userRepository.existsById(userDTO.getId()))
            return userRepository.findById(userDTO.getId()).get();
        User user = new User();
        user.setLogin(userDTO.getLogin());
        user.setPassword(userDTO.getPassword());
        user.setName(userDTO.getName());
        user.setSurname(userDTO.getSurname());
        user.setMiddleName(userDTO.getMiddleName());
        return user;
    }

    public UserInChannelDTO userInChannelConverter(UserInChannel userInChannel){
        return new UserInChannelDTO(userInChannel);
    }

    public UserInChannel userInChannelConverter(UserInChannelDTO userInChannelDTO){
        if(userInChannelDTO.getId() != null && userInChannelRepository.existsById(userInChannelDTO.getId()))
            return userInChannelRepository.findById(userInChannelDTO.getId()).get();
        UserInChannel userInChannel = new UserInChannel();
        userInChannel.setChannel(channelRepository.findById(userInChannelDTO.getChanel()).get());
        userInChannel.setStudent(studentRepository.findById(userInChannelDTO.getStudent()).get());
        return userInChannel;
    }




}
