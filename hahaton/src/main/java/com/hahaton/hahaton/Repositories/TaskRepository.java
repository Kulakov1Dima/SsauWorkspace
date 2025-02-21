package com.hahaton.hahaton.Repositories;

import com.hahaton.hahaton.Entities.Channel;
import com.hahaton.hahaton.Entities.Task;
import org.springframework.data.repository.CrudRepository;

public interface TaskRepository extends CrudRepository<Task,Long> {
    Iterable<Task> findAllByChannel(Channel channel);
}
