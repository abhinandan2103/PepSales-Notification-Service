package com.pepsales.notification.repository;


import com.pepsales.notification.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
