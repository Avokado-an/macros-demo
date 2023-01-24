package com.bahdanau.user.repository;

import com.bahdanau.user.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface UserRepository extends MongoRepository<User, String> {
    List<User> findAllByUsernameContainingIgnoreCase(String usernameFragment);

    User findAllByEmail(String email);
}
