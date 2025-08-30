package com.example.demo.controller.product.dto;

import com.example.demo.repository.product.entity.Review;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class ReviewResponseDto {

    private String status;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss") //formating 추가
    private LocalDateTime changedAt;

    public static ReviewResponseDto from(Review entity) {
        return new ReviewResponseDto(
                entity.getCurrentStatus().name(),
                entity.getChangedAt()
        );
    }
}
