package com.bahdanau.user.repository;

import com.bahdanau.user.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends MongoRepository<User, String> {

    Optional<User> findByEmail(String email);
    List<User> findAllByUsernameContainingIgnoreCase(String usernameFragment);

    User findAllByEmail(String email);
}
