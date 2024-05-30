package com.example.bank_system.service;

import com.example.bank_system.exception.BankServiceException;
import com.example.bank_system.model.Account;
import com.example.bank_system.model.Bank;
import com.example.bank_system.model.Transaction;
import com.example.bank_system.repository.AccountRepository;
import com.example.bank_system.repository.BankRepository;
import com.example.bank_system.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class BankService {
    @Autowired
    private BankRepository bankRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    public Bank createBank(String name, double flatFee, double percentFee) {
        Bank bank = new Bank();
        bank.setName(name);
        bank.setFlatFee(flatFee);
        bank.setPercentFee(percentFee);
        return bankRepository.save(bank);
    }

    public Account createAccount(Bank bank, String name, double initialBalance) {
        Account account = new Account();
        account.setName(name);
        account.setBalance(initialBalance);
        account = accountRepository.save(account);

        bank.getAccounts().add(account);
        bankRepository.save(bank);

        return account;
    }

    @Transactional
    public void depositMoney(Account account, double amount) {
        account.setBalance(account.getBalance() + amount);
        accountRepository.save(account);
    }

    @Transactional
    public void withdrawMoney(Account account, double amount) {
        if (account.getBalance() < amount) {
            throw new BankServiceException("Insufficient funds");
        }
        account.setBalance(account.getBalance() - amount);
        accountRepository.save(account);
    }

    @Transactional
    public Transaction transfer(Bank bank, Account from, Account to, double amount, String reason, boolean isFlatFee) {
        if (from.getBalance() < amount) {
            throw new BankServiceException("Insufficient funds");
        }

        double fee = isFlatFee ? bank.getFlatFee() : (amount * bank.getPercentFee() / 100);
        double totalAmount = amount + fee;

        if (from.getBalance() < totalAmount) {
            throw new BankServiceException("Insufficient funds to cover fee");
        }

        from.setBalance(from.getBalance() - totalAmount);
        to.setBalance(to.getBalance() + amount);

        Transaction transaction = new Transaction();
        transaction.setAmount(amount);
        transaction.setReason(reason);
        transaction.setOriginatingAccount(from);
        transaction.setResultingAccount(to);
        transactionRepository.save(transaction);

        bank.setTotalTransactionFees(bank.getTotalTransactionFees() + fee);
        bank.setTotalTransferAmount(bank.getTotalTransferAmount() + amount);
        bankRepository.save(bank);

        accountRepository.save(from);
        accountRepository.save(to);

        return transaction;
    }

    public List<Account> getAccounts(Bank bank) {
        return bank.getAccounts();
    }

    public List<Transaction> getTransactions(Account account) {
        return account.getTransactions();
    }

    public double getAccountBalance(Account account) {
        return account.getBalance();
    }

    public double getTotalTransactionFees(Bank bank) {
        return bank.getTotalTransactionFees();
    }

    public double getTotalTransferAmount(Bank bank) {
        return bank.getTotalTransferAmount();
    }

    public Bank getBankById(Long bankId) {
        return bankRepository.findById(bankId).orElseThrow(() -> new BankServiceException("Bank not found"));
    }

    public Account getAccountById(Long accountId) {
        return accountRepository.findById(accountId).orElseThrow(() -> new BankServiceException("Account not found"));
    }
}
