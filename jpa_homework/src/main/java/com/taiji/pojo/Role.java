package com.taiji.pojo;

import lombok.Data;
import lombok.ToString;
import javax.persistence.*;
import java.util.List;

@Entity
@Data
@ToString
@Table(name="role")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = 20, unique = true)
    private String name;

    @Column(length = 20)
    private String type;

    @ManyToMany(mappedBy = "roleList")
    private List<Users> usersList;

    @JoinColumn(name="users_id")
    @ManyToOne
    private Users users;
}
