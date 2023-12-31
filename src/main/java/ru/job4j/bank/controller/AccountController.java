package ru.job4j.bank.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import ru.job4j.bank.model.Account;
import ru.job4j.bank.service.BankService;
import java.util.Map;

@RestController
@RequestMapping("/account")
public class AccountController {
    private final BankService bankService;
    public AccountController(BankService bankService) {
        this.bankService = bankService;
    }

    @PostMapping
    public Account addAccount(@RequestBody Map<String, String> body) {
        var passport = body.get("passport");
        var account = new Account(body.get("requisite"), 0);
        bankService.addAccount(passport, account);
        return account;
    }

    @GetMapping
    public Account findByRequisite(@RequestParam String passport, @RequestParam String requisite) {
        return bankService.findByRequisite(passport, requisite)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Account is not found. Please, check requisites."
                ));
    }
}
