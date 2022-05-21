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
package com.ngineapps.concierge.web.controllers.services.impl;

import com.ngineapps.concierge.web.controllers.services.AccountService;
import com.ngineapps.concierge.web.dto.AccountResponseDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Service;

import java.text.StringCharacterIterator;

@Service
@Slf4j
public class AccountServiceImpl implements AccountService {

    @Override
    public AccountResponseDTO getAccount(String accountId, OidcUser principal){
        return null;
    }
}
