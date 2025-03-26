package com.chatop.chatop_backend.dto;

import lombok.Builder;

@Builder
public record RegisterRequest(String email,String name, String password) {

}
