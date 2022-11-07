package com.example.spring.modules.film;

import com.example.spring.utils.BaseEntity;
import com.example.spring.modules.schedule.Schedule;
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
public class Film extends BaseEntity {
    @Column(name = "code", nullable = false)
    private String code;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "is_played", nullable = false)
    private Boolean isPlayed;

    @OneToMany(mappedBy = "filmByFilmCode", cascade = CascadeType.ALL)
    private List<Schedule> schedulesByCode;
}
