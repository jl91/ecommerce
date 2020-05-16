package com.profectusweb.ecommerce.configuration;

import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.boot.autoconfigure.elasticsearch.ElasticsearchRestClientProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.client.ClientConfiguration;
import org.springframework.data.elasticsearch.client.RestClients;
import org.springframework.data.elasticsearch.config.AbstractElasticsearchConfiguration;

@Configuration
public class ElasticsearchConfiguration extends AbstractElasticsearchConfiguration {

    private final ElasticsearchRestClientProperties properties;

    public ElasticsearchConfiguration(ElasticsearchRestClientProperties properties) {
        this.properties = properties;
    }

    @Override
    @Bean
    public RestHighLevelClient elasticsearchClient() {

        String uri = properties.getUris().iterator().next();
        String username =  properties.getUsername();
        String password = properties.getPassword();

        final ClientConfiguration clientConfiguration = ClientConfiguration.builder()
                .connectedTo(uri)
                .withBasicAuth(username, password)
                .build();

        return RestClients.create(clientConfiguration).rest();
    }
}
