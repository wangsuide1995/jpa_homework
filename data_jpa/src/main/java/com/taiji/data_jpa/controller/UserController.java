package com.taiji.data_jpa.controller;

import com.taiji.data_jpa.dao.UserRepository;
import com.taiji.data_jpa.pojo.User;
import com.taiji.data_jpa.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.web.bind.annotation.*;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserService userService;

    //根据id调用方法来查询用户
    @GetMapping("/getuser")
    public User getUser(User user1) {
        User user = userService.getUser(user1);
        return user;
    }

    //添加用户方法
    @GetMapping("/adduser")
    public String insert(User user) {
        System.out.println("-----user-----" + user.toString());
        User user1 = userRepository.save(user);
        if (user1 != null) {
            return "ok";
        } else {
            return "fail";
        }
    }

    //调用findall无参数的方法来查询所有
    @GetMapping("/findall")
    public List<User> findall() {
        List<User> list = userRepository.findAll();
        //打印输出查看
        Iterator<User> iterator = list.iterator();
        while (iterator.hasNext()) {
            System.err.println("users is:" + iterator.next());
        }
        if (list != null){
            return list;
        }else {
            return null;
        }

    }

    //根据姓名来查用户
    @GetMapping("/byname")
    public User byname(User user) {
        System.out.println("-----name-----" + user.getName());
        User users = userRepository.findUserByName(user.getName());
        return users;
    }

    //根据id、name、pwd来查询用户
    @GetMapping("/bynamepwdid")
    public User bynamepwdid(User user) {
        User users = userRepository.findUserByNameAndIdAndPassword(user.getName(),user.getId(),user.getPassword());
        return users;
    }

    //利用id来删除用户
    @GetMapping("/delete")
    public String detele(User user) {
        userRepository.delete(user.getId());
        return "ok";
    }

    //用id来修改用户信息，并对属性进行非空判断，达到自定义更新
    @GetMapping("/update")
    public User update(User user) {
      User user1 =  userService.updateservice(user);
      return user1;
    }

    //根据自定义的sql语句来查询用户
    @GetMapping("/bynames")
    public User bynames(User user) {
        User users = userRepository.findByname(user.getName());
        return users;
    }

    //无条件分页查询和有条件分页查询
    //page:查询第几页
    //size:每页多少条数据
    //select:用名字模糊查询
    //start:查询多少到多少的开始数据
    //end:查询多少到多少的结束数据
    @GetMapping("/pageable")
    public Page<User> getEntryByParams(@RequestParam(value = "page", defaultValue = "0") Integer page,
                                       @RequestParam(value = "size", defaultValue = "10") Integer size,
                                       @RequestParam(value = "select", defaultValue = "") String select,
                                       @RequestParam(value = "start", defaultValue = "0") Integer start,
                                       @RequestParam(value = "end", defaultValue = "") Integer end) {

        Sort sort = new Sort(Sort.Direction.DESC, "id");
        Pageable pageable = new PageRequest(page, size, sort);
        Specification<User> specs = new Specification<User>(){
            public Predicate toPredicate(Root<User> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> list = new ArrayList<Predicate>();
                list.add(cb.like(root.<String>get("name"), "%"+select+"%"));
                if (start != 0 && end != null){
                    list.add(cb.between(root.<Integer>get("id"),start,end));
                }
                return cb.and(list.toArray(new Predicate[0]));
            }
        };
        return userRepository.findAll(specs,pageable);
    }
}