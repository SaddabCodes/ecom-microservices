package com.sadcodes.ecommerce.order.client;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.support.RestClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

import org.springframework.beans.factory.annotation.Qualifier;
import java.util.Optional;

@Configuration
public class UserServiceClientConfig {

    @Bean("userServiceClient")
    public UserServiceClient userServiceClient(@Qualifier("loadBalancedRestClientBuilder") RestClient.Builder restClientBuilder) {
        RestClient restClient = restClientBuilder.baseUrl("http://user")
                .defaultStatusHandler(HttpStatusCode::is4xxClientError,
                        ((request, response) -> Optional.empty()))
                .build();

        RestClientAdapter adapter = RestClientAdapter.create(restClient);
        HttpServiceProxyFactory factory = HttpServiceProxyFactory.builderFor(adapter).build();

        return factory.createClient(UserServiceClient.class);
    }
}
