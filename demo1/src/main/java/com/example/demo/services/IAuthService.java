package com.example.demo.services;

import com.example.demo.DTO.RegisterCustomerRequest;
import com.example.demo.DTO.RegisterCustomerResponse;
import com.example.demo.exception.CustomerAlreadyExistException;

import java.util.List;

public interface IAuthService {
    public RegisterCustomerResponse register( RegisterCustomerRequest body) throws CustomerAlreadyExistException;
    public List<RegisterCustomerResponse> getList();
   public boolean existsByEmail(String email);
}
