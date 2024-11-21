package com.aluraChallenge.literatura.service;

import com.aluraChallenge.literatura.entities.Author;
import com.aluraChallenge.literatura.repositories.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AuthorService {

  private final AuthorRepository authorRepository;

  @Autowired
  public AuthorService(AuthorRepository authorRepository) {
    this.authorRepository = authorRepository;
  }

  public List<Author> listAllAuthors() {
    return authorRepository.findAll();
  }

  public List<Author> listAuthorsAliveInYear(int year) {
    List<Author> authors = authorRepository.findAll();
    return authors.stream()
            .filter(author -> isAliveInYear(author, year))
            .collect(Collectors.toList());
  }

  private boolean isAliveInYear(Author author, int year) {
    return author.getBirth_year() <= year &&
            (author.getDeath_year() == null || author.getDeath_year() >= year);
  }


  public Author getOrCreateAuthor(String authorName, Integer birthYear, Integer deathYear) {
    Author author = authorRepository.findByName(authorName);

    if (author == null) {
      author = new Author();
      author.setName(authorName);
      author.setBirth_year(birthYear);
      author.setDeath_year(deathYear);
      author = authorRepository.save(author);
    }

    return author;
  }
}
