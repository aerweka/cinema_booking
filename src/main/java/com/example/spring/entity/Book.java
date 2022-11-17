package com.example.spring.entity;

import com.example.spring.utils.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Setter
@Getter
@Entity
@Table(name = "books")
@Where(clause = "deleted_date is null")
public class Book extends BaseEntity {
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyy")
    @Column(name = "book_date", nullable = false)
    private LocalDate bookDate;

    @Column(name = "schedule_id")
    private Long scheduleId;

    @Column(name = "user_id")
    private Long userId;

    @Transient
    private List<Long> seats;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "id", insertable = false, updatable = false)
    private User userRelations;

    @OneToMany(mappedBy = "bookRelation", fetch = FetchType.LAZY)
    private List<Seat> seatData;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "schedule_id", referencedColumnName = "id", insertable = false, updatable = false)
    private Schedule scheduleRelations;
}
