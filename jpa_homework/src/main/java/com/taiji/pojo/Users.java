package com.taiji.pojo;

import lombok.Data;
import lombok.ToString;
import javax.persistence.*;
import java.util.List;

@Entity
@Data
@ToString
@Table(name="users")
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = 20, unique = true)
    private String name;

    @Column(length = 100)
    private String pwd;

    @Column(length = 100)
    private String phone;

    @ManyToMany
    @JoinTable(name = "users_role",joinColumns = @JoinColumn(name = "Users_id"), inverseJoinColumns = @JoinColumn(name = "Role_id"))
    private List<Role> roleList;

    @JoinColumn(name="role_id")//关联user表的id字段
    @OneToMany
    private Role role;
}
