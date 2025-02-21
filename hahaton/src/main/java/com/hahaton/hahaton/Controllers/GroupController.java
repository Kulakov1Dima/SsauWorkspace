package com.hahaton.hahaton.Controllers;

import com.hahaton.hahaton.Entities.DTO.GroupDTO;
import com.hahaton.hahaton.Services.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController()
@RequestMapping("/group")
public class GroupController {

    @Autowired
    private GroupService groupService;

    @GetMapping()
    public ResponseEntity<?> getAllGroup(){
        return ResponseEntity.status(HttpStatus.OK).body(groupService.getAllGroup());
    }

    @GetMapping("/{groupId}")
    public ResponseEntity<?> getGroup(@PathVariable(name = "groupId")Long id){
        return ResponseEntity.status(HttpStatus.OK).body(groupService.getGroupByIdDTO(id));
    }

    @PostMapping()
    public ResponseEntity<?> postGroup(@RequestBody GroupDTO groupDTO){
        return ResponseEntity.status(HttpStatus.OK).body(groupService.saveGroup(groupDTO));
    }

    @PutMapping("/{groupId}")
    public ResponseEntity<?> putGroup(@PathVariable(name = "groupId")Long id,@RequestBody GroupDTO groupDTO){
        groupDTO.setId(id);
        groupDTO = groupService.updateGroup(groupDTO);
        return ResponseEntity.status(HttpStatus.OK).body(groupDTO);
    }
    @DeleteMapping("/{groupId}")
    public ResponseEntity<?> deleteGroup(@PathVariable(name = "groupId")Long id){
        GroupDTO groupDTO = groupService.getGroupByIdDTO(id);
        groupService.deleteGroup(groupDTO);
        return ResponseEntity.status(HttpStatus.OK).body("Объект успешно удален");
    }
}

