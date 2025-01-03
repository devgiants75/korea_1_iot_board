package com.korit.board_back.dto.medicine;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MedicineResponseDto {
    private String sourceApi; // API 구분
    private String medicineName; // 약품명 (검색한 약품 이름)
}
