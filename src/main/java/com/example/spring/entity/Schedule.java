package com.example.spring.entity;

import com.example.spring.utils.BaseEntity;
import com.example.spring.entity.Film;
import com.fasterxml.jackson.annotation.JsonFormat;
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

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyy")
    @Column(name = "show_date", nullable = false)
    private LocalDate showDate;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm:ss")
    @Column(name = "show_start")
    private LocalTime showStart;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm:ss")
    @Column(name = "show_end")
    private LocalTime showEnd;

    @Column(name = "ticket_price")
    private Long ticketPrice;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "film_code", referencedColumnName = "code", insertable = false, updatable = false)
    private Film filmByFilmCode;
}
