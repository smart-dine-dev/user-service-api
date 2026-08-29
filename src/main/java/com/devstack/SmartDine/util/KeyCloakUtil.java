package com.devstack.SmartDine.util;

import com.devstack.SmartDine.config.KeyCloakConfig;
import com.devstack.SmartDine.entity.enums.AuthProvider;
import com.devstack.SmartDine.exceptions.KeyCloakIntegrationException;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClient;

import java.rmi.Remote;
import java.util.List;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class KeyCloakUtil {
    private final RestClient keyCloakRestClient;
    private final KeyCloakConfig.KeyCloakProperties properties;

    private String createUser(String email, String password, String firstName, String lastName){
        String adminToken = getAdminToken();

        Map<String, Object> userPresentation = Map.of(
                "username", email,
                "email",email,
                "firstName", firstName,
                "lastName", lastName,
                "enabled",true,
                "emailVerified",false,
                "credentials", List.of(Map.of(
                        "type","password",
                        "value", password,
                        "temporary",false
                ))
        );

        var response = keyCloakRestClient.post()
                .uri("/admin/realms/{realm}/users",properties.getRealm())
                .header("Authorization", "Bearer "+adminToken)
                .contentType(MediaType.APPLICATION_JSON)
                .body(userPresentation)
                .retrieve()
                .toBodilessEntity();
        String location = response.getHeaders().getFirst("Location");
        if(location == null){
            throw new KeyCloakIntegrationException("location not found");
        }
        return location.substring(location.lastIndexOf("/")+1);
    }

    public String createSocialUser(String email, String firstName, String lastName, AuthProvider provider){
        String adminToken = getAdminToken();

        Map<String, Object> userPresentation = Map.of(
                "username", email,
                "email",email,
                "firstName", firstName,
                "lastName", lastName,
                "enabled",true,
                "emailVerified",true,
                "attributes", Map.of("provider",List.of(provider.name()))
        );

        var response = keyCloakRestClient.post()
                .uri("/admin/realms/{realm}/users",properties.getRealm())
                .header("Authorization", "Bearer "+adminToken)
                .contentType(MediaType.APPLICATION_JSON)
                .body(userPresentation)
                .retrieve()
                .toBodilessEntity();
        String location = response.getHeaders().getFirst("Location");
        if(location == null){
            throw new KeyCloakIntegrationException("location not found");
        }
        return location.substring(location.lastIndexOf("/")+1);
    }

    private Map<String, Object> authenticateUser(String email, String password){
        MultiValueMap<String, String> form = new LinkedMultiValueMap<>();
        form.add("grant_type","password");
        form.add("client_id", properties.getClientId());
        form.add("client_secret", properties.getSecretId());
        form.add("username",email);
        form.add("password",password);


        return  keyCloakRestClient.post()
                .uri("/realms/{realm}/protocol/openid-connect/token", properties.getRealm())
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .body(form)
                .retrieve()
                .body( new ParameterizedTypeReference<>() {});
    }

    private String getAdminToken(){
        MultiValueMap<String, String> form = new LinkedMultiValueMap<>();
        form.add("grant_type","password");
        form.add("client_id","admin-cli");
        form.add("username",properties.getAdmin().getUsername());
        form.add("password",properties.getAdmin().getPassword());

        Map<String, Object> response = keyCloakRestClient.post()
                .uri("/realms/master/protocol/openid-connect/token")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .body(form)
                .retrieve()
                .body( new ParameterizedTypeReference<>() {});

        return (String) response.get("access_token");

    }

}
