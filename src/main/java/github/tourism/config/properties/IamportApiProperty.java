package github.tourism.config.properties;

import jakarta.annotation.PostConstruct;
import lombok.Getter;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;


@Getter
@Configuration
public class IamportApiProperty {

    @Value("${imp.v1.api.key}")
    private String impKey;

    @Value("${imp.v1.api.secret}")
    private String impSecret;

    @PostConstruct
    public void init() {
        System.out.println("API Key: " + impKey);
        System.out.println("API Secret: " + impSecret);
    }

}
