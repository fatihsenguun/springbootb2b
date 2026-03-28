package com.fatihsengun.repository;

import com.fatihsengun.entity.User;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository {

    Optional<User> findByEmail(String mail);


}
