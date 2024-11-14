package com.korit.board_back.dto.store.request;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class StoreReviewRequestDto {
    // storeId를 기반으로 리뷰 데이터를 요청하는 DTO
    private Long storeId;
}
