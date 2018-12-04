package com.taiji.data_jpa;

import com.taiji.data_jpa.dao.UserRepository;
import com.taiji.data_jpa.pojo.User;
import com.taiji.data_jpa.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DataJpaApplicationTests {

    @Autowired
    UserRepository userRepository;
    @Autowired
    UserService userService;

    @Test
    public void contextLoads() {
    User user = userRepository.findOne(1);
        System.out.println(user.toString());
    }

    @Test
    public void update() {
        User user = new User();
        user.setId(5);
        user.setName("崔七七");
        User user1 =  userService.updateservice(user);
        System.out.println("user:"+user1.toString());
    }
}
