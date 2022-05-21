/*
 *     Copyright 2022-Present Ngine Apps @ http://www.ngingeapps.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *          http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.ngineapps.concierge.web.services.impl;

import com.ngineapps.concierge.web.dto.AccountMovementsResponseDTO;
import com.ngineapps.concierge.web.services.MovementService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class MovementServiceImpl implements MovementService {

    private final RestTemplate restTemplate;

    private final WebClient webClient;

    private final OAuth2AuthorizedClientService oAuth2AuthorizedClientService;

    public MovementServiceImpl(OAuth2AuthorizedClientService oAuth2AuthorizedClientService, RestTemplate restTemplate,
                               @Qualifier("internalWebClient") WebClient webClient) {
        this.oAuth2AuthorizedClientService = oAuth2AuthorizedClientService;
        this.restTemplate = restTemplate;
        this.webClient = webClient;
    }

    @Override
    public AccountMovementsResponseDTO getMovements(final String accountId, @AuthenticationPrincipal OidcUser principal) {

        String url = "http://localhost:8008/api/v1/accounts/" + accountId + "/movements";

        AccountMovementsResponseDTO responseDTO = webClient.get().uri(url)
                .retrieve().bodyToMono(new ParameterizedTypeReference<AccountMovementsResponseDTO>() {})
                .block();

        return responseDTO;

        /*
         * 02 Deleting this in favor of web client use
         */
        /*
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        OAuth2AuthenticationToken oAuth2AuthenticationToken = (OAuth2AuthenticationToken) authentication;

        OAuth2AuthorizedClient oAuth2AuthorizedClient = oAuth2AuthorizedClientService.loadAuthorizedClient(
                oAuth2AuthenticationToken.getAuthorizedClientRegistrationId(),
                oAuth2AuthenticationToken.getName());

        String tokenValue = oAuth2AuthorizedClient.getAccessToken().getTokenValue();

        System.out.println("tokenValue = " + tokenValue);

        System.out.println("principal = " + principal);
        OidcIdToken idToken = principal.getIdToken();
        String idTokenValue = idToken.getTokenValue();
        System.out.println("idTokenValue = " + idTokenValue);

        String url = "http://localhost:8008/api/v1/accounts/" + accountId + "/movements";

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Authorization", "Bearer " + tokenValue);
        HttpEntity<AccountMovementsResponseDTO> httpEntity = new HttpEntity<>(httpHeaders);

        ResponseEntity<AccountMovementsResponseDTO> responseEntity = restTemplate
                .exchange(url, HttpMethod.GET, httpEntity,
                        new ParameterizedTypeReference<AccountMovementsResponseDTO>() {});

        return responseEntity.getBody();

         */

        /*
         * 01 Deleting this in favor of connecting to service
         */
        /*
        AccountMovementsResponseDTO.builder()
                .movements(Arrays.asList(
                        MovementResponseDTO.builder()
                                .concept(
                                        ConceptDTO.builder()
                                                .id(1L)
                                                .description("Ingress concept")
                                                .build()
                                )
                                .amount(BigDecimal.valueOf(100L))
                                .movementId("123f-wqe02321-123120")
                                .build(),
                        MovementResponseDTO.builder()
                                .concept(
                                        ConceptDTO.builder()
                                                .id(2L)
                                                .description("Egress concept")
                                                .build()
                                )
                                .amount(BigDecimal.valueOf(121L))
                                .movementId("123f-das231221-123120")
                                .build()
                ))
                .account(AccountResponseDTO.builder()
                        .accountName("501-Edificio-Administracion 2022")
                        .accountId(accountId)
                        .build())
                .build();
         */
    }
}
