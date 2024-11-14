package com.korit.board_back.service;

import com.korit.board_back.dto.store.response.ReviewStatisticsResponseDto;
import com.korit.board_back.repository.ExampleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ExampleService {

    private final ExampleRepository exampleRepository;

    public List<ReviewStatisticsResponseDto> getReviewStatistics (Long storeId) {

        List<ReviewStatisticsResponseDto> data = null;

        try {
            List<Object[]> results = exampleRepository.findReviewStatisticsByStoreId(storeId);

            data = results.stream()
                    .map(result -> new ReviewStatisticsResponseDto((Integer) result[0], (Long) result[1]))
                    .collect(Collectors.toList());

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        return data;
    }
}
