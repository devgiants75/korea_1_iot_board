package com.korit.board_back.controller;

import com.korit.board_back.dto.ResponseDto;
import com.korit.board_back.dto.medicine.MedicineSearchByNameRequestDto;
import com.korit.board_back.dto.medicine.MedicineResponseDto;
import com.korit.board_back.service.MedicineService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/medicines")
@RequiredArgsConstructor
public class DrugApiController {
    private final MedicineService medicineService;

    @GetMapping("/search")
    public ResponseEntity<ResponseDto<List<MedicineResponseDto>>> searchMedicines(
            @RequestParam String serviceKey,
            @RequestParam String medicineName,
            @RequestParam int pageNo,
            @RequestParam int numOfRows
    ) {
        ResponseDto<List<MedicineResponseDto>> response = medicineService.searchMedicinesByName(serviceKey, medicineName, pageNo, numOfRows);
        HttpStatus status = response.isResult() ? HttpStatus.OK : HttpStatus.NOT_FOUND;
        return ResponseEntity.status(status).body(response);
    }
}
