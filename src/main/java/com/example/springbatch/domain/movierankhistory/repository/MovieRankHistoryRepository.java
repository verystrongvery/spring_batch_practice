package com.example.springbatch.domain.movierankhistory.repository;

import com.example.springbatch.domain.movierankhistory.entity.MovieRankHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieRankHistoryRepository extends JpaRepository<MovieRankHistory, Long> {
}
