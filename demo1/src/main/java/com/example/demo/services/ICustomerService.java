package com.example.demo.services;

import com.example.demo.DTO.CustomerDTO;
import com.example.demo.exception.ResourceNotFoundException;

public interface ICustomerService {
    CustomerDTO getCustomerbyId(long customerId)throws ResourceNotFoundException;

}
