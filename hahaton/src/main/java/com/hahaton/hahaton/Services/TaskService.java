package com.hahaton.hahaton.Services;

import com.hahaton.hahaton.Entities.*;
import com.hahaton.hahaton.Entities.DTO.*;
import com.hahaton.hahaton.Repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class TaskService {

    @Autowired
    private ClassConverter classConverter;
    @Autowired
    private TaskRepository taskRepository;
    @Autowired
    private SendTaskRepository sendTaskRepository;
    @Autowired
    private PointInTaskRepository pointInTaskRepository;
    @Autowired
    private FeedBackRepository feedBackRepository;
    @Autowired
    private AdditionalFileRepository additionalFileRepository;
    @Autowired
    private ChannelRepository channelRepository;
    @Autowired
    private FileInRepositoryRepository fileInRepositoryRepository;
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private TypeWorkClassRepository typeWorkClassRepository;

    public List<TaskDTO> getAllTask(){
        List<TaskDTO> taskDTOList = new ArrayList<>();
        Iterable<Task> taskIterable = taskRepository.findAll();
        for(Task task: taskIterable){
            taskDTOList.add(new TaskDTO(task));
        }
        return taskDTOList;
    }

    public List<TaskDTO> getAllTaskFromChannel(ChannelDTO channelDTO){
        Channel channel = classConverter.channelConverter(channelDTO);
        List<TaskDTO> taskDTOList = new ArrayList<>();
        Iterable<Task> taskIterable = taskRepository.findAllByChannel(channel);
        for(Task task : taskIterable){
            taskDTOList.add(new TaskDTO(task));
        }
        return taskDTOList;
    }

    public TaskDTO getTaskById(Long id){
        if(taskRepository.existsById(id))
            return new TaskDTO(taskRepository.findById(id).get());
        return null;
    }

    public TaskDTO saveTask(TaskDTO taskDTO){
        Task task = classConverter.taskConverter(taskDTO);
        return new TaskDTO(taskRepository.save(task));
    }

    public TaskDTO updateTask(TaskDTO newTaskDTO){
        if(!taskRepository.existsById(newTaskDTO.getId()))
            return null;
        Task task = taskRepository.findById(newTaskDTO.getId()).get();
        task.setChannel(channelRepository.findById(newTaskDTO.getChanel()).get());
        task.setDescription(newTaskDTO.getDescription());
        return new TaskDTO(taskRepository.save(task));
    }
    public void deleteTask(TaskDTO taskDTO){
        if(taskRepository.existsById(taskDTO.getId())){
            Task task = taskRepository.findById(taskDTO.getId()).get();
            taskRepository.delete(task);
        }
    }

    //AdditionalFile

    public List<AdditionalFileDTO> getAllAdditionalFile(){
        List<AdditionalFileDTO> additionalFileDTOList = new ArrayList<>();
        Iterable<AdditionalFile> additionalFileIterable = additionalFileRepository.findAll();
        for(AdditionalFile additionalFile: additionalFileIterable){
            additionalFileDTOList.add(new AdditionalFileDTO(additionalFile));
        }
        return additionalFileDTOList;
    }

    public List<AdditionalFileDTO> getAllAdditionalFileFromTask(TaskDTO taskDTO){
        if(!taskRepository.existsById(taskDTO.getId()))
            return null;
        Task task = classConverter.taskConverter(taskDTO);
        List<AdditionalFileDTO> additionalFileDTOList = new ArrayList<>();
        Iterable<AdditionalFile> additionalFileIterable = additionalFileRepository.findAllByTask(task);
        for(AdditionalFile additionalFile: additionalFileIterable){
            additionalFileDTOList.add(new AdditionalFileDTO(additionalFile));
        }
        return additionalFileDTOList;
    }

    public AdditionalFileDTO getAdditionalFileById(Long id){
        if(!additionalFileRepository.existsById(id))
            return null;
        AdditionalFile additionalFile = additionalFileRepository.findById(id).get();
        return new AdditionalFileDTO(additionalFile);
    }

    public AdditionalFileDTO saveAdditionalFile(AdditionalFileDTO additionalFileDTO){
        AdditionalFile additionalFile = classConverter.additionalFileConverter(additionalFileDTO);

        return new AdditionalFileDTO(additionalFileRepository.save(additionalFile));
    }

    public List<AdditionalFileDTO> addAdditionalFile(AdditionalFileDTO additionalFileDTO, TaskDTO taskDTO){
        if(!taskRepository.existsById(taskDTO.getId()) || !additionalFileRepository.existsById(additionalFileDTO.getId()))
            return null;
        AdditionalFile additionalFile = classConverter.additionalFileConverter(additionalFileDTO);
        Task task = classConverter.taskConverter(taskDTO);
        additionalFile.setTask(task);
        additionalFileRepository.save(additionalFile);
        return getAllAdditionalFileFromTask(taskDTO);
    }

    public AdditionalFileDTO updateAdditionalFile(AdditionalFileDTO newAdditionalFileDTO){
        if(!additionalFileRepository.existsById(newAdditionalFileDTO.getId()))
            return null;
        AdditionalFile additionalFile = additionalFileRepository.findById(newAdditionalFileDTO.getId()).get();
        additionalFile.setTask(taskRepository.findById(newAdditionalFileDTO.getTask()).get());
        additionalFile.setFileInRepository(fileInRepositoryRepository.findById(newAdditionalFileDTO.getFileInRepository()).get());
        return new AdditionalFileDTO(additionalFileRepository.save(additionalFile));
    }

    public void deleteAdditionalFile(AdditionalFileDTO additionalFileDTO){
        if(additionalFileRepository.existsById(additionalFileDTO.getId())){
            AdditionalFile additionalFile = additionalFileRepository.findById(additionalFileDTO.getId()).get();
            additionalFileRepository.delete(additionalFile);
        }
    }

    //SendTask

    public SendTaskDTO getSendTaskById(Long id){
        if(!sendTaskRepository.existsById(id))
            return null;
        SendTask sendTask = sendTaskRepository.findById(id).get();
        return new SendTaskDTO(sendTask);
    }

    public Map<PointInTaskDTO,List<SendTaskDTO>> getAllSendTaskByTask(TaskDTO taskDTO){
        Map<PointInTaskDTO,List<SendTaskDTO>> pointInTaskDTOListMap = new HashMap<>();
        Iterable<PointInTask> pointInTaskIterable = pointInTaskRepository.findAllByTask(classConverter.taskConverter(taskDTO));
        for(PointInTask pointInTask: pointInTaskIterable){
            Iterable<SendTask> sendTaskIterable = sendTaskRepository.findAllByPointInTask(pointInTask);
            List<SendTaskDTO> sendTaskDTOList = new ArrayList<>();
            for(SendTask sendTask: sendTaskIterable){
                sendTaskDTOList.add(new SendTaskDTO(sendTask));
            }
            pointInTaskDTOListMap.put(new PointInTaskDTO(pointInTask),sendTaskDTOList);
        }
        return pointInTaskDTOListMap;
    }


    public Map<TaskDTO,List<SendTaskDTO>> getAllSendTaskByStudent(StudentDTO studentDTO){
        Student student = classConverter.studentConverter(studentDTO);
        Iterable<SendTask> sendTaskIterable = sendTaskRepository.findAllByStudent(student);
        Map<TaskDTO,List<SendTaskDTO>> taskDTOListMap = new HashMap<>();
        for(SendTask sendTask : sendTaskIterable){
            TaskDTO taskDTO = new TaskDTO(sendTask.getPointInTask().getTask());
            if(taskDTOListMap.get(taskDTO) != null){
                taskDTOListMap.get(taskDTO).add(new SendTaskDTO(sendTask));
            }
            else {
                List<SendTaskDTO> sendTaskDTOList = List.of(new SendTaskDTO(sendTask));
                taskDTOListMap.put(taskDTO,sendTaskDTOList);
            }
        }
        return taskDTOListMap;
    }

    public List<SendTaskDTO> getAllSendTaskByStudentAndTask(StudentDTO studentDTO, TaskDTO taskDTO){
        return getAllSendTaskByStudent(studentDTO).get(taskDTO);
    }

    public List<SendTaskDTO> getAllSendTaskByPoint(PointInTaskDTO pointInTaskDTO){
        List<SendTaskDTO>sendTaskDTOList = new ArrayList<>();
        Iterable<SendTask> sendTaskIterable = sendTaskRepository.findAllByPointInTask(classConverter.pointInTaskConverter(pointInTaskDTO));
        for(SendTask sendTask : sendTaskIterable){
            sendTaskDTOList.add(new SendTaskDTO(sendTask));
        }
        return sendTaskDTOList;
    }

    public SendTaskDTO saveSendTask(SendTaskDTO sendTaskDTO){
        SendTask sendTask = classConverter.sendTaskConverter(sendTaskDTO);
        return new SendTaskDTO(sendTaskRepository.save(sendTask));
    }

    public SendTaskDTO updateSendTask(SendTaskDTO newSendTaskDTO){
        if(!sendTaskRepository.existsById(newSendTaskDTO.getId()))
            return null;
        SendTask sendTask = sendTaskRepository.findById(newSendTaskDTO.getId()).get();
        sendTask.setPointInTask(pointInTaskRepository.findById(newSendTaskDTO.getPointInTask()).get());
        sendTask.setFileInRepository(fileInRepositoryRepository.findById(newSendTaskDTO.getFileInRepository()).get());
        sendTask.setStudent(studentRepository.findById(newSendTaskDTO.getStudent()).get());
        return new SendTaskDTO(sendTaskRepository.save(sendTask));
    }

    public void deleteSendTask(SendTaskDTO sendTaskDTO){
        if(sendTaskRepository.existsById(sendTaskDTO.getId())){
            SendTask sendTask = sendTaskRepository.findById(sendTaskDTO.getId()).get();
            sendTaskRepository.delete(sendTask);
        }
    }

    //Feedback

    public List<FeedBackDTO> getAllFeedback(){
        List<FeedBackDTO> feedBackDTOList = new ArrayList<>();
        Iterable<FeedBack> feedBackIterable = feedBackRepository.findAll();
        for(FeedBack feedBack : feedBackIterable){
            feedBackDTOList.add(new FeedBackDTO(feedBack));
        }
        return feedBackDTOList;
    }

    public FeedBackDTO getFeedBackFromSendTask(SendTaskDTO sendTaskDTO){
        return new FeedBackDTO(feedBackRepository.findBySendTask(classConverter.sendTaskConverter(sendTaskDTO)));
    }

    public Map<SendTaskDTO,List<FeedBackDTO>> getAllFeedbackByPoint(PointInTaskDTO pointInTaskDTO){
        Map<SendTaskDTO,List<FeedBackDTO>> sendTaskDTOListMap = new HashMap<>();
        PointInTask pointInTask = pointInTaskRepository.findById(pointInTaskDTO.getId()).get();
        Iterable<SendTask> sendTaskIterable = sendTaskRepository.findAllByPointInTask(pointInTask);
        for(SendTask sendTask: sendTaskIterable){
            Iterable<FeedBack> feedBackIterable = feedBackRepository.findAllBySendTask(sendTask);
            List<FeedBackDTO> feedBackDTOList = new ArrayList<>();
            for(FeedBack feedBack: feedBackIterable){
                feedBackDTOList.add(new FeedBackDTO(feedBack));
            }
            sendTaskDTOListMap.put(new SendTaskDTO(sendTask),feedBackDTOList);
        }
        return sendTaskDTOListMap;
    }

    public Map<SendTaskDTO,List<FeedBackDTO>> getAllFeedbackByStudentAndPoint(PointInTaskDTO pointInTaskDTO, StudentDTO studentDTO){
        Map<SendTaskDTO, List<FeedBackDTO>> sendTaskDTOListMap = new HashMap<>();
        PointInTask pointInTask = pointInTaskRepository.findById(pointInTaskDTO.getId()).get();
        Iterable<SendTask> sendTaskIterable = sendTaskRepository.findAllByStudentAndPointInTask(classConverter.studentConverter(studentDTO),pointInTask);
        for(SendTask sendTask: sendTaskIterable){
            Iterable<FeedBack> feedBackIterable = feedBackRepository.findAllBySendTask(sendTask);
            List<FeedBackDTO> feedBackDTOList = new ArrayList<>();
            for(FeedBack feedBack: feedBackIterable){
                feedBackDTOList.add(new FeedBackDTO(feedBack));
            }
            sendTaskDTOListMap.put(new SendTaskDTO(sendTask),feedBackDTOList);
        }
        return sendTaskDTOListMap;
    }


    public FeedBackDTO saveFeedback(FeedBackDTO feedBackDTO){
        FeedBack feedBack = classConverter.feedBackConverter(feedBackDTO);
        return new FeedBackDTO(feedBackRepository.save(feedBack));
    }

    public FeedBackDTO getFeedbackById(Long id){
        FeedBack feedBack = feedBackRepository.findById(id).get();
        return new FeedBackDTO(feedBack);
    }

    public FeedBackDTO updateFeedback(FeedBackDTO newFeedBackDTO){
        FeedBack feedBack = feedBackRepository.findById(newFeedBackDTO.getId()).get();
        feedBack.setSendTask(sendTaskRepository.findById(newFeedBackDTO.getSendTask()).get());
        feedBack.setText(newFeedBackDTO.getText());
        feedBack.setScore(newFeedBackDTO.getScore());
        return new FeedBackDTO(feedBackRepository.save(feedBack));
    }

    public void deleteFeedBack(FeedBackDTO feedBackDTO){
        FeedBack feedBack = feedBackRepository.findById(feedBackDTO.getId()).get();
        feedBackRepository.delete(feedBack);
    }

    //PointInTask

    public List<PointInTaskDTO> getAllPointInTaskByTask(TaskDTO taskDTO){
        return getAllPointInTask(classConverter.taskConverter(taskDTO));
    }

    public PointInTaskDTO getPointById(Long id){
        return new PointInTaskDTO(pointInTaskRepository.findById(id).get());
    }

    private List<PointInTaskDTO> getAllPointInTask(Task task){
        List<PointInTaskDTO> pointInTaskDTOList = new ArrayList<>();
        Iterable<PointInTask> pointInTaskIterable = pointInTaskRepository.findAllByTask(task);
        for(PointInTask pointInTask: pointInTaskIterable){
            pointInTaskDTOList.add(new PointInTaskDTO(pointInTask));
        }
        return pointInTaskDTOList;
    }

    public List<PointInTaskDTO> savePointInTask(PointInTaskDTO pointInTaskDTO){
        List<PointInTaskDTO> pointInTaskDTOList = new ArrayList<>();
        pointInTaskRepository.save(classConverter.pointInTaskConverter(pointInTaskDTO));
        return getAllPointInTask(taskRepository.findById(pointInTaskDTO.getTask()).get());
    }

    public List<PointInTaskDTO> deletePointInTask(PointInTaskDTO pointInTaskDTO){
        PointInTask pointInTask = pointInTaskRepository.findById(pointInTaskDTO.getId()).get();
        pointInTaskRepository.delete(pointInTask);
        return getAllPointInTask(pointInTask.getTask());
    }

    public List<PointInTaskDTO> updatePointInTask(PointInTaskDTO pointInTaskDTO){
        PointInTask pointInTask = pointInTaskRepository.findById(pointInTaskDTO.getId()).get();
        pointInTask.setTask(taskRepository.findById(pointInTaskDTO.getTask()).get());
        pointInTask.setTypeWorkClass(typeWorkClassRepository.findById(pointInTaskDTO.getTypeWorkClass()).get());
        pointInTaskRepository.save(pointInTask);
        return getAllPointInTask(pointInTask.getTask());
    }
}
