package com.taiji.pojo;

import lombok.Data;
import lombok.ToString;
import javax.persistence.*;

@Entity
@Data
@ToString
@Table(name="student")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = 20, unique = true)
    private String name;

    @Column(length = 100)
    private String phone;

    @JoinColumn(name="teacher_id")//关联user表的id字段
    @OneToMany
    private Teacher teacher;
}
