package com.example.springbatch.batch.processor;

import com.example.springbatch.batch.constants.BatchConstants;
import com.example.springbatch.batch.constants.MovieRankType;
import com.example.springbatch.common.utils.DateUtils;
import com.example.springbatch.common.utils.StringUtils;
import com.example.springbatch.domain.kobis.constants.KobisConstants;
import com.example.springbatch.domain.kobis.dto.KobisMovieRankResponseDto;
import com.example.springbatch.domain.movierank.entity.MovieRank;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class SavePastDailyKoreanMovieProcessor implements ItemProcessor<KobisMovieRankResponseDto, List<MovieRank>> {
    @Override
    public List<MovieRank> process(KobisMovieRankResponseDto item) {
        String showRange = item.getBoxOfficeResult()
                .getShowRange();
        String dateString = StringUtils.subStringUntil(showRange, KobisConstants.SHOW_RANGE_DELIMITER);
        LocalDate showRangeLocalDate = DateUtils.stringToLocalDate(dateString, BatchConstants.YYYYMMDD);
        return item.getBoxOfficeResult()
                .getDailyBoxOfficeList()
                .stream()
                .map(kobisBoxOfficeResponseDto -> MovieRank.createMovieRank(showRangeLocalDate, MovieRankType.KOREAN, kobisBoxOfficeResponseDto))
                .collect(Collectors.toList());
    }
}
