package com.hahaton.hahaton.Repositories;

import com.hahaton.hahaton.Entities.FeedBack;
import com.hahaton.hahaton.Entities.PointInTask;
import com.hahaton.hahaton.Entities.SendTask;
import org.springframework.data.repository.CrudRepository;

public interface FeedBackRepository extends CrudRepository<FeedBack,Long> {
    Iterable<FeedBack> findAllBySendTask(SendTask sendTask);
    FeedBack findBySendTask(SendTask sendTask);
}
