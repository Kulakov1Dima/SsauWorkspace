package com.hahaton.hahaton.Repositories;

import com.hahaton.hahaton.Entities.Channel;
import com.hahaton.hahaton.Entities.Group;
import com.hahaton.hahaton.Entities.Teacher;
import org.springframework.data.repository.CrudRepository;

public interface ChannelRepository extends CrudRepository<Channel,Long> {
    Iterable<Channel> findAllByGroup(Group group);
    Iterable<Channel> findAllByTeacher(Teacher teacher);
}
