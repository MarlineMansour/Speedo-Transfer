package com.example.demo.services;

import com.example.demo.datddto.AccountDTO;
import com.example.demo.datddto.CreateAccountDTO;
import com.example.demo.datddto.enums.AccountCurrency;
import com.example.demo.datddto.enums.AccountType;
import com.example.demo.entity.Account;
import com.example.demo.entity.Customer;
import com.example.demo.exception.CustomerAlreadyExistException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.AccountRepository;
import com.example.demo.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
@Service
@RequiredArgsConstructor
public class AccountService implements IAccountService {
    private AccountRepository accountRepository;
    private CustomerRepository customerRepository;
    @Override
    public AccountDTO createAccount( CreateAccountDTO accountDTO)throws ResourceNotFoundException {
        Customer customer=this.customerRepository.findById(accountDTO.getCustomerId()).orElseThrow(()->new ResourceNotFoundException("Customer with Id " + accountDTO.getCustomerId() + " already exists"));
        Account account= Account.builder()
                .accountName(accountDTO.getAccountName())
                .balance(0.0)
                .accountType(AccountType.SAVINGS)
                .accountDescription("Saving Account")
                .currency(AccountCurrency.EGP)
                .accountNumber(new SecureRandom().nextInt(10000000)+"")
                .customer(customer)
                .build();
        Account savedAccount = this.accountRepository.save(account);
        return savedAccount.toDTO();
    }

    @Override
    public AccountDTO updateAccount(Long customerId, AccountDTO accountDTO) {
        return null;
    }

    @Override
    public AccountDTO getAccountById(Long accountId) throws ResourceNotFoundException{
        return this.accountRepository.findById(accountId)
                .orElseThrow(()->new ResourceNotFoundException("Customer with Id " + accountId + " already exists")).toDTO();
    }

    @Override
    public void deleteAccount(Long accountId) {

    }

    @Override
    public void deposit(Long accountId, Double amount) {

    }
}
