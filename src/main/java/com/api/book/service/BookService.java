package com.api.book.service;

import com.api.book.dao.BookRepository;
import com.api.book.entities.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BookService
{
  @Autowired
    BookRepository bookRepository;

    //---- Create Book (Create from CRUD)

    public Book creatingNewBook(Book book)
    {
        return bookRepository.save(book);
    }

    //----- GET ALL Books (Read from CRUD) :

    public List<Book> getAllBooks()
    {
        return bookRepository.findAll();
    }

    //----- Get-Book-By-ID :

    public Book getBookById(Integer bid)
    {
         Optional<Book> book  =  bookRepository.findById(bid);

            return book.orElseThrow(()->new RuntimeException("Book not Found with ID : " + bid));
    }


    //----- DELETE Book

    public Book deleteBook(int bid)
    {
        Book deleteBook = getBookById(bid);
        bookRepository.deleteById(bid);

        return deleteBook;
    }


    //------ Update Book
    public void updatingBookData(Book book,int bid)
    {
        Book existingBook = getBookById(bid);

        existingBook.setId(book.getId());
        existingBook.setAuthor(book.getAuthor());
        existingBook.setTitle(book.getTitle());

        /*bookList.stream().map(id->{
                if(id.getId()==bid) {
                    id.setId(book.getId());
                    id.setTitle(book.getTitle());
                    id.setAuthor(book.getAuthor());
                }
            return book;

               }).collect(Collectors.toList());*/
    }



}
