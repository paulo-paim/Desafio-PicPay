package com.picpaysimplificado.services;

import com.picpaysimplificado.domain.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.Map;

@Service
public class AuthorizationService {

    @Autowired
    private RestTemplate restTemplate;

    public boolean autorizeTransaction(User sender, BigDecimal value){
        ResponseEntity<Map> authorizationResponse =restTemplate.getForEntity("https://util.devi.tools/api/v2/authorize", Map.class);

        Map<String, Boolean> mapObject = (Map<String, Boolean>) authorizationResponse.getBody().get("data");

        if(mapObject.get("authorization") == Boolean.TRUE/*(boolean)authorizationResponse.getBody().get("data.authorization")*/){
            return true;
        }
        return false;
    }
}
