package com.example.spring.service.oauth;

import com.example.spring.repository.OauthClientsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.ClientRegistrationException;
import org.springframework.stereotype.Service;

@Service
public class Oauth2ClientDetailsService implements ClientDetailsService {

    @Autowired
    private OauthClientsRepository oauthClientsRepository;

    @Override
    public ClientDetails loadClientByClientId(String s) throws ClientRegistrationException {
        ClientDetails client = oauthClientsRepository.findOneByClientId(s);
        if (null == client) {
            throw new ClientRegistrationException("Client not found");
        }

        return client;
    }

    @CacheEvict("oauth_client_id")
    public void clearCache(String s) {
        System.out.println("ini cache  oauth_client_id=  "+s);
    }

}
