package com.hahaton.hahaton.Entities.DTO;


import com.hahaton.hahaton.Entities.FeedBack;
import com.hahaton.hahaton.Entities.SendTask;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@AllArgsConstructor
@Getter
@Setter
public class FeedBackDTO {

    private Long id;
    private Long sendTask;
    private int score;
    private String text;

    public FeedBackDTO(FeedBack feedBack){
        this.id = feedBack.getId();
        this.sendTask = feedBack.getSendTask().getId();
        this.score = feedBack.getScore();
        this.text = feedBack.getText();
    }

    public FeedBackDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getSendTask() {
        return sendTask;
    }

    public void setSendTask(SendTask sendTask) {
        this.sendTask = sendTask.getId();
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
