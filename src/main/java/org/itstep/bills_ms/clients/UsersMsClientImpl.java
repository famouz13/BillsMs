package org.itstep.bills_ms.clients;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import lombok.extern.slf4j.Slf4j;
import net.minidev.json.JSONObject;
import org.itstep.bills_ms.models.external.TokenResponse;
import org.itstep.bills_ms.models.external.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


import java.time.Duration;

@Component
@Slf4j
class UsersMsClientImpl implements UsersMsClient {

    private final static String ADMIN_EMAIL = "famouz13@gmail.com";
    private final static String ADMIN_PASSWORD = "famouz13";
    private final static String LOGIN_URL = "http://localhost:8080/api/v1/login";
    private final static String GET_USER_URL = "http://localhost:8080/api/v1/admin/users/";
    private final static String GET_USER_BY_TOKEN_URL = "http://localhost:8080/api/v1/admin/users/jwt";
    private String jwtToken;

    public UsersMsClientImpl() {
        try {
            RestTemplate restTemplate = new RestTemplate();
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            JSONObject loginDto = new JSONObject();
            loginDto.put("email", ADMIN_EMAIL);
            loginDto.put("password", ADMIN_PASSWORD);

            HttpEntity<String> request = new HttpEntity(loginDto.toString(), headers);

            TokenResponse result = restTemplate.postForObject(LOGIN_URL, request, TokenResponse.class);
            this.jwtToken = result.getToken();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public UserDto getUserById(Long id) {
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.add("Authorization", "Bearer " + jwtToken);

            HttpEntity request = new HttpEntity(headers);
            String url = GET_USER_URL + id;

            ResponseEntity<String> response = new RestTemplate().exchange(url, HttpMethod.GET, request, String.class);
            String json = response.getBody();

            return new Gson().fromJson(json, UserDto.class);

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public UserDto getUserByToken(String token) {
        try {
            RestTemplate restTemplate = new RestTemplate();
            HttpHeaders headers = new HttpHeaders();
            headers.add("Authorization", "Bearer " + this.jwtToken);
            headers.setContentType(MediaType.APPLICATION_JSON);

            JSONObject loginDto = new JSONObject();
            loginDto.put("token", token);

            HttpEntity<String> request = new HttpEntity(loginDto.toString(), headers);

            UserDto result = restTemplate.postForObject(GET_USER_BY_TOKEN_URL, request, UserDto.class);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


}
