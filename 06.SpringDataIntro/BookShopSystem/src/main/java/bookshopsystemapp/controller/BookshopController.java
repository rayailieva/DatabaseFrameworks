package bookshopsystemapp.controller;

import bookshopsystemapp.service.AuthorService;
import bookshopsystemapp.service.BookService;
import bookshopsystemapp.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class BookshopController implements CommandLineRunner {

    private final AuthorService authorService;
    private final CategoryService categoryService;
    private final BookService bookService;

    @Autowired
    public BookshopController(AuthorService authorService, CategoryService categoryService, BookService bookService) {
        this.authorService = authorService;
        this.categoryService = categoryService;
        this.bookService = bookService;
    }

    @Override
    public void run(String... strings) throws Exception {
        //this.authorService.seedAuthors();
        //this.categoryService.seedCategories();
        //this.bookService.seedBooks();

       // List<String> result = this.bookService.getAllBooksByReleaseDateAfter();
        //result.forEach(System.out::println);

        //List<String> result = this.bookService.getAllAuthorsWithBookAfter();
        //result.forEach(System.out::println);

        List<String> result = this.authorService.getAuthorByCountOfBooks();
        result.forEach(System.out::println);

    }

}