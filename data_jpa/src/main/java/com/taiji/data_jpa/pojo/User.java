package com.taiji.data_jpa.pojo;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;

@Data
@ToString
@Entity
@Table(name="jpa_user")
public class User {
    @Id //这是一个主键
    @GeneratedValue(strategy = GenerationType.IDENTITY) //自增主键
    private Integer id;
    private String name;
    private String password;
    private String email;
    private String phone;
}
