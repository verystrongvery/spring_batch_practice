package com.example.springbatch.domain.movierank.entity;

import com.example.springbatch.batch.constants.MovieRankType;
import com.example.springbatch.domain.kobis.dto.KobisBoxOfficeResponseDto;
import lombok.Getter;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import java.time.LocalDate;

@Getter
@Entity
public class MovieRank {
    @EmbeddedId
    private MovieRankId movieRankId;
    private String movieCd;
    private String movieNm;
    private String openDt;
    private String rankInten;
    private String rankOldAndNew;
    private String audiCnt;
    private String salesAmt;
    private String scrnCnt;
    private String showCnt;

    public static MovieRank createMovieRank(LocalDate showRangeLocalDate, MovieRankType movieRankType, KobisBoxOfficeResponseDto kobisBoxOfficeResponseDto) {
        MovieRank movieRank = new MovieRank();
        movieRank.movieRankId = new MovieRankId(showRangeLocalDate, Integer.parseInt(kobisBoxOfficeResponseDto.getRnum()), movieRankType);
        movieRank.movieCd = kobisBoxOfficeResponseDto.getMovieCd();
        movieRank.movieNm = kobisBoxOfficeResponseDto.getMovieNm();
        movieRank.openDt = kobisBoxOfficeResponseDto.getOpenDt();
        movieRank.rankInten = kobisBoxOfficeResponseDto.getRankInten();
        movieRank.rankOldAndNew = kobisBoxOfficeResponseDto.getRankOldAndNew();
        movieRank.audiCnt = kobisBoxOfficeResponseDto.getAudiCnt();
        movieRank.salesAmt = kobisBoxOfficeResponseDto.getSalesAmt();
        movieRank.scrnCnt = kobisBoxOfficeResponseDto.getScrnCnt();
        movieRank.showCnt = kobisBoxOfficeResponseDto.getShowCnt();
        return movieRank;
    }
}
