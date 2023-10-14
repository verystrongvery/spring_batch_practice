package com.example.springbatch.batch.reader;

import com.example.springbatch.batch.constants.BatchConstants;
import com.example.springbatch.domain.kobis.dto.KobisMovieRankResponseDto;
import com.example.springbatch.domain.kobis.service.KobisService;
import com.example.springbatch.domain.movierankhistory.entity.MovieRankHistory;
import com.example.springbatch.domain.movierankhistory.repository.MovieRankHistoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.annotation.BeforeStep;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.ItemReader;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
@RequiredArgsConstructor
public class SavePastDailyTotalMovieReader implements ItemReader<KobisMovieRankResponseDto> {
    private final MovieRankHistoryRepository movieRankHistoryRepository;
    private final KobisService kobisService;
    private ExecutionContext executionContext;

    @BeforeStep
    public void beforeStep(StepExecution stepExecution) {
        executionContext = stepExecution.getExecutionContext();
    }

    @Override
    public KobisMovieRankResponseDto read() {
        if (executionContext.get(BatchConstants.IS_READ) != null) {
            executionContext.remove(BatchConstants.IS_READ);
            return null;
        }
        executionContext.put(BatchConstants.IS_READ, true);
        List<MovieRankHistory> movieRankHistories = movieRankHistoryRepository.findAll();
        MovieRankHistory movieRankHistory;
        if (movieRankHistories.isEmpty()) {
            movieRankHistory = new MovieRankHistory();
            movieRankHistory.setPastDailyMovieStartDate(LocalDate.now().minusDays(1));
            movieRankHistoryRepository.save(movieRankHistory);
        }
        movieRankHistory = movieRankHistoryRepository.findAll()
                .get(0);
        return kobisService.findMovieRank(movieRankHistory.getPastDailyMovieStartDate(), null);
    }
}
