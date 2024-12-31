package com.korit.board_back.dto.medicine;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MedicineApi1ResponseDto extends MedicineResponseDto {

    public MedicineApi1ResponseDto(String sourceApi, String medicineName) {
        super(sourceApi, medicineName);
    }
}
