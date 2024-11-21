package com.aluraChallenge.literatura.entities.Dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class AuthorDTO {
    @JsonAlias("id")
    private Long authorId;
    @JsonAlias("name")
    private String name;
    @JsonAlias("birth_year")
    private int birth_year;
    @JsonAlias("death_year")
    private int death_year;
}
