package com.korit.board_back.controller;

import com.korit.board_back.dto.ResponseDto;
import com.korit.board_back.dto.medicine.MedicineRequestDto;
import com.korit.board_back.dto.medicine.MedicineResponseDto;
import com.korit.board_back.service.MedicineService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/medicines")
@RequiredArgsConstructor
public class DrugApiController {
    private final MedicineService medicineService;

    @PostMapping("/search")
    public ResponseEntity<ResponseDto<List<MedicineResponseDto>>> searchMedicines(@RequestBody MedicineRequestDto requestDto) {
        ResponseDto<List<MedicineResponseDto>> response = medicineService.searchMedicinesByName(requestDto);
        HttpStatus status = response.isResult() ? HttpStatus.OK : HttpStatus.NOT_FOUND;
        return ResponseEntity.status(status).body(response);
    }
}
