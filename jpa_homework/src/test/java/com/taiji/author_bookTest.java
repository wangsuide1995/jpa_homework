package com.taiji;

import com.taiji.pojo.Author;
import com.taiji.pojo.Book;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import javax.persistence.*;

/**
 * Unit test for simple App.
 */
public class author_bookTest
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
    public void insert() {
        Author author = new Author();
        author.setName("wangss");
        author.setPhone("12345456");

        Book book = new Book();
        book.setName("数学");
        book.setType("2");
        entityManager.persist(author);
        entityManager.persist(book);
    }

    //一对多
    @Test
    public void oneTomany1() {
        //持久化操作
        Author author = new Author();
        author.setName("王绥德");
        author.setPhone("12345456");

        Book book1 = new Book();
        book1.setName("数学");
        book1.setType("2");
        book1.setAuthor(author);

        Book book2 = new Book();
        book2.setName("语文");
        book2.setType("3");
        book2.setAuthor(author);
        // 添加user到数据库，相当于hibernate的save();
        entityManager.persist(author);
        entityManager.persist(book1);
        entityManager.persist(book2);
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
