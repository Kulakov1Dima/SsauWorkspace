package com.hahaton.hahaton.Services;

import com.hahaton.hahaton.Entities.Commentary;
import com.hahaton.hahaton.Entities.DTO.ChannelDTO;
import com.hahaton.hahaton.Entities.DTO.CommentaryDTO;
import com.hahaton.hahaton.Entities.DTO.MessageDTO;
import com.hahaton.hahaton.Entities.Message;
import com.hahaton.hahaton.Repositories.ChannelRepository;
import com.hahaton.hahaton.Repositories.CommentaryRepository;
import com.hahaton.hahaton.Repositories.MessageRepository;
import com.hahaton.hahaton.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class MessageService {

    @Autowired
    private ClassConverter classConverter;
    @Autowired
    private MessageRepository messageRepository;
    @Autowired
    private CommentaryRepository commentaryRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ChannelRepository channelRepository;

    public List<MessageDTO> getAllMessage(){
        List<MessageDTO> messageDTOList = new ArrayList<>();
        Iterable<Message> messageIterable = messageRepository.findAll();
        for(Message message: messageIterable){
            messageDTOList.add(new MessageDTO(message));
        }
        return messageDTOList;
    }

    public List<MessageDTO> getAllMessageFromChannel(ChannelDTO channelDTO){
        List<MessageDTO> messageDTOList = new ArrayList<>();
        Iterable<Message> messageIterable = messageRepository.findAllByChannel(classConverter.channelConverter(channelDTO));
        for(Message message : messageIterable){
            messageDTOList.add(new MessageDTO(message));
        }
        return messageDTOList;
    }

    public MessageDTO getMessageById(Long id){
        Message message = messageRepository.findById(id).get();
        return new MessageDTO(message);
    }

    public MessageDTO saveMessage(MessageDTO messageDTO){
        Message message = classConverter.messageConverter(messageDTO);
        return new MessageDTO(messageRepository.save(message));
    }

    public MessageDTO updateMessage(MessageDTO newMessage){
        Message message = messageRepository.findById(newMessage.getId()).get();
        message.setChannel(channelRepository.findById(newMessage.getChannel()).get());
        message.setText(newMessage.getText());
        message.setUser(userRepository.findById(newMessage.getUser()).get());
        return new MessageDTO(messageRepository.save(message));
    }

    public void deleteMessage(MessageDTO messageDTO){
        Message message = messageRepository.findById(messageDTO.getId()).get();
        messageRepository.delete(message);
    }

    public List<CommentaryDTO> getAllCommentaryFromMessage(MessageDTO messageDTO){
        List<CommentaryDTO> commentaryDTOList = new ArrayList<>();
        Message message = messageRepository.findById(messageDTO.getId()).get();
        Iterable<Commentary> commentaryIterable = commentaryRepository.findAllByMessage(message);
        for(Commentary commentary: commentaryIterable){
            commentaryDTOList.add(new CommentaryDTO(commentary));
        }
        return commentaryDTOList;
    }

    public Map<MessageDTO,List<CommentaryDTO>> getAllCommentaryFromChannel(ChannelDTO channelDTO){
        Map<MessageDTO,List<CommentaryDTO>> messageDTOListMap = new HashMap<>();

        Iterable<Message> messageIterable = messageRepository.findAllByChannel(classConverter.channelConverter(channelDTO));
        for(Message message: messageIterable){
            Iterable<Commentary> commentaryIterable = commentaryRepository.findAllByMessage(message);
            List<CommentaryDTO> commentaryDTOList = new ArrayList<>();
            for(Commentary commentary: commentaryIterable){
                commentaryDTOList.add(new CommentaryDTO(commentary));
            }
            messageDTOListMap.put(new MessageDTO(message),commentaryDTOList);
        }
        return messageDTOListMap;
    }

    public CommentaryDTO saveCommentary(CommentaryDTO commentaryDTO){
        Commentary commentary = classConverter.commentaryConverter(commentaryDTO);
        return new CommentaryDTO(commentaryRepository.save(commentary));
    }

    public CommentaryDTO getCommentaryById(Long id){
        Commentary commentary = commentaryRepository.findById(id).get();
        return new CommentaryDTO(commentary);
    }

    public CommentaryDTO updateCommentary(CommentaryDTO newCommentaryDTO){
        Commentary commentary = commentaryRepository.findById(newCommentaryDTO.getId()).get();
        commentary.setMessage(messageRepository.findById(newCommentaryDTO.getMessage()).get());
        commentary.setUser(userRepository.findById(newCommentaryDTO.getUser()).get());
        commentary.setMessage(messageRepository.findById(newCommentaryDTO.getMessage()).get());
        return new CommentaryDTO(commentaryRepository.save(commentary));
    }

    public void deleteCommentary(CommentaryDTO commentaryDTO){
        Commentary commentary = commentaryRepository.findById(commentaryDTO.getId()).get();
        commentaryRepository.delete(commentary);
    }
}
