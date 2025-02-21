package com.hahaton.hahaton.Repositories;

import com.hahaton.hahaton.Entities.Student;
import org.springframework.data.repository.CrudRepository;

public interface StudentRepository extends CrudRepository<Student,Long> {
}
