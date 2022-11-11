package com.example.spring.entity;

import com.example.spring.utils.BaseEntity;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.time.LocalDate;

@Setter
@Getter
@Entity
@Table(name = "books")
@Where(clause = "deleted_date is null")
public class Book extends BaseEntity {
    @Column(name = "book_date", nullable = false)
    private LocalDate bookDate;

    @Column(name = "film_code", nullable = false)
    private String filmCode;

    @Column(name = "studio_code", nullable = false)
    private String studioCode;
}
