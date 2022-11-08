package com.example.spring.modules.user;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersRepo extends PagingAndSortingRepository<User, Long>, JpaSpecificationExecutor<User> {
    /*

   selet * from barang;
   1. native query :
     contoh : selet * from barang;

    */
    @Query(value = "select * from users c WHERE c.id = :idbarang", nativeQuery = true)
    Object getbyID(@Param("idbarang") Long idbebas);

    /*
    2. JPQL : JPA query : yang di slect adalah nama class variable

     */
    @Query(value = "select c from User c WHERE c.id = :idbarang", nativeQuery = false)
    User getbyIDByJPQL(@Param("idbarang") Long idbebas);

//    Page<User> findBySatuan(String satuan, Pageable pageable);

//    Page<User> findBySatuanAndHarga(String satuan, int harga, Pageable pageable);

//    Page<User> findBySatuanAndHargaOrNama(String satuan, int harga, String nama, Pageable pageable);

//    Page<User> findBySatuanAndHargaOrNamaLike(String satuan, int harga, String nama, Pageable pageable);//

    @Query(value = "select c from User c ")
    public Page<User> getListData(Pageable pageable);

}
