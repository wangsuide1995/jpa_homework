package com.taiji.data_jpa.dao;

import com.taiji.data_jpa.pojo.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User,Integer>,JpaSpecificationExecutor<User> {

    User findUserByName(String name);

    User findUserByNameAndIdAndPassword(String name,Integer id,String password);

    @Query(value = "SELECT u FROM User u WHERE u.name = ?1")
    User findByname(String name);

}
