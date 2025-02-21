package com.hahaton.hahaton.Controllers;

import com.hahaton.hahaton.Entities.DTO.ChannelDTO;
import com.hahaton.hahaton.Entities.DTO.MessageDTO;
import com.hahaton.hahaton.Services.ChannelService;
import com.hahaton.hahaton.Services.ClassConverter;
import com.hahaton.hahaton.Services.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/channel/{channelId}/message")
public class MessageController {

    @Autowired
    private MessageService messageService;
    @Autowired
    private ChannelService channelService;
    @Autowired
    private ClassConverter classConverter;

    @GetMapping()
    public ResponseEntity<?> getAllMessage(@PathVariable(name = "channelId")Long id){
        ChannelDTO channelDTO = channelService.getChannelById(id);
        return ResponseEntity.status(HttpStatus.OK).body(messageService.getAllMessageFromChannel(channelDTO));
    }

    @PutMapping("/{messageId}")
    public ResponseEntity<?> putMessage(@PathVariable(name = "messageId")Long id,@RequestBody MessageDTO messageDTO){
        messageDTO.setId(id);
        return ResponseEntity.status(HttpStatus.OK).body(messageService.updateMessage(messageDTO));
    }
    @DeleteMapping("/{messageId}")
    public ResponseEntity<?> deleteMessage(@PathVariable(name = "messageId")Long id){
        MessageDTO messageDTO = messageService.getMessageById(id);
        messageService.deleteMessage(messageDTO);
        return ResponseEntity.status(HttpStatus.OK).body("Объект успешно удален");
    }
    @PostMapping()
    public ResponseEntity<?> postMessage(@PathVariable(name = "channelId")Long id,@RequestBody MessageDTO messageDTO){
        ChannelDTO channelDTO = channelService.getChannelById(id);
        messageDTO.setChannel(id);
        return ResponseEntity.status(HttpStatus.CREATED).body(messageService.saveMessage(messageDTO));
    }
}
