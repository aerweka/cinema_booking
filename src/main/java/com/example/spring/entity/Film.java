package com.example.spring.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.List;

@Setter
@Getter
@Entity
@Table(name = "films")
@Where(clause = "deleted_date is null")
public class Film extends BaseEntity{
    @Column(name = "code", nullable = false)
    private String code;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "is_played", nullable = false)
    private Boolean isPlayed;

    @OneToMany(mappedBy = "filmByFilmCode", cascade = CascadeType.ALL)
    private List<Schedule> schedulesByCode;
}
