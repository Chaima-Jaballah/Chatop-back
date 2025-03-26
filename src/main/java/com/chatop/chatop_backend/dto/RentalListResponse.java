package com.chatop.chatop_backend.dto;

import java.util.List;

import lombok.Builder;

@Builder
public record RentalListResponse(List<RentalResponse> rentals) {

}
