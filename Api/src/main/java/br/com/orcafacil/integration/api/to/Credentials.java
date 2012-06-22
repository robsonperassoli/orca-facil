package br.com.orcafacil.integration.api.to;

import java.io.Serializable;
import org.codehaus.jackson.annotate.JsonAutoDetect;

@JsonAutoDetect
public class Credentials implements Serializable {
    
    private String email;
    private String apiKey;

    public Credentials() {
    }

    public Credentials(String email, String apiKey) {
        this.email = email;
        this.apiKey = apiKey;
    }

    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
}