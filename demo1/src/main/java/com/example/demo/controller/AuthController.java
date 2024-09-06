package com.example.demo.controller;
import com.example.demo.datddto.RegisterCustomerRequest;
import com.example.demo.datddto.RegisterCustomerResponse;
import com.example.demo.exception.CustomerAlreadyExistException;
import com.example.demo.exception.response.ErrorDetails;
import com.example.demo.services.IAuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController
@RequestMapping("api/v1/auth")
@RequiredArgsConstructor
@Validated
@Tag(name = "Customer Auth Controller", description = "Customer Auth controller")
public class AuthController {
    private final IAuthService authService;
    @PostMapping("/register")
    @Operation(summary = "Register new Customer")
    @ApiResponse(responseCode = "200", content = {@Content(schema = @Schema(implementation = RegisterCustomerResponse.class), mediaType = "application/json")})
    @ApiResponse(responseCode = "400", content = {@Content(schema = @Schema(implementation = ErrorDetails.class), mediaType = "application/json")})
    public RegisterCustomerResponse postDemo( @Valid @RequestBody RegisterCustomerRequest body) throws CustomerAlreadyExistException {
     return this.authService.register(body);
    }
    @GetMapping("/log")
    public List<RegisterCustomerResponse> getList(){
        return this.authService.getList();
    }
    @GetMapping("/{email}")
    public boolean existEmail(@PathVariable String email){
        return this.authService.existsByEmail(email);
    }
}
