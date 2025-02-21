package com.hahaton.hahaton.Entities;

import com.hahaton.hahaton.Entities.DTO.FeedBackDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class FeedBack {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private SendTask sendTask;
    private int score;
    private String text;

    public FeedBack(FeedBackDTO feedBackDTO, SendTask sendTask){
        this.id = feedBackDTO.getId();
        this.sendTask = sendTask;
        this.score = feedBackDTO.getScore();
        this.text = feedBackDTO.getText();
    }

    public FeedBack() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public SendTask getSendTask() {
        return sendTask;
    }

    public void setSendTask(SendTask sendTask) {
        this.sendTask = sendTask;
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
