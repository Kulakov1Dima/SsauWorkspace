package com.hahaton.hahaton.Services;

import com.hahaton.hahaton.Entities.DTO.GroupDTO;
import com.hahaton.hahaton.Entities.Group;
import com.hahaton.hahaton.Repositories.GroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GroupService {

    @Autowired
    private ClassConverter classConverter;
    @Autowired
    private GroupRepository groupRepository;

    public List<GroupDTO> getAllGroup(){
        List<GroupDTO> groupDTOList = new ArrayList<>();
        Iterable<Group> groupIterable = groupRepository.findAll();
        for(Group group: groupIterable){
            groupDTOList.add(new GroupDTO(group));
        }
        return groupDTOList;
    }

    public GroupDTO getGroupByIdDTO(Long id){
        Group group = groupRepository.findById(id).get();
        return new GroupDTO(group);
    }

    public Group getGroupById(Long id){
        return groupRepository.findById(id).get();
    }

    public GroupDTO saveGroup(GroupDTO groupDTO){
        Group group = classConverter.groupConverter(groupDTO);
        return new GroupDTO(groupRepository.save(group));
    }

    public GroupDTO updateGroup(GroupDTO newGroupDTO){
        Group group = groupRepository.findById(newGroupDTO.getId()).get();
        group.setName(newGroupDTO.getName());
        return new GroupDTO(groupRepository.save(group));
    }

    public void deleteGroup(GroupDTO groupDTO){
        Group group = groupRepository.findById(groupDTO.getId()).get();
        groupRepository.delete(group);
    }
}
