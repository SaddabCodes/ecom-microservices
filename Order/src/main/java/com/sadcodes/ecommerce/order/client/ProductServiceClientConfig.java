package com.sadcodes.ecommerce.order.client;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.support.RestClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

import java.util.Optional;

@Configuration
class ProductServiceClientConfig {

    @Bean
    @Primary
    public RestClient.Builder restClientBuilder() {
        return RestClient.builder();
    }

    @Bean
    @LoadBalanced
    public RestClient.Builder productServiceRestClientBuilder() {
        return RestClient.builder();
    }

    @Bean
    public ProductServiceClient restClientInterface(@Qualifier("productServiceRestClientBuilder") RestClient.Builder restClientBuilder) {
        RestClient restClient = restClientBuilder.baseUrl("http://product").defaultStatusHandler(HttpStatusCode::is4xxClientError, ((request, response) -> Optional.empty())).build();

        RestClientAdapter adapter = RestClientAdapter.create(restClient);
        HttpServiceProxyFactory factory = HttpServiceProxyFactory.builderFor(adapter).build();

        ProductServiceClient productServiceClient = factory.createClient(ProductServiceClient.class);
        return productServiceClient;
    }
}
