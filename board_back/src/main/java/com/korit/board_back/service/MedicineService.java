package com.korit.board_back.service;

import com.korit.board_back.dto.ResponseDto;
import com.korit.board_back.dto.medicine.MedicineRequestDto;
import com.korit.board_back.dto.medicine.MedicineResponseDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface MedicineService {
    ResponseDto<List<MedicineResponseDto>> searchMedicinesByName(MedicineRequestDto dto);
}
