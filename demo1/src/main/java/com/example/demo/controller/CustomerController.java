package com.example.demo.controller;
import com.example.demo.DTO.CustomerDTO;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.exception.response.ErrorDetails;
import com.example.demo.services.ICustomerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/customers")
@RequiredArgsConstructor
@Validated
@Tag(name = "Customer Controller", description = "Customer controller")
public class CustomerController {

    private final ICustomerService customerService;
    @Operation(summary = "Get customer by id")
    @ApiResponse(responseCode = "200", content = {@Content(schema = @Schema(implementation = CustomerDTO.class), mediaType = "application/json")})
    @ApiResponse(responseCode = "400", content = {@Content(schema = @Schema(implementation = ErrorDetails.class), mediaType = "application/json")})
    @GetMapping("/{customerId}")
    public CustomerDTO getCustomerbyId(@PathVariable Long customerId)throws ResourceNotFoundException{
        return this.customerService.getCustomerbyId(customerId);
    }


}
