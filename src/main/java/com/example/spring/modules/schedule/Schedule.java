package com.example.spring.modules.schedule;

import com.example.spring.utils.BaseEntity;
import com.example.spring.modules.film.Film;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Setter
@Getter
@Entity
@Table(name = "schedules")
@Where(clause = "deleted_date is null")
public class Schedule extends BaseEntity {
    @Column(name = "film_code", nullable = false)
    private String filmCode;

    @Column(name = "show_date", nullable = false)
    private LocalDate showDate;

    @Column(name = "show_start")
    private LocalTime showStart;

    @Column(name = "show_end")
    private LocalTime showEnd;

    @Column(name = "ticket_price")
    private Long ticketPrice;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "film_code", referencedColumnName = "code", insertable = false, updatable = false)
    private Film filmByFilmCode;
}
