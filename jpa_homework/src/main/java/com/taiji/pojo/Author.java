package com.taiji.pojo;

import lombok.Data;
import lombok.ToString;
import javax.persistence.*;

@Entity
@Data
@ToString
@Table(name="author")
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = 20, unique = true)
    private String name;

    @Column(length = 100)
    private String phone;

    @JoinColumn(name="book_id")
    @OneToMany
    private Book book;
}
