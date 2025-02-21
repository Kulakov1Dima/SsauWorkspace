package com.hahaton.hahaton.Repositories;

import com.hahaton.hahaton.Entities.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User,Long> {
}
