package com.example.demo.services;

import com.example.demo.DTO.CustomerDTO;
import com.example.demo.exception.CustomerAlreadyExistException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerService implements ICustomerService {
    private final CustomerRepository customerRepository;
    @Override
    public CustomerDTO getCustomerbyId(long customerId) throws ResourceNotFoundException {
            return this.customerRepository.findById(customerId).
                    orElseThrow(()->new ResourceNotFoundException("Customer with id " + customerId + " notfound"))
                    .toDTO();
    }
}
