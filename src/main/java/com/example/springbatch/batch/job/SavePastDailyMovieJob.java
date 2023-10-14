package com.example.springbatch.batch.job;

import com.example.springbatch.batch.listener.SavePastDailyMovieJobListener;
import com.example.springbatch.batch.processor.SavePastDailyForeignMovieProcessor;
import com.example.springbatch.batch.processor.SavePastDailyKoreanMovieProcessor;
import com.example.springbatch.batch.reader.SavePastDailyTotalMovieReader;
import com.example.springbatch.batch.processor.SavePastDailyTotalMovieProcessor;
import com.example.springbatch.batch.reader.SavePastDailyForeignMovieReader;
import com.example.springbatch.batch.reader.SavePastDailyKoreanMovieReader;
import com.example.springbatch.batch.writer.SavePastDailyMovieWriter;
import com.example.springbatch.domain.kobis.dto.KobisMovieRankResponseDto;
import com.example.springbatch.domain.movierank.entity.MovieRank;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
@RequiredArgsConstructor
public class SavePastDailyMovieJob {
    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;
    private final SavePastDailyTotalMovieReader savePastDailyTotalMovieReader;
    private final SavePastDailyKoreanMovieReader savePastDailyKoreanMovieReader;
    private final SavePastDailyForeignMovieReader savePastDailyForeignMovieReader;
    private final SavePastDailyTotalMovieProcessor savePastDailyTotalMovieProcessor;
    private final SavePastDailyKoreanMovieProcessor savePastDailyKoreanMovieProcessor;
    private final SavePastDailyForeignMovieProcessor savePastDailyForeignMovieProcessor;
    private final SavePastDailyMovieWriter savePastDailyMovieWriter;
    private final SavePastDailyMovieJobListener savePastDailyMovieJobListener;

    // 과거의 일일 영화 순위 데이터 저장
    @Bean
    public Job SavePastDailyMovieJob() {
        return jobBuilderFactory.get("savePastDailyMovieJob")
                .start(SavePastDailyTotalMovieStep())
                .next(SavePastDailyKoreanMovieStep())
                .next(SavePastDailyForeignMovieStep())
                .listener(savePastDailyMovieJobListener)
                .build();
    }

    // 과거의 일일 전체 영화 순위 데이터 조회
    @Bean
    public Step SavePastDailyTotalMovieStep() {
        return stepBuilderFactory.get("SavePastDailyTotalMovieStep")
                .<KobisMovieRankResponseDto, List<MovieRank>>chunk(2)  //  chunk size: 한번에 처리될 트랜잭션 단위
                .reader(savePastDailyTotalMovieReader)
                .processor(savePastDailyTotalMovieProcessor)
                .writer(savePastDailyMovieWriter)
                .build();
    }

    // 과거의 일일 한국 영화 순위 데이터 조회
    @Bean
    public Step SavePastDailyKoreanMovieStep() {
        return stepBuilderFactory.get("SavePastDailyKoreainMovieStep")
                .<KobisMovieRankResponseDto, List<MovieRank>>chunk(2)  //  chunk size: 한번에 처리될 트랜잭션 단위
                .reader(savePastDailyKoreanMovieReader)
                .processor(savePastDailyKoreanMovieProcessor)
                .writer(savePastDailyMovieWriter)
                .build();
    }

    // 과거의 일일 외국 영화 순위 데이터 조회
    @Bean
    public Step SavePastDailyForeignMovieStep() {
        return stepBuilderFactory.get("SavePastDailyForeainMovieStep")
                .<KobisMovieRankResponseDto, List<MovieRank>>chunk(2)  //  chunk size: 한번에 처리될 트랜잭션 단위
                .reader(savePastDailyForeignMovieReader)
                .processor(savePastDailyForeignMovieProcessor)
                .writer(savePastDailyMovieWriter)
                .build();
    }
}
