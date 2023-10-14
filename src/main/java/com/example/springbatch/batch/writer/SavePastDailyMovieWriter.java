package com.example.springbatch.batch.writer;

import com.example.springbatch.domain.movierank.entity.MovieRank;
import com.example.springbatch.domain.movierank.repository.MovieRankRepository;
import org.springframework.batch.item.database.JpaItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManagerFactory;
import java.util.List;

@Component
public class SavePastDailyMovieWriter extends JpaItemWriter<List<MovieRank>> {
    @Autowired
    private MovieRankRepository movieRankRepository;

    public SavePastDailyMovieWriter(EntityManagerFactory entityManagerFactory) {
        setEntityManagerFactory(entityManagerFactory);
    }

    @Override
    public void write(List<? extends List<MovieRank>> items) {
        items.forEach(itemList -> itemList.forEach((item) -> movieRankRepository.save(item)));
    }

}
