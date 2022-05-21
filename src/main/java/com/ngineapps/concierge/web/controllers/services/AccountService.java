package com.ngineapps.concierge.web.controllers.services;

import com.ngineapps.concierge.web.dto.AccountResponseDTO;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;

public interface AccountService {

    AccountResponseDTO getAccount(String accountId, OidcUser principal);
}