package com.example.spring.entity;

import com.example.spring.utils.BaseEntity;
import com.example.spring.entity.Seat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.List;

@Setter
@Getter
@Entity
@Table(name = "studios")
@Where(clause = "deleted_date is null")
public class Studio extends BaseEntity {
    @Column(name = "code", nullable = false)
    private String code;

    @Column(name = "capacity", nullable = false)
    private Integer capacity;

    @JsonIgnore
    @OneToMany(mappedBy = "studioByStudioCode", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Seat> seatsByCode;

    @Transient
    private List<Seat> seatsData;
}