package com.korit.board_back.dto.medicine;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MedicineRequestDto {
    private String sourceApi;
    private String medicineName;


}
