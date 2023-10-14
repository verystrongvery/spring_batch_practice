package com.example.springbatch.domain.movierankhistory.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDate;

@Entity
@Getter
@Setter
public class MovieRankHistory {
    @Id
    @GeneratedValue
    private Long id;
    private LocalDate pastDailyMovieStartDate;
}
