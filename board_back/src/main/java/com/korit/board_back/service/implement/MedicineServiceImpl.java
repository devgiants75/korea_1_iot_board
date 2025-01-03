package com.korit.board_back.service.implement;

import com.korit.board_back.common.ResponseMessage;
import com.korit.board_back.dto.ResponseDto;
import com.korit.board_back.dto.medicine.MedicineApi1ResponseDto;
import com.korit.board_back.dto.medicine.MedicineApi2ResponseDto;
import com.korit.board_back.dto.medicine.MedicineResponseDto;
import com.korit.board_back.service.MedicineService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

@Service
public class MedicineServiceImpl implements MedicineService {

    private static final Logger logger = LoggerFactory.getLogger(MedicineServiceImpl.class);

    private final WebClient webClient;
    // private final String serviceKey;

    public MedicineServiceImpl(WebClient.Builder webClientBuilder, @Value("${drug.api.service-key}") String serviceKey) {
        this.webClient = webClientBuilder.build();
        // this.serviceKey = serviceKey;
    }

    @Override
    public ResponseDto<List<MedicineResponseDto>> searchMedicinesByName(
            String serviceKey,
            String medicineName,
            int pageNo,
            int numOfRows
    ) {
        List<MedicineResponseDto> data = new ArrayList<>();
        try {
            logger.info("Service Key: {}", serviceKey);

            // API 1 호출
            String firstApiResponse = callApi(
                    "https",
                    "apis.data.go.kr",
                    "/1471000/MdcinGrnIdntfcInfoService01/getMdcinGrnIdntfcInfoList01",
                    medicineName,
                    pageNo,
                    numOfRows,
                    "ITEM_NAME",
                    serviceKey
            );

            // API 2 호출
            String secondApiResponse = callApi(
                    "https",
                    "apis.data.go.kr",
                    "/1471000/DrbEasyDrugInfoService/getDrbEasyDrugList",
                    medicineName,
                    pageNo,
                    numOfRows,
                    "itemName",
                    serviceKey
            );

            data.add(new MedicineApi1ResponseDto("API1", firstApiResponse));
            data.add(new MedicineApi2ResponseDto("API2", secondApiResponse));

        } catch (IllegalArgumentException e) {
            logger.error("Invalid service key: {}", e.getMessage(), e);
            return ResponseDto.setFailed("Invalid API service key. Please check your configuration.");
        } catch (Exception e) {
            logger.error("Error occurred while searching for medicines: {}", e.getMessage(), e);
            return ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);
        }

        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, data);
    }

    private String callApi(
            String scheme,
            String host,
            String path,
            String medicineName,
            int pageNo,
            int numOfRows,
            String itemNameParam,
            String serviceKey
    ) {
        try {
            String response = webClient.get()
                    .uri(uriBuilder -> uriBuilder
                            .scheme(scheme)
                            .host(host)
                            .path(path)
                            .queryParam("serviceKey", serviceKey)
                            .queryParam(itemNameParam, URLEncoder.encode(medicineName, StandardCharsets.UTF_8))
                            .queryParam("pageNo", pageNo)
                            .queryParam("numOfRows", numOfRows)
                            .queryParam("type", "json")
                            .build())
                    .retrieve()
                    .onStatus(
                            status -> status.is4xxClientError() || status.is5xxServerError(),
                            clientResponse -> clientResponse.bodyToMono(String.class).map(body -> {
                                logger.error("API response error: {}", body);
                                throw new IllegalArgumentException("API returned an error: " + body);
                            })
                    )
                    .bodyToMono(String.class)
                    .block();

            logger.info("API Response for {}: {}", path, response);

            return response;

        } catch (Exception e) {
            logger.error("API call failed: {}", e.getMessage(), e);
            throw new RuntimeException("Failed to call external API", e);
        }
    }
}