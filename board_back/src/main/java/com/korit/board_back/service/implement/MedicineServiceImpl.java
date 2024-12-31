package com.korit.board_back.service.implement;

import com.korit.board_back.common.ResponseMessage;
import com.korit.board_back.dto.ResponseDto;
import com.korit.board_back.dto.medicine.MedicineApi1ResponseDto;
import com.korit.board_back.dto.medicine.MedicineApi2ResponseDto;
import com.korit.board_back.dto.medicine.MedicineRequestDto;
import com.korit.board_back.dto.medicine.MedicineResponseDto;
import com.korit.board_back.service.MedicineService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

@Service
public class MedicineServiceImpl implements MedicineService {
    @Value("${drug.api.service-key}")
    String serviceKey;

    private final WebClient webClient;

    public MedicineServiceImpl(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.build();
    }

    @Override
    public ResponseDto<List<MedicineResponseDto>> searchMedicinesByName(MedicineRequestDto dto) {
        List<MedicineResponseDto> data = new ArrayList<>();

        String encodedServiceKey = URLEncoder.encode(serviceKey, StandardCharsets.UTF_8);

        try {
            var firstApiResponse = webClient.get()
                    .uri(uriBuilder -> uriBuilder
                            .scheme("https")
                            .host("apis.data.go.kr")
                            .path("/1471000/MdcinGrnIdntfcInfoService01/getMdcinGrnIdntfcInfoList01")
                            .queryParam("serviceKey", "jptKXkEhoWS2pwVQ34adwBGaLMbSQxl8jipaqrcP3oFbUD%2BVSG73q0mvxhSxJ46NK3v%2BsGLTPy0bH0oTQmuSdQ%3D%3D")
                            .queryParam("type", "json")
                            .queryParam("pageNo", 1)
                            .queryParam("numOfRows", 1)
                            .build())
                    .retrieve()
                    .bodyToMono(String.class)
                    .block();

            var secondApiResponse = webClient.get()
                    .uri(uriBuilder -> uriBuilder
                            .scheme("https")
                            .host("apis.data.go.kr")
                            .path("/1471000/DrbEasyDrugInfoService/getDrbEasyDrugList")
                            .queryParam("ServiceKey", "jptKXkEhoWS2pwVQ34adwBGaLMbSQxl8jipaqrcP3oFbUD%2BVSG73q0mvxhSxJ46NK3v%2BsGLTPy0bH0oTQmuSdQ%3D%3D")
                            .queryParam("type", "json")
                            .queryParam("pageNo", 1)
                            .queryParam("numOfRows", 1)
                            .build())
                    .retrieve()
                    .bodyToMono(String.class)
                    .block();

            System.out.println("Service Key: " + serviceKey);

            data.add(new MedicineApi1ResponseDto("API1", firstApiResponse));
            data.add(new MedicineApi2ResponseDto("API2", secondApiResponse));

        } catch (Exception e) {
            e.printStackTrace();
            ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);
        }

        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, data);
    }
}
