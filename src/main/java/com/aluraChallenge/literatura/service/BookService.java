package com.aluraChallenge.literatura.service;

import com.aluraChallenge.literatura.entities.Author;
import com.aluraChallenge.literatura.entities.Book;
import com.aluraChallenge.literatura.entities.Dto.ResultsDTO;
import com.aluraChallenge.literatura.entities.Dto.BookDTO;
import com.aluraChallenge.literatura.repositories.BookRepository;
import com.aluraChallenge.literatura.utils.JsonUtil;
import com.fasterxml.jackson.core.type.TypeReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

  private final FetchAPI fetchAPI;
  private final AuthorService authorService;
  private final BookRepository bookRepository;

  @Autowired
  public BookService(FetchAPI fetchAPI, AuthorService authorService, BookRepository bookRepository) {
    this.fetchAPI = fetchAPI;
    this.authorService = authorService;
    this.bookRepository = bookRepository;
  }

  public List<Book> getAllBooks() {
    return bookRepository.findAll();
  }


  public ResultsDTO searchBooksByTitle(String title) {
    try {
      String formattedQuery = title.replace(" ", "+");
      String results = fetchAPI.fetchData("/?search=" + formattedQuery);
      return JsonUtil.fromJson(results, new TypeReference<ResultsDTO>() {
      });
    } catch (Exception e) {
      throw new RuntimeException("Error al obtener los libros: " + e.getMessage());
    }
  }

  public void saveBook(BookDTO book) {
    book.getAuthors().forEach(authorDTO -> {
      Author author = authorService.getOrCreateAuthor(
              authorDTO.getName(),
              authorDTO.getBirth_year(),
              authorDTO.getDeath_year()
      );

      // Guardar el libro
      Book bookEntity = new Book();
      bookEntity.setGutendex_id(Long.parseLong(book.getGutendex_id()));
      bookEntity.setTitle(book.getTitle());
      bookEntity.setLanguages(String.join(", ", book.getLanguages()));
      bookEntity.setDownload_count(book.getDownload_count());
      bookEntity.setAuthor(author);
      bookRepository.save(bookEntity);
    });
  }

  public void countBooksByLanguage(String language) {

    long count = bookRepository.countByLanguagesContaining(language);

    System.out.println("Cantidad de libros en el idioma '" + language + "': " + count);
  }
}
