package com.hahaton.hahaton.Controllers;

import com.hahaton.hahaton.Entities.Channel;
import com.hahaton.hahaton.Entities.DTO.ChannelDTO;
import com.hahaton.hahaton.Entities.DTO.TaskDTO;
import com.hahaton.hahaton.Services.ChannelService;
import com.hahaton.hahaton.Services.ClassConverter;
import com.hahaton.hahaton.Services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/channel/{channelId}/task")
public class TaskController {

    @Autowired
    private TaskService taskService;
    @Autowired
    private ChannelService channelService;
    @Autowired
    private ClassConverter classConverter;

    @GetMapping()
    public ResponseEntity<?> getAllTask(@PathVariable(name = "channelId")Long id){
        ChannelDTO channelDTO = channelService.getChannelById(id);
        return ResponseEntity.status(HttpStatus.OK).body(taskService.getAllTaskFromChannel(channelDTO));
    }
    @GetMapping("/{taskId}")
    public ResponseEntity<?> getTask(@PathVariable(name = "taskId")Long id){
        return ResponseEntity.status(HttpStatus.OK).body(taskService.getTaskById(id));
    }
    @PostMapping()
    public ResponseEntity<?> postTask(@PathVariable(name = "channelId")Long id,@RequestBody TaskDTO taskDTO){
        ChannelDTO channelDTO = channelService.getChannelById(id);
        taskDTO.setChanel(id);
        return ResponseEntity.status(HttpStatus.CREATED).body(taskService.saveTask(taskDTO));
    }
    @PutMapping("/{taskId}")
    public ResponseEntity<?> putTask(@PathVariable(name = "taskId")Long id,@RequestBody TaskDTO taskDTO){
        taskDTO.setId(id);
        return ResponseEntity.status(HttpStatus.OK).body(taskService.updateTask(taskDTO));
    }
    @DeleteMapping("/{taskId}")
    public ResponseEntity<?> deleteTask(@PathVariable(name = "taskId")Long id){
        TaskDTO taskDTO = taskService.getTaskById(id);
        taskService.deleteTask(taskDTO);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Объект успешно удален");
    }
}

