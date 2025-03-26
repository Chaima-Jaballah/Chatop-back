package com.chatop.chatop_backend.dto;

import lombok.Builder;

@Builder
public record AuthResponse(String token) {

}
