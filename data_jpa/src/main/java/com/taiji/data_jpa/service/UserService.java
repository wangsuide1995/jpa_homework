package com.taiji.data_jpa.service;

import com.taiji.data_jpa.dao.UserRepository;
import com.taiji.data_jpa.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User getUser(User user1) {
        System.out.println("-----id-----" + user1.getId());
        User user = userRepository.findOne(user1.getId());
        return user;
    }

    public User updateservice(User user) {
        User users = userRepository.findOne(user.getId());
        if (user.getName() != null){
            users.setName(user.getName());
        }
        if (user.getPassword() != null){
            users.setPassword(user.getPassword());
        }
        if (user.getPhone() != null){
            users.setPhone(user.getPhone());
        }
        if (user.getEmail() != null){
            users.setEmail(user.getEmail());
        }
        User userss = userRepository.saveAndFlush(users);
        return userss;
    }
}
