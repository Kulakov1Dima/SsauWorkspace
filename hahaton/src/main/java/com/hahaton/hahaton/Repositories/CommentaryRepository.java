package com.hahaton.hahaton.Repositories;

import com.hahaton.hahaton.Entities.Commentary;
import com.hahaton.hahaton.Entities.Message;
import org.springframework.data.repository.CrudRepository;

public interface CommentaryRepository extends CrudRepository<Commentary,Long> {
    Iterable<Commentary> findAllByMessage(Message message);
}
