package com.liubomur.bot.repository;

import com.liubomur.bot.entity.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {

}
