package github.tourism.config.util;

import com.siot.IamportRestClient.IamportClient;
import github.tourism.config.properties.IamportApiProperty;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class IamportConfig {

    private final IamportApiProperty apiProperty;

    @Bean
    public IamportClient iamportClient(IamportApiProperty iamportApiProperty) {
        return new IamportClient(iamportApiProperty.getImpKey()
                ,iamportApiProperty.getImpSecret());
    }
}
