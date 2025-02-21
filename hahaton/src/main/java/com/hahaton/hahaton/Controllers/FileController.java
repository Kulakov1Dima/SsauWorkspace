package com.hahaton.hahaton.Controllers;

import com.hahaton.hahaton.Entities.DTO.*;
import com.hahaton.hahaton.Entities.Task;
import com.hahaton.hahaton.Services.ClassConverter;
import com.hahaton.hahaton.Services.FileService;
import com.hahaton.hahaton.Services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class FileController {

    @Autowired
    private FileService fileService;
    @Autowired
    private TaskService taskService;
    @Autowired
    private ClassConverter classConverter;

    @GetMapping("/channel/{channelId}/task/{taskId}/file")
    public ResponseEntity<?> getAllFile(@PathVariable(name = "taskId")Long id){
        TaskDTO taskDTO = taskService.getTaskById(id);
        return ResponseEntity.status(HttpStatus.OK).body(taskService.getAllAdditionalFileFromTask(taskDTO));
    }
    @GetMapping("/channel/{channelId}/task/{taskId}/file/{fileId}")
    public ResponseEntity<?> getFile(@PathVariable(name = "fileId")Long id){
        AdditionalFileDTO additionalFileDTO = taskService.getAdditionalFileById(id);
        return ResponseEntity.status(HttpStatus.OK).body(additionalFileDTO);
    }
    @PostMapping("/channel/{channelId}/task/{taskId}/file")
    public ResponseEntity<?> postFile(@PathVariable(name = "taskId")Long id,@RequestBody AdditionalFileDTO additionalFileDTO){
        TaskDTO taskDTO = taskService.getTaskById(id);
        additionalFileDTO.setTask(id);
        return ResponseEntity.status(HttpStatus.CREATED).body(taskService.saveAdditionalFile(additionalFileDTO));
    }
    @PutMapping("/channel/{channelId}/task/{taskId}/file/{fileId}")
    public ResponseEntity<?> putFile(@PathVariable(name = "fileId")Long id,@RequestBody AdditionalFileDTO additionalFileDTO){
        additionalFileDTO.setId(id);
        return ResponseEntity.status(HttpStatus.OK).body(taskService.updateAdditionalFile(additionalFileDTO));
    }
    @DeleteMapping("/channel/{channelId}/task/{taskId}/file/{fileId}")
    public ResponseEntity<?> deleteFile(@PathVariable(name = "fileId")Long id){
        AdditionalFileDTO additionalFileDTO = taskService.getAdditionalFileById(id);
        taskService.deleteAdditionalFile(additionalFileDTO);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Объект успешно удален");
    }

    //SendTask
    @GetMapping("/channel/{channelId}/task/{taskId}/sendTask")
    public ResponseEntity<?> getAllSendTask(@PathVariable(name = "taskId")Long id){
        TaskDTO taskDTO = taskService.getTaskById(id);
        return ResponseEntity.status(HttpStatus.OK).body(taskService.getAllSendTaskByTask(taskDTO));
    }
    @GetMapping("/channel/{channelId}/task/{taskId}/point/{pointId}/sendTask/{sendId}")
    public ResponseEntity<?> getSendTask(@PathVariable(name = "sendId")Long id){
        return ResponseEntity.status(HttpStatus.OK).body(taskService.getSendTaskById(id));
    }

    @GetMapping("/channel/{channelId}/task/{taskId}/point/{pointId}/sendTask")
    public ResponseEntity<?> getAllSendTaskByPoint(@PathVariable(name = "pointId")Long id){
        PointInTaskDTO pointInTaskDTO = taskService.getPointById(id);
        return ResponseEntity.status(HttpStatus.OK).body(taskService.getAllSendTaskByPoint(pointInTaskDTO));
    }

    @DeleteMapping("/channel/{channelId}/task/{taskId}/point/{pointId}sendTask/{sendId}")
    public ResponseEntity<?> deleteSendTask(@PathVariable(name = "sendId")Long id){
        SendTaskDTO sendTaskDTO = taskService.getSendTaskById(id);
        taskService.deleteSendTask(sendTaskDTO);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Объект успешно удален");
    }
    @PutMapping("/channel/{channelId}/task/{taskId}/sendTask/{sendId}")
    public ResponseEntity<?> putSendTask(@PathVariable(name = "sendId")Long id,@RequestBody SendTaskDTO sendTaskDTO){
        sendTaskDTO.setId(id);

        return ResponseEntity.status(HttpStatus.OK).body(taskService.updateSendTask(sendTaskDTO));
    }

    //POINT IN TASK

    @GetMapping("/channel/{channelId}/task/{taskId}/point")
    public ResponseEntity<?> getAllPoint(@PathVariable(name = "taskId")Long id){
        TaskDTO taskDTO = taskService.getTaskById(id);
        return ResponseEntity.status(HttpStatus.OK).body(taskService.getAllPointInTaskByTask(taskDTO));
    }

    @GetMapping("/channel/{channelId}/task/{taskId}/point/{pointId}")
    public ResponseEntity<?> getPointById(@PathVariable(name = "pointId")Long id){
        return ResponseEntity.status(HttpStatus.OK).body(taskService.getPointById(id));
    }

    @PostMapping("/channel/{channelId}/task/{taskId}/point")
    public ResponseEntity<?> postPointInTask(@PathVariable(name = "taskId")Long id,@RequestBody PointInTaskDTO pointInTaskDTO){
        TaskDTO taskDTO = taskService.getTaskById(id);
        pointInTaskDTO.setTask(id);
        return ResponseEntity.status(HttpStatus.CREATED).body(taskService.savePointInTask(pointInTaskDTO));
    }

    //FILE

    @GetMapping("/file")
    public ResponseEntity<?> getAllFile(){
        return ResponseEntity.status(HttpStatus.OK).body(fileService.getAllFileInRepository());
    }

    @PostMapping("/file")
    public ResponseEntity<?> postFile(@RequestBody FileInRepositoryDTO fileInRepositoryDTO){
        return ResponseEntity.status(HttpStatus.CREATED).body(fileService.saveFileInRepo(fileInRepositoryDTO));
    }


}
