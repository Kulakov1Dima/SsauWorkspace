package com.hahaton.hahaton.Entities;

import com.hahaton.hahaton.Entities.DTO.UserInChannelDTO;
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
public class UserInChannel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private Student student;
    @ManyToOne
    private Channel channel;

    public UserInChannel(UserInChannelDTO userInChannelDTO, Student student, Channel channel){
        this.id = userInChannelDTO.getId();
        this.student = student;
        this.channel = channel;
    }

    public UserInChannel() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }


    public Channel getChannel() {
        return channel;
    }

    public void setChannel(Channel chanel) {
        this.channel = chanel;
    }
}
