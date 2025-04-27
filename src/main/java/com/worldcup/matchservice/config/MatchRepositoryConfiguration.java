package com.worldcup.matchservice.config;

import com.worldcup.matchservice.repository.MatchRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.client.support.RestClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

@Configuration
public class MatchRepositoryConfiguration {
    @Value("${db-service.url}")
    private String dbServiceUrl;

    @Bean("matchServiceRestClient")
    public RestClient matchServiceRestClient() {
        return RestClient.builder()
                .baseUrl(dbServiceUrl)
                .build();
    }

    @Bean
    public MatchRepository matchRepository(@Qualifier("matchServiceRestClient") RestClient restClient) {
        RestClientAdapter adapter = RestClientAdapter.create(restClient);
        HttpServiceProxyFactory factory = HttpServiceProxyFactory.builderFor(adapter).build();

        return factory.createClient(MatchRepository.class);
    }
}
