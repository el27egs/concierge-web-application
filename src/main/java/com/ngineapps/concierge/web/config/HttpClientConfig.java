package com.ngineapps.concierge.web.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.web.OAuth2AuthorizedClientRepository;
import org.springframework.security.oauth2.client.web.reactive.function.client.ServletOAuth2AuthorizedClientExchangeFilterFunction;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class HttpClientConfig {

    /*
     * Do not use this client anymore. instead of this use
     * a webclient
     */
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    /*
     * The next configuration will add the jwt access token automatically
     * for all the calls, to avoid having a lot of code in service classese.
     * Note:
     * Please, take care not use this client to call external services due
     * it will send the access token, use only with internal calls.
     *
     *
     */
    @Bean("internalWebClient")
    public WebClient webClient(ClientRegistrationRepository clientRegistrationRepository,
                               OAuth2AuthorizedClientRepository oAuth2AuthorizedClientRepository) {

        ServletOAuth2AuthorizedClientExchangeFilterFunction oauth2 =
                new ServletOAuth2AuthorizedClientExchangeFilterFunction(clientRegistrationRepository,
                        oAuth2AuthorizedClientRepository);

        oauth2.setDefaultOAuth2AuthorizedClient(true);;

        return WebClient.builder().apply(oauth2.oauth2Configuration()).build();
    }
}
