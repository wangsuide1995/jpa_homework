package com.taiji.pojo;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Data
@ToString
@Table(name="book")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = 20, unique = true)
    private String name;

    @Column(length = 100)
    private String type;

    @JoinColumn(name="author_id")
    @ManyToOne
    private Author author;
}
