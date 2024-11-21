package com.aluraChallenge.literatura.entities.Dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ResultsDTO {
    @JsonAlias("results")
    List<BookDTO> books;
}
