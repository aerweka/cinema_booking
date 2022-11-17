package com.example.spring.entity;

import com.example.spring.utils.BaseEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Where;

import javax.persistence.*;

@Setter
@Getter
@Entity
@Table(name = "seats")
@Where(clause = "deleted_date is null")
public class Seat extends BaseEntity {
    @Column(name = "studio_code", nullable = false)
    private String studioCode;

    @Column(name = "book_id")
    private Long bookId;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "studio_code", referencedColumnName = "code", insertable = false, updatable = false)
    private Studio studioByStudioCode;

    @Transient
    private Studio studioData;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "book_id", referencedColumnName = "id", insertable = false, updatable = false)
    private Book bookRelation;
}
