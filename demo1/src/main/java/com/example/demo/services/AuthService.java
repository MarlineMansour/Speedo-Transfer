package com.example.demo.services;
import com.example.demo.datddto.RegisterCustomerRequest;
import com.example.demo.datddto.RegisterCustomerResponse;
import com.example.demo.datddto.enums.AccountCurrency;
import com.example.demo.datddto.enums.AccountType;
import com.example.demo.entity.Account;
import com.example.demo.entity.Customer;
import com.example.demo.exception.CustomerAlreadyExistException;
import com.example.demo.repository.AccountRepository;
import com.example.demo.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthService implements IAuthService  {
    private final CustomerRepository customerRepository;
    private final AccountRepository accountRepository;

    public RegisterCustomerResponse register( RegisterCustomerRequest body)throws CustomerAlreadyExistException{
        if (Boolean.TRUE.equals(this.customerRepository.existsByEmail(body.getEmail()))) {
            throw new CustomerAlreadyExistException("Customer with email " + body.getEmail() + " already exists");
        }
        Customer customer = Customer.builder()
                .email(body.getEmail())
                .password(body.getPassword())
                .name(body.getName())
                .DateOfBirth(body.getDateOfBirth())
                .Country(body.getCountry())
                .build();

        Account account= Account.builder()
                .balance(0.0)
                .accountType(AccountType.SAVINGS)
                .accountDescription("Saving Account")
                .currency(AccountCurrency.EGP)
                .accountNumber(new SecureRandom().nextInt(10000000)+"")
                .customer(customer)
                .build();
        customer.getAccounts().add(account);

        Customer savedCustomer = customerRepository.save(customer);

        return savedCustomer.toResponse();
    }
    public List<RegisterCustomerResponse> getList(){
        return this.customerRepository.findAll().stream().map(Customer::toResponse).toList();
    }
    public boolean existsByEmail(String email) {
        return this.customerRepository.existsByEmail(email);
    }
}
