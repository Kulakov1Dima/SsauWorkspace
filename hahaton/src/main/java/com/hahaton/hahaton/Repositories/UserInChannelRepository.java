package com.hahaton.hahaton.Repositories;

import com.hahaton.hahaton.Entities.*;
import org.springframework.data.repository.CrudRepository;

public interface UserInChannelRepository extends CrudRepository<UserInChannel,Long> {
    Iterable<UserInChannel> findAllByStudent(Student student);
    Iterable<UserInChannel> findAllByChannel(Channel channel);
    UserInChannel findByStudentAndChannel(Student student, Channel channel);
}
