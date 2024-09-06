package com.example.demo.exception.response;


import lombok.Builder;

@Builder
public record ViolationErrors(String fieldName, String message) {
}