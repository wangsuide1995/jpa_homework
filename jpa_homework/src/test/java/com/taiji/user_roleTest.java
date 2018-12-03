package com.taiji;

import com.taiji.pojo.Role;
import com.taiji.pojo.Users;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Unit test for simple App.
 */
public class user_roleTest
{
    /**
     * Rigorous Test :-)
     */

    @PersistenceContext
    private static EntityManagerFactory factory = null;
    private static EntityManager entityManager = null;
    private static EntityTransaction transaction = null;

    @Before
    public void before(){
        // 1. 创建EntityManagerFactory
        factory = Persistence.createEntityManagerFactory("jpa_homework");
        // 2. 创建EntityManager
        entityManager = factory.createEntityManager();
        // 3.开启事务
        transaction = entityManager.getTransaction();
        transaction.begin();
    }

    //新增数据
    @Test
    public void insertauth() {
        Users user = new Users();
        user.setName("wangss");
        user.setPwd("12346");
        user.setPhone("12345456");

        Role role = new Role();
        role.setName("财务主管");
        role.setType("2");
        entityManager.persist(user);
        entityManager.persist(role);
    }

    //多对多的操作
    @Test
    public void shouldAnswerWithTrue()
    {
        Users user = new Users();
        user.setName("wsrr");
        user.setPwd("12655");
        user.setPhone("125645");
        // 添加user到数据库，相当于hibernate的save();
        List<Role> list = new ArrayList<>();

        Role role1 = entityManager.find(Role.class,1);
        list.add(role1);

        Role role12 = entityManager.find(Role.class,2);
        list.add(role12);
        user.setRoleList(list);
        entityManager.persist(user);
    }

    //根据id查询
    @Test
    public void find() {
        Users user = entityManager.find(Users.class,1);
        System.out.println("user:"+user.toString());
    }

    //根据id查询query(1)
    @Test
    public void query() {
        String select = "select * from users u where u.id = ?1 and u.name = ?2";
        Query query = entityManager.createNativeQuery(select,Users.class).setParameter(1,5).setParameter(2,"王绥德");
        Users user = (Users) query.getSingleResult();
        System.out.println("user:"+user.toString());
    }

    //根据id查询query(2)
    @Test
    public void queryselect() {
        String select = "select u from Users u where u.id = :id and u.name = :name";
        Query query = entityManager.createQuery(select).setParameter("id",7).setParameter("name","王");
        Users user = (Users) query.getSingleResult();
        System.out.println("user:"+user.toString());
    }

    //根据name查询
    @Test
    public void queryname() {
        String select = "select u.* from Users u where u.name = ?1";
        Query query = entityManager.createNativeQuery(select,Users.class).setParameter(1,"wans");
        Users user = (Users) query.getSingleResult();
        System.out.println("user:"+user.toString());
    }

    //根据id修改模块
    @Test
    public void update(){
        String sql ="update Users u set u.name =?1 where u.id = ?2";
        Query query = entityManager.createQuery(sql).setParameter(1,"网速对").setParameter(2,3);
        int num = query.executeUpdate();
        System.out.println("user:"+num);
    }

    //删除模块
    @Test
    public void remove() {
        Users user = entityManager.find(Users.class,2);
        entityManager.remove(user);
    }

    @After
    public void close(){
        // 1. 提交事务
        transaction.commit();
        // 2. 关闭EntityManager
        entityManager.close();
        // 3. 关闭EntityManagerFactory
        factory.close();
    }
}
