package com.hahaton.hahaton.Entities.DTO;

import com.hahaton.hahaton.Entities.Commentary;
import com.hahaton.hahaton.Entities.Message;
import com.hahaton.hahaton.Entities.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CommentaryDTO {

    private Long id;
    private Long user;
    private Long message;
    private String text;

    public CommentaryDTO(Commentary commentary){
        this.id = commentary.getId();
        this.user = commentary.getUser().getId();
        this.message = commentary.getMessage().getId();
        this.text = commentary.getText();
    }

    public CommentaryDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUser() {
        return user;
    }

    public void setUser(Long user) {
        this.user = user;
    }

    public Long getMessage() {
        return message;
    }

    public void setMessage(Long message) {
        this.message = message;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
