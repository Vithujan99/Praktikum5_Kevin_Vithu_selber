package klaeffer.service;

import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;

import java.util.List;

public class ProfilService {
    private OAuth2AuthenticationToken auth;

    public ProfilService(OAuth2AuthenticationToken auth) {
        this.auth = auth;
    }

    public String getName() {
        return auth.getPrincipal().getAttribute("login");
    }

    public List<String> getPlan(){
        return null;
    }
}
