package com.example.springbatch.domain.movierank.entity;

import com.example.springbatch.batch.constants.MovieRankType;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.io.Serializable;
import java.time.LocalDate;

@Embeddable
@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class MovieRankId implements Serializable {
    private LocalDate date;
    private Integer rank;
    @Enumerated(EnumType.STRING)
    private MovieRankType movieRankType;
}
