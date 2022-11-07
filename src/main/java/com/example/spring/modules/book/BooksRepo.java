package com.example.spring.modules.book;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface BooksRepo extends PagingAndSortingRepository<Book, Long>, JpaSpecificationExecutor<Book> {
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
    @Query(value = "select c from Book c WHERE c.id = :idbarang")
    Book getbyIDByJPQL(@Param("idbarang") Long idbebas);

    @Query(value = "select c from Book c ")
    public Page<Book> getListData(Pageable pageable);
}