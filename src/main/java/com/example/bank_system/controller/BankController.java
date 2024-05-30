package com.example.bank_system.controller;

import com.example.bank_system.model.Account;
import com.example.bank_system.model.Bank;
import com.example.bank_system.model.Transaction;
import com.example.bank_system.service.BankService;
import com.example.bank_system.dto.TransferRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class BankController {
    @Autowired
    private BankService bankService;

    @PostMapping("/banks")
    public Bank createBank(@RequestBody Bank bank) {
        return bankService.createBank(bank.getName(), bank.getFlatFee(), bank.getPercentFee());
    }

    @PostMapping("/banks/{bankId}/accounts")
    public Account createAccount(@PathVariable Long bankId, @RequestBody Account account) {
        Bank bank = bankService.getBankById(bankId);
        return bankService.createAccount(bank, account.getName(), account.getBalance());
    }

    @PostMapping("/banks/{bankId}/transfer")
    public Transaction transfer(@PathVariable Long bankId, @RequestBody TransferRequest transferRequest)
            throws Exception {
        Bank bank = bankService.getBankById(bankId);
        Account from = bankService.getAccountById(transferRequest.getFromAccountId());
        Account to = bankService.getAccountById(transferRequest.getToAccountId());
        return bankService.transfer(bank, from, to, transferRequest.getAmount(), transferRequest.getReason(),
                transferRequest.isFlatFee());
    }

    @GetMapping("/accounts/{accountId}/balance")
    public double getAccountBalance(@PathVariable Long accountId) {
        Account account = bankService.getAccountById(accountId);
        return bankService.getAccountBalance(account);
    }

    @GetMapping("/banks/{bankId}/accounts")
    public List<Account> getAccounts(@PathVariable Long bankId) {
        Bank bank = bankService.getBankById(bankId);
        return bankService.getAccounts(bank);
    }

    @PostMapping("/accounts/{accountId}/deposit")
    public void depositMoney(@PathVariable Long accountId, @RequestParam double amount) {
        Account account = bankService.getAccountById(accountId);
        bankService.depositMoney(account, amount);
    }

    @PostMapping("/accounts/{accountId}/withdraw")
    public void withdrawMoney(@PathVariable Long accountId, @RequestParam double amount) throws Exception {
        Account account = bankService.getAccountById(accountId);
        bankService.withdrawMoney(account, amount);
    }

    @GetMapping("/accounts/{accountId}/transactions")
    public List<Transaction> getTransactions(@PathVariable Long accountId) {
        Account account = bankService.getAccountById(accountId);
        return bankService.getTransactions(account);
    }

    @GetMapping("/banks/{bankId}/total-fees")
    public double getTotalTransactionFees(@PathVariable Long bankId) {
        Bank bank = bankService.getBankById(bankId);
        return bankService.getTotalTransactionFees(bank);
    }

    @GetMapping("/banks/{bankId}/total-transfer-amount")
    public double getTotalTransferAmount(@PathVariable Long bankId) {
        Bank bank = bankService.getBankById(bankId);
        return bankService.getTotalTransferAmount(bank);
    }
}
