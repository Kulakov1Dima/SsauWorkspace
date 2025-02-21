package com.hahaton.hahaton.Entities.DTO;

import com.hahaton.hahaton.Entities.Channel;
import com.hahaton.hahaton.Entities.Student;
import com.hahaton.hahaton.Entities.UserInChannel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.java.Log;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserInChannelDTO {

    private Long id;
    private Long student;
    private Long chanel;

    public UserInChannelDTO(UserInChannel userInChannel){
        this.id = userInChannel.getId();
        this.student = userInChannel.getStudent().getId();
        this.chanel = userInChannel.getChannel().getId();
    }

    public UserInChannelDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getStudent() {
        return student;
    }

    public void setStudent(Long student) {
        this.student = student;
    }

    public Long getChanel() {
        return chanel;
    }

    public void setChanel(Long chanel) {
        this.chanel = chanel;
    }
}
