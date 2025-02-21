package com.hahaton.hahaton.Repositories;

import com.hahaton.hahaton.Entities.PointInTask;
import com.hahaton.hahaton.Entities.SendTask;
import com.hahaton.hahaton.Entities.Student;
import org.springframework.data.repository.CrudRepository;

public interface SendTaskRepository extends CrudRepository<SendTask,Long> {
    Iterable<SendTask> findAllByStudent(Student student);
    Iterable<SendTask> findAllByStudentAndPointInTask(Student student, PointInTask pointInTask);
    Iterable<SendTask> findAllByPointInTask(PointInTask pointInTask);
}
