package com.example.springbatch.domain.kobis.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class KobisBoxOfficeResponseDto {
    private String movieCd;
    private String movieNm;
    private String openDt;
    private String rnum;
    private String rankInten;
    private String rankOldAndNew;
    private String audiCnt;
    private String salesAmt;
    private String scrnCnt;
    private String showCnt;
}
