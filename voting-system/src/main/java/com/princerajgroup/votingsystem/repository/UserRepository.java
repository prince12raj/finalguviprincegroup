package com.princerajgroup.votingsystem.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.votingsystem.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
