package com.korit.board_back.dto.medicine;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MedicineApi2ResponseDto extends MedicineResponseDto{

    public MedicineApi2ResponseDto(String sourceApi, String medicineName) {
        super(sourceApi, medicineName);
    }
}
