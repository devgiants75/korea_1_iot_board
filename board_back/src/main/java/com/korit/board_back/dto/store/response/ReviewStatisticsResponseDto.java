package com.korit.board_back.dto.store.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class ReviewStatisticsResponseDto {
    // 리뷰 평점 (Ex. 0 ~ 5)
    private Integer rating;

    // 각 평점에 대한 리뷰 개수
    private Long reviewCount;
}
