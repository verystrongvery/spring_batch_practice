package com.example.springbatch.domain.kobis.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class KobisResultResponseDto {
    private String showRange;
    private List<KobisBoxOfficeResponseDto> dailyBoxOfficeList;
}
