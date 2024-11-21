package com.aluraChallenge.literatura.entities.Dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class BookDTO {
    @JsonAlias("id")
    public String gutendex_id;
    @JsonAlias("title")
    private String title;
    @JsonAlias("languages")
    private List<String> languages;
    @JsonAlias("authors")
    private List<AuthorDTO> authors;
    @JsonAlias("download_count")
    private Integer download_count;
}
