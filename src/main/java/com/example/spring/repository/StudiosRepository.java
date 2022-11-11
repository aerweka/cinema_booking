package com.example.spring.repository;

import com.example.spring.entity.Studio;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface StudiosRepository extends PagingAndSortingRepository<Studio, Long>, JpaSpecificationExecutor<Studio> {
    /*

   selet * from barang;
   1. native query :
     contoh : selet * from barang;

    */
    @Query(value = "select * from films c WHERE c.id = :idbarang", nativeQuery = true)
    Object getbyID(@Param("idbarang") Long idbebas);

    /*
    2. JPQL : JPA query : yang di slect adalah nama class variable

     */
    @Query(value = "select c from Studio c WHERE c.id = :idbarang")
    Studio getbyIDByJPQL(@Param("idbarang") Long idbebas);

    @Query(value = "select c from Studio c ")
    public Page<Studio> getListData(Pageable pageable);
}
