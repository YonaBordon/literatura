package com.aluraChallenge.literatura.application;

import com.aluraChallenge.literatura.entities.Author;
import com.aluraChallenge.literatura.entities.Book;
import com.aluraChallenge.literatura.entities.Dto.AuthorDTO;
import com.aluraChallenge.literatura.entities.Dto.ResultsDTO;
import com.aluraChallenge.literatura.service.AuthorService;
import com.aluraChallenge.literatura.service.BookService;
import com.aluraChallenge.literatura.utils.Screen;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

@Component
public class Application {
  private final Scanner scanner = new Scanner(System.in);
  private final AuthorService authorService;
  private final BookService bookService;


  @Autowired
  public Application(BookService bookService, AuthorService authorService) {
    this.bookService = bookService;
    this.authorService = authorService;
  }


  public void run(String... args) {
    menuManager();
  }

  public void menuManager() {
    Screen.screenWelcome();
    bookService.countBooksByLanguage("en");
    bookService.countBooksByLanguage("es");
    var option = -1;
    while (option != 0) {
      Screen.screenOption();
      option = scanner.nextInt();
      scanner.nextLine();

      switch (option) {
        case 1:
          //Buscar por titulo
          searchBookByTittle();
          break;
        case 2:
          //Lista todos libros
          printAllBooks();
          break;
        case 3:
          //Lista de autores
          printAllAuthors();
          break;
        case 4:
          //istar autores vivos en anio
          printAuthorsAliveInYear();
          break;
        case 0:
          //Salir
          Screen.screenGoodBye();
          break;
        default:
          //Opcion erronea mensaje
          Screen.screenInvalidOption();
      }
      waitingForInteraction();
    }
  }

  private void searchBookByTittle() {
    System.out.println("Ingrese el título del libro que deseas buscar:");
    String searchQuery = scanner.nextLine().trim();
    Screen.screenSeparation();

    try {
      ResultsDTO books = bookService.searchBooksByTitle(searchQuery);

      if (books.getBooks() == null || books.getBooks().isEmpty()) {
        System.out.println("No se encontraron libros con el título proporcionado.");
      } else {
        System.out.println("Resultados encontrados:");

        books.getBooks().forEach(book -> {
          bookService.saveBook(book);

          System.out.println("Título: " + book.getTitle());
          System.out.println("Idioma: " + book.getLanguages());
          System.out.println("Autor: " + String.join(", ", book.getAuthors()
                  .stream()
                  .map(AuthorDTO::getName)
                  .collect(Collectors.toList())));

          System.out.println("Descargas: " + book.getDownload_count());
          Screen.screenSeparation();
        });
      }
    } catch (Exception e) {
      System.out.println("Ocurrió un error al buscar el libro: " + e.getMessage());
    }
  }

  public void printAllBooks() {
    List<Book> books = bookService.getAllBooks();

    if (books.isEmpty()) {
      System.out.println("No hay libros en la base de datos.");
    } else {
      books.forEach(book -> {
        System.out.println("Título: " + book.getTitle());
        System.out.println("Idioma: " + book.getLanguages());
        System.out.println("Autor: " + book.getAuthor().getName());
        System.out.println("Descargas: " + book.getDownload_count());
        Screen.screenSeparation();
      });
    }
  }

  public void printAllAuthors() {
    List<Author> authors = authorService.listAllAuthors();

    if (authors.isEmpty()) {
      System.out.println("No hay autores en la base de datos.");
    } else {
      authors.forEach(author -> {
        System.out.println("Autor: " + author.getName());
        System.out.println("Fecha de nacimiento: " + author.getBirth_year());
        System.out.println("Fecha de fallecimiento: " + author.getDeath_year());
        Screen.screenSeparation();
      });
    }
  }

  public void printAuthorsAliveInYear() {
    System.out.println("Ingrese el año a buscar:");
    int year = scanner.nextInt();
    List<Author> authors = authorService.listAuthorsAliveInYear(year);

    if (authors.isEmpty()) {
      System.out.println("No hay autores vivos en el año " + year);
    } else {
      authors.forEach(author -> {
        System.out.println("Autor: " + author.getName());
        System.out.println("Fecha de nacimiento: " + author.getBirth_year());
        System.out.println("Fecha de fallecimiento: " + (author.getDeath_year() != null ? author.getDeath_year() : "Aún vivo"));
        Screen.screenSeparation();
      });
    }
  }

  private void waitingForInteraction() {
    System.out.println("Presione enter 2 veces para volver al menu inicial");
    scanner.nextLine();
  }


}
