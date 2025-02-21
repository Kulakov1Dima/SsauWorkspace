package com.hahaton.hahaton.Services;

import com.hahaton.hahaton.Entities.*;
import com.hahaton.hahaton.Entities.DTO.*;
import com.hahaton.hahaton.Repositories.ChannelRepository;
import com.hahaton.hahaton.Repositories.UserInChannelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ChannelService {


    @Autowired
    private ClassConverter classConverter;
    @Autowired
    private ChannelRepository channelRepository;
    @Autowired
    private UserInChannelRepository userInChannelRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private GroupService groupService;
    @Autowired
    private AcademicSubjectService academicSubjectService;

    public List<ChannelDTO> getAllChannel(){
        List<ChannelDTO> channelDTOList = new ArrayList<>();
        Iterable<Channel> channelIterable = channelRepository.findAll();
        for(Channel channel: channelIterable){
            channelDTOList.add(classConverter.channelConverter(channel));
        }
        return channelDTOList;
    }

    public ChannelDTO getChannelById(Long id){
        Channel channel = channelRepository.findById(id).get();
        return new ChannelDTO(channel);
    }

    public List<ChannelDTO> getChannelByGroup(GroupDTO groupDTO){
        List<ChannelDTO> channelDTOList = new ArrayList<>();
        Group group = classConverter.groupConverter(groupDTO);
        Iterable<Channel> channelIterable = channelRepository.findAllByGroup(group);
        for(Channel channel: channelIterable){
            channelDTOList.add(classConverter.channelConverter(channel));
        }
        return channelDTOList;
    }

    public List<ChannelDTO> getChannelByStudent(StudentDTO studentDTO){
        List<ChannelDTO> channelDTOList = new ArrayList<>();
        Student student = classConverter.studentConverter(studentDTO);
        Iterable<UserInChannel> userInChanelIterable = userInChannelRepository.findAllByStudent(student);
        for(UserInChannel userInChannel : userInChanelIterable){
            channelDTOList.add(classConverter.channelConverter(userInChannel.getChannel()));
        }
        return channelDTOList;
    }

    public List<ChannelDTO> getChannelByTeacher(TeacherDTO teacherDTO){
        List<ChannelDTO> channelDTOList = new ArrayList<>();
        Teacher teacher = classConverter.teacherConverter(teacherDTO);
        Iterable<Channel> channelIterable = channelRepository.findAllByTeacher(teacher);
        for(Channel channel: channelIterable){
            channelDTOList.add(classConverter.channelConverter(channel));
        }
        return channelDTOList;
    }

    public ChannelDTO saveChannel(ChannelDTO channelDTO){
        Channel channel = classConverter.channelConverter(channelDTO);
        return new ChannelDTO(channelRepository.save(channel));
    }

    public ChannelDTO updateChannel(ChannelDTO newChannelDTO){
        Channel channel = channelRepository.findById(newChannelDTO.getId()).get();
        channel.setGroup(groupService.getGroupById(newChannelDTO.getGroup()));
        channel.setTeacher(userService.getTeacherById(newChannelDTO.getTeacher()));
        channel.setAcademicSubject(academicSubjectService.getSubjectById(newChannelDTO.getAcademicSubject()));
        return new ChannelDTO(channelRepository.save(channel));

    }

    public void deleteChannel(ChannelDTO channelDTO){
        Channel channel = channelRepository.findById(channelDTO.getId()).get();
        channelRepository.delete(channel);
    }

    public List<UserInChannelDTO> getAllUserByChannel(ChannelDTO channelDTO){
        List<UserInChannelDTO> userInChannelDTOList = new ArrayList<>();
        Channel channel = channelRepository.findById(channelDTO.getId()).get();
        Iterable<UserInChannel> userInChanelIterable = userInChannelRepository.findAllByChannel(channel);
        for(UserInChannel userInChannel : userInChanelIterable){
            userInChannelDTOList.add(classConverter.userInChannelConverter(userInChannel));
        }
        return userInChannelDTOList;
    }

    public List<UserInChannelDTO> addUserInChannel(StudentDTO studentDTO, ChannelDTO channelDTO){
        UserInChannel userInChannel = new UserInChannel();
        userInChannel.setStudent(classConverter.studentConverter(studentDTO));
        userInChannel.setChannel(classConverter.channelConverter(channelDTO));
        userInChannelRepository.save(userInChannel);
        return getAllUserByChannel(channelDTO);
    }

    public List<UserInChannelDTO> deleteUserFromChannel(StudentDTO studentDTO, ChannelDTO channelDTO){
        UserInChannel userInChannel = userInChannelRepository.findByStudentAndChannel(classConverter.studentConverter(studentDTO),classConverter.channelConverter(channelDTO));
        userInChannelRepository.delete(userInChannel);
        return getAllUserByChannel(channelDTO);
    }

    public List<UserInChannelDTO> addUserInChannel(List<StudentDTO> studentList, ChannelDTO channelDTO){
        List<UserInChannel> userInChannelList = new ArrayList<>();
        for(StudentDTO studentDTO: studentList){
            UserInChannel userInChannel = new UserInChannel();
            userInChannel.setChannel(classConverter.channelConverter(channelDTO));
            userInChannel.setStudent(classConverter.studentConverter(studentDTO));
            userInChannelList.add(userInChannel);
        }
        userInChannelRepository.saveAll(userInChannelList);
        return getAllUserByChannel(channelDTO);
    }

    public List<UserInChannelDTO> deleteUserFromChannel(List<StudentDTO> studentList, ChannelDTO channelDTO){
        List<UserInChannel> userInChannelList = new ArrayList<>();
        for(StudentDTO studentDTO : studentList){
            UserInChannel userInChannel = userInChannelRepository.findByStudentAndChannel(classConverter.studentConverter(studentDTO),classConverter.channelConverter(channelDTO));
            userInChannelList.add(userInChannel);
        }
        userInChannelRepository.deleteAll(userInChannelList);
        return getAllUserByChannel(channelDTO);
    }


}
