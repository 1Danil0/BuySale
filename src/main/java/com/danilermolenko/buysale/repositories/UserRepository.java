package com.danilermolenko.buysale.repositories;

import com.danilermolenko.buysale.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    public User findByEmail(String email);
}
