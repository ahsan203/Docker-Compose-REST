package com.api.book.controllers;

import com.api.book.entities.Book;
import com.api.book.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class BookController
{
    private BookService bookService;

    @Autowired
    public BookController(BookService bookService)
    {
        this.bookService = bookService;
    }


    @PostMapping("/books")
    public Book createBook(@RequestBody Book book)
    {
        Book newBook = bookService.creatingNewBook(book);
        System.out.println(newBook);
        return newBook;
    }


    @RequestMapping(value="/books", method = RequestMethod.GET)
    //@ResponseBody
    public List<Book> getBooks()
    {
        List<Book> allBooks = bookService.getAllBooks();
        return allBooks;
    }


    /*@GetMapping("/books/{bid}")
    public Book getBookById(@PathVariable int bid)
    {
        Book bookById = bookService.getBookById(bid);

        return bookById;
    }*/

    @GetMapping("/books/{bid}")
    public ResponseEntity<Book> getBookById(@PathVariable int bid)
    {
        try {
            Book bookById = bookService.getBookById(bid);
            return ResponseEntity.of(Optional.of(bookById));
        }
        catch (Exception e)
        {
            System.out.println("\"Internal Server Error\"");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }




//------- Without using ResponseEntity......
    /*@DeleteMapping("/books/{bookId}")
    public Book deleteBookById(@PathVariable int bookId)
    {
        Book deletedBook = bookService.delteBook(bookId);

        return deletedBook;
    }
*/
    @DeleteMapping("/books/{bookId}")
    public ResponseEntity<Book> deleteBookById(@PathVariable int bookId)
    {
        try {
            Book deletedBook = bookService.deleteBook(bookId);
            return ResponseEntity.of(Optional.of(deletedBook));
        }
        catch (Exception e)
        {
            System.out.println("Internal Server Error");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }


    //--- Update the book-data
    @PutMapping("/books/{bookId}")
    public ResponseEntity<Void> updateBook(@RequestBody Book book, @PathVariable int bookId)
    {
        try
        {
            this.bookService.updatingBookData(book,bookId);
           return ResponseEntity.status(HttpStatus.CREATED).build();
        }
        catch (Exception e)
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

    }
}
