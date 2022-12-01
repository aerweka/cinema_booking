package com.example.spring.entity;

import com.example.spring.utils.BaseEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.List;

@Entity
@Setter
@Getter
@Table(name = "oauth_role", uniqueConstraints = {
        @UniqueConstraint(
                name = "role_name_and_type",
                columnNames = {"type", "name"}
        )})
public class Role extends BaseEntity implements GrantedAuthority {
    @Column(length = 20)
    private String name;

    private String type;

    @OneToMany(mappedBy = "role", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<RolePath> rolePaths;

    @JsonIgnore
    @ManyToMany(targetEntity = User.class, mappedBy = "roles", fetch = FetchType.LAZY)
    private List<User> users;

    @Override
    @JsonIgnore
    public String getAuthority() {
        return this.name;
    }

}
