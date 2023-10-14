package com.example.springbatch.batch.listener;

import com.example.springbatch.domain.movierankhistory.entity.MovieRankHistory;
import com.example.springbatch.domain.movierankhistory.repository.MovieRankHistoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.annotation.AfterJob;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@RequiredArgsConstructor
@Component
public class SavePastDailyMovieJobListener {
    private final MovieRankHistoryRepository movieOpenApiHistoryRepository;

    @AfterJob
    public void afterJob(JobExecution jobExecution) {
        MovieRankHistory movieRankHistory = movieOpenApiHistoryRepository.findAll()
                .get(0);
        LocalDate theDayBefore = movieRankHistory.getPastDailyMovieStartDate()
                .minus(1, ChronoUnit.DAYS);
        movieRankHistory.setPastDailyMovieStartDate(theDayBefore);
        movieOpenApiHistoryRepository.save(movieRankHistory);
    }
}
