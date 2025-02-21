package com.hahaton.hahaton.Repositories;

import com.hahaton.hahaton.Entities.AdditionalFile;
import com.hahaton.hahaton.Entities.Task;
import org.springframework.data.repository.CrudRepository;

public interface AdditionalFileRepository extends CrudRepository<AdditionalFile,Long> {
    Iterable<AdditionalFile> findAllByTask(Task task);
}
