package com.example.spring.entity;

import com.example.spring.utils.BaseEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Where;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.*;

@Setter
@Getter
@Entity
@Table(name = "users")
@Where(clause = "deleted_date is null")
public class User extends BaseEntity implements UserDetails {
    @Column(name = "email", nullable = false)
    private String email;

    @Column(length = 100, unique = true)
    private String username;

    @Column(length = 100, unique = true)
    private String username1;

    @Column(length = 100)
    private String fullname;

    @JsonIgnore
    private String password;

    @JsonIgnore
    private String verifyToken;

    @JsonIgnore
    private Date expiredVerifyToken;

    @Column(length = 100, nullable = true)
    private String otp;

    private Date otpExpiredDate;

    @JsonIgnore
    private Boolean enabled = true;

    @JsonIgnore
    @Column(name = "not_expired")
    private Boolean accountNonExpired = true;

    @JsonIgnore
    @Column(name = "not_locked")
    private Boolean accountNonLocked = true;

    @JsonIgnore
    @Column(name = "credential_not_expired")
    private Boolean credentialsNonExpired = true;

    @ManyToMany(targetEntity = Role.class, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(
            name = "oauth_user_role",
            joinColumns = {
                    @JoinColumn(name = "user_id")
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "role_id")
            }
    )
    private List<Role> roles = new ArrayList<>();

    @OneToMany(mappedBy = "userRelations", fetch = FetchType.LAZY)
    private Set<Book> bookData;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.roles;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return accountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return accountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return credentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

}
