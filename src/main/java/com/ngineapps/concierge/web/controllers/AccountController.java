package com.ngineapps.concierge.web.controllers;

import com.ngineapps.concierge.web.services.MovementService;
import com.ngineapps.concierge.web.dto.AccountMovementsResponseDTO;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class AccountController {

    private final MovementService movementService;

    public AccountController(MovementService movementService) {
        this.movementService = movementService;
    }

    @GetMapping("/account-details/{accountId}")
    public String getAccountDetails(Model model, @PathVariable String accountId, @AuthenticationPrincipal OidcUser principal) {

        AccountMovementsResponseDTO responseDTO = movementService.getMovements(accountId, principal);

        model.addAttribute("fullName", principal.getFullName());
        model.addAttribute("data", responseDTO);

        return "account-details";
    }

}


