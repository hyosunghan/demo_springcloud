package com.example.uaaservice.dao;


import com.example.uaaservice.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by hyosunghan on 2019/7/12.
 */

public interface UserDao extends JpaRepository<User, Long> {

	User findByUsername(String username);
}
