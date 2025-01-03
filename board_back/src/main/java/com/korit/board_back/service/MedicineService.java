package com.korit.board_back.service;

import com.korit.board_back.dto.ResponseDto;
import com.korit.board_back.dto.medicine.MedicineSearchByNameRequestDto;
import com.korit.board_back.dto.medicine.MedicineResponseDto;

import java.util.List;

public interface MedicineService {
    ResponseDto<List<MedicineResponseDto>> searchMedicinesByName(
            String serviceKey,
            String medicineName,
            int pageNo,
            int numOfRows
    );
}
