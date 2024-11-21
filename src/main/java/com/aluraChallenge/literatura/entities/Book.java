package com.aluraChallenge.literatura.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "books")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bookId;

    private Long gutendex_id;
    private String title;
    private String languages;
    private Integer download_count;

    @ManyToOne
    private Author author;

}
