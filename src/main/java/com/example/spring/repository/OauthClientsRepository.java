package com.example.spring.repository;

import com.example.spring.entity.OauthClient;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OauthClientsRepository extends PagingAndSortingRepository<OauthClient, Long>, JpaSpecificationExecutor<OauthClient> {
    OauthClient findOneByClientId(String clientId);
}
