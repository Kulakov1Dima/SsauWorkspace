package com.hahaton.hahaton.Controllers;

import com.hahaton.hahaton.Entities.DTO.CommentaryDTO;
import com.hahaton.hahaton.Entities.DTO.MessageDTO;
import com.hahaton.hahaton.Entities.Message;
import com.hahaton.hahaton.Services.ClassConverter;
import com.hahaton.hahaton.Services.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/channel/{channelId}/message/{messageId}/commentary")
public class CommentaryController {

    @Autowired
    private MessageService messageService;
    @Autowired
    private ClassConverter classConverter;
    @GetMapping()
    public ResponseEntity<?> getCommentary(@PathVariable(name = "messageId")Long id){
        MessageDTO messageDTO = messageService.getMessageById(id);
        return ResponseEntity.status(HttpStatus.OK).body(messageService.getAllCommentaryFromMessage(messageDTO));
    }
    @PostMapping()
    public ResponseEntity<?> postCommentary(@PathVariable(name = "messageId")Long id,@RequestBody CommentaryDTO commentaryDTO){
        MessageDTO messageDTO = messageService.getMessageById(id);
        commentaryDTO.setMessage(id);
        return ResponseEntity.status(HttpStatus.CREATED).body(messageService.saveCommentary(commentaryDTO));
    }
    @PutMapping("/{commentaryId}")
    public ResponseEntity<?> putCommentary(@PathVariable(name = "commentaryId")Long id,@RequestBody CommentaryDTO commentaryDTO){
        commentaryDTO.setId(id);
        return ResponseEntity.status(HttpStatus.OK).body(messageService.updateCommentary(commentaryDTO));
    }
    @DeleteMapping("/{commentaryId}")
    public ResponseEntity<?> deleteCommentary(@PathVariable(name = "commentaryId")Long id){
        CommentaryDTO commentaryDTO = messageService.getCommentaryById(id);
        messageService.deleteCommentary(commentaryDTO);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Объект успешно удален");
    }
}
