package com.taiji;

import com.taiji.pojo.Student;
import com.taiji.pojo.Teacher;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import javax.persistence.*;

/**
 * Unit test for simple App.
 */
public class teacher_studentTest
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
    public void inser() {
        Teacher teacher = new Teacher();
        teacher.setName("wangss");
        teacher.setPhone("12345456");

        Student student = new Student();
        student.setName("shdd");
        entityManager.persist(teacher);
        entityManager.persist(student);
    }

    //一对一
    @Test
    public void oneTomany1() {
        //持久化操作
        Teacher teacher = new Teacher();
        teacher.setName("wangssqq");
        teacher.setPhone("123445456");

        Student student = new Student();
        student.setName("shddqq");
        student.setTeacher(teacher);

        entityManager.persist(teacher);
        entityManager.persist(student);
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
