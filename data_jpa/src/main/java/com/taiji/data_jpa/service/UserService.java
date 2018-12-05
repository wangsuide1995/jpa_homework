package com.taiji.data_jpa.service;

import com.taiji.data_jpa.dao.UserRepository;
import com.taiji.data_jpa.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Transactional(propagation = Propagation.REQUIRED)
    public User getUser(User user1) {
        System.out.println("-----id-----" + user1.getId());
        User user = userRepository.findOne(user1.getId());
        return user;
    }

    @Transactional(propagation = Propagation.REQUIRED)
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

    @Transactional(propagation = Propagation.REQUIRED)
    public User insertuser(User user) {
        User user1 = userRepository.save(user);
        return user1;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public Integer deleteuser(User user) {
        Integer user1 = userRepository.deleteById(user.getId());
        return user1;
    }
}
