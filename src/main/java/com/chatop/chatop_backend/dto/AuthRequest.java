package com.chatop.chatop_backend.dto;

import lombok.Builder;

@Builder
public record AuthRequest(String email,String password) {

}
