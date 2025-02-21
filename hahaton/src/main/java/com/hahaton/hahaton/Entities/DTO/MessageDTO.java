package com.hahaton.hahaton.Entities.DTO;

import com.hahaton.hahaton.Entities.Message;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class MessageDTO {

    private Long id;
    private Long channel;
    private Long user;
    private String text;

    public MessageDTO(Message message){
        this.id = message.getId();
        this.channel = message.getChannel().getId();
        this.user = message.getUser().getId();
        this.text = message.getText();
    }

    public MessageDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getChannel() {
        return channel;
    }

    public void setChannel(Long channel) {
        this.channel = channel;
    }

    public Long getUser() {
        return user;
    }

    public void setUser(Long user) {
        this.user = user;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
