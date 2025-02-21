package com.hahaton.hahaton.Repositories;

import com.hahaton.hahaton.Entities.PointInTask;
import com.hahaton.hahaton.Entities.Task;
import org.springframework.data.repository.CrudRepository;

public interface PointInTaskRepository extends CrudRepository<PointInTask,Long> {
    Iterable<PointInTask> findAllByTask(Task task);
}
