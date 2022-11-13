package com.example.spring.repository;

import com.example.spring.entity.Schedule;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface SchedulesRepository extends PagingAndSortingRepository<Schedule, Long>, JpaSpecificationExecutor<Schedule> {
    /*

   selet * from barang;
   1. native query :
     contoh : selet * from barang;

    */
    @Query(value = "select * from schedules c WHERE c.id = :idbarang", nativeQuery = true)
    Object getbyID(@Param("idbarang") Long idbebas);

    /*
    2. JPQL : JPA query : yang di slect adalah nama class variable

     */
    @Query(value = "select c from Schedule c WHERE c.id = :idbarang")
    Schedule getbyIDByJPQL(@Param("idbarang") Long idbebas);

    @Query(value = "select c from Schedule c ")
    public Page<Schedule> getListData(Pageable pageable);

    Page<Schedule> findAllByFilmCode(String filmCode, Pageable pageable);
}
