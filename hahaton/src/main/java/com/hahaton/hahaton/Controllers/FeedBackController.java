package com.hahaton.hahaton.Controllers;

import com.hahaton.hahaton.Entities.DTO.FeedBackDTO;
import com.hahaton.hahaton.Entities.DTO.SendTaskDTO;
import com.hahaton.hahaton.Entities.DTO.TaskDTO;
import com.hahaton.hahaton.Entities.SendTask;
import com.hahaton.hahaton.Services.ClassConverter;
import com.hahaton.hahaton.Services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/channel/{channelId}/task/{taskId}/sendTask/{sendId}/")
public class FeedBackController {

    @Autowired
    private TaskService taskService;
    @Autowired
    private ClassConverter classConverter;


    @GetMapping("/feedback")
    public ResponseEntity<?> getFeedback(@PathVariable(name = "sendId")Long id){
        SendTaskDTO sendTaskDTO = taskService.getSendTaskById(id);
        return ResponseEntity.status(HttpStatus.OK).body(taskService.getFeedBackFromSendTask(sendTaskDTO));
    }
    @PostMapping("/feedback")
    public ResponseEntity<?> acceptSendTask(@PathVariable(name = "sendId")Long id,
                                            @RequestBody FeedBackDTO feedBackDTO){
        SendTaskDTO sendTaskDTO = taskService.getSendTaskById(id);
        feedBackDTO.setSendTask(classConverter.sendTaskConverter(sendTaskDTO));
        return ResponseEntity.status(HttpStatus.CREATED).body(taskService.saveFeedback(feedBackDTO));
    }

    @PutMapping("/feedback/{feedId}")
    public ResponseEntity<?> putFeedback(@PathVariable(name = "feedId")Long id,@RequestBody FeedBackDTO feedBackDTO){
        feedBackDTO.setId(id);
        return ResponseEntity.status(HttpStatus.OK).body(taskService.updateFeedback(feedBackDTO));
    }

    @DeleteMapping("/feedback/{feedId}")
    public ResponseEntity<?> deleteFeedback(@PathVariable(name = "feedId")Long id){
        FeedBackDTO feedBackDTO = taskService.getFeedbackById(id);
        taskService.deleteFeedBack(feedBackDTO);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Объект успешно удален");
    }

}
