package com.hahaton.hahaton.Repositories;

import com.hahaton.hahaton.Entities.Channel;
import com.hahaton.hahaton.Entities.Message;
import org.springframework.data.repository.CrudRepository;

public interface MessageRepository extends CrudRepository<Message,Long> {
    Iterable<Message> findAllByChannel(Channel channel);
}
