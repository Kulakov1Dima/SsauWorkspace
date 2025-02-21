package com.hahaton.hahaton.Controllers;

import com.hahaton.hahaton.Entities.DTO.ChannelDTO;
import com.hahaton.hahaton.Entities.DTO.StudentDTO;
import com.hahaton.hahaton.Services.ChannelService;
import com.hahaton.hahaton.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/channel")
public class ChannelController {

    @Autowired
    private ChannelService channelService;
    @Autowired
    private UserService userService;

    @GetMapping()
    public ResponseEntity<?> getChannels(){
        return ResponseEntity.status(HttpStatus.OK).body(channelService.getAllChannel());
    }

    @PutMapping("/{channelId}")
    public ResponseEntity<?> putChannel(@PathVariable(name = "channelId")Long id, @RequestBody ChannelDTO newChannel){
        newChannel.setId(id);
        return ResponseEntity.status(HttpStatus.OK).body(channelService.updateChannel(newChannel));
    }

    @DeleteMapping("/{channelId}")
    public ResponseEntity<?> deleteChannel(@PathVariable(name = "channelId")Long id){
        ChannelDTO channelDTO = channelService.getChannelById(id);
        channelService.deleteChannel(channelDTO);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Объект успешно удален");
    }

    @PostMapping()
    public ResponseEntity<?> postChannel(@RequestBody ChannelDTO channelDTO){
        System.out.println("!!!"+ channelDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(channelService.saveChannel(channelDTO));
    }

    @PostMapping("/{channelId}/add")
    public ResponseEntity<?> addUserInChannel(@RequestBody StudentDTO studentDTO, @PathVariable(name = "channelId")Long id){
        ChannelDTO channelDTO = channelService.getChannelById(id);
        channelService.addUserInChannel(studentDTO,channelDTO);
        return ResponseEntity.status(HttpStatus.OK).body(channelService.getAllUserByChannel(channelDTO));
    }

    @DeleteMapping("/{channelId}/delete/{studentId}")
    public ResponseEntity<?> deleteUserFromChannel(@PathVariable(name = "channelId")Long channelId, @PathVariable(name = "studentId")Long studentId){
        StudentDTO studentDTO = userService.getStudentByIdDTO(studentId);
        ChannelDTO channelDTO = channelService.getChannelById(channelId);
        channelService.deleteUserFromChannel(studentDTO,channelDTO);
        return ResponseEntity.status(HttpStatus.OK).body(channelService.getAllUserByChannel(channelDTO));
    }

    @GetMapping("/{channelId}/users")
    public ResponseEntity<?> getAllUserInChannel(@PathVariable(name = "channelId")Long id){
        ChannelDTO channelDTO = channelService.getChannelById(id);
        return ResponseEntity.status(HttpStatus.OK).body(channelService.getAllUserByChannel(channelDTO));
    }
}
