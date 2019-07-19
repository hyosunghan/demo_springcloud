package com.example.userservice.dao;


import com.forezp.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by hysounghan on 2019/7/12.
 */

public interface UserDao extends JpaRepository<User, Long> {

	User findByUsername(String username);
}
