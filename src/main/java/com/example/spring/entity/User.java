package com.example.spring.entity;

import com.example.spring.utils.BaseEntity;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.Set;

@Setter
@Getter
@Entity
@Table(name = "users")
@Where(clause = "deleted_date is null")
public class User extends BaseEntity {
    @Column(name = "username", nullable = false)
    private String username;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @OneToMany(mappedBy = "userRelations", fetch = FetchType.LAZY)
    private Set<Book> bookData;
}
