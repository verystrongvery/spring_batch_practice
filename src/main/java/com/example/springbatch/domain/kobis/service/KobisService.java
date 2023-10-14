package com.example.springbatch.domain.kobis.service;

import com.example.springbatch.batch.constants.BatchConstants;
import com.example.springbatch.batch.constants.BatchErrorMessage;
import com.example.springbatch.domain.kobis.constants.KobisConstants;
import com.example.springbatch.domain.kobis.dto.KobisMovieRankResponseDto;
import com.example.springbatch.domain.webclient.service.WebClientService;
import com.example.springbatch.common.utils.DateUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class KobisService {
    @Value("${kobis.api.key}")
    private String KOBIS_API_KEY;

    private final WebClientService webClientService;

    public KobisMovieRankResponseDto findMovieRank(LocalDate date, String repNationCd) {
        MultiValueMap<String, String> params = createParams(date, repNationCd);
        KobisMovieRankResponseDto kobisMovieRankResponseDto = webClientService.get(KobisConstants.DAILY_BOX_OFFICE_PATH, params, KobisMovieRankResponseDto.class);
        if (kobisMovieRankResponseDto.getBoxOfficeResult() == null) {
            throw new IllegalStateException(BatchErrorMessage.KOBIS_CALL_FAIL);
        }
        return kobisMovieRankResponseDto;
    }

    private MultiValueMap<String, String> createParams(LocalDate date, String repNationCd) {
        String dateString = DateUtils.localDateToString(date, BatchConstants.YYYYMMDD);
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("key", KOBIS_API_KEY);
        params.add("targetDt", dateString);
        if (repNationCd != null && (repNationCd.equals(KobisConstants.KOREAN_MOVIE) || repNationCd.equals(KobisConstants.FOREIGN_MOVIE))) {
            params.add("repNationCd", repNationCd);
        }
        return params;
    }
}
