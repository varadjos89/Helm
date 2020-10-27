package edu.northeastern.ccwebapp.service;

import edu.northeastern.ccwebapp.Util.ResponseMessage;
import edu.northeastern.ccwebapp.pojo.Book;
import edu.northeastern.ccwebapp.repository.BookRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class BookService {

    private BookRepository bookRepository;
    private final static Logger logger = LogManager.getLogger(BookService.class);

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public ResponseEntity<?> addBookDetails(Book book) {
        ResponseMessage responseMessage = new ResponseMessage();
        Book bookDetails = new Book();
        if (book.getTitle() != null && book.getAuthor() != null
        		&& book.getQuantity() > 0) {
            bookDetails.setAuthor(book.getAuthor());
            bookDetails.setQuantity(book.getQuantity());
            bookDetails.setTitle(book.getTitle());
            UUID uuid = UUID.randomUUID();
            bookDetails.setId(uuid.toString());
            this.save(bookDetails);
            return new ResponseEntity<>(bookDetails, HttpStatus.CREATED);
        } else {
            responseMessage.setMessage("Invalid Title/ Author or Invalid JSON.");
            logger.warn("Invalid Title/ Author or Invalid JSON.");
            return new ResponseEntity<>(responseMessage, HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<?> getBooks() {
        List<Book> bookDetails;
        bookDetails = bookRepository.findAll();
        return new ResponseEntity<>(bookDetails, HttpStatus.OK);
    }

    public ResponseEntity<?> getBook(String bookId) {
        ResponseMessage responseMessage = new ResponseMessage();
        Book book = this.getBookById(bookId);
        if (book == null) {
            responseMessage.setMessage("Book with id " + bookId + " not found");
            logger.warn("Book with id " + bookId + " not found");
            return new ResponseEntity<>(responseMessage, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(book, HttpStatus.OK);
    }

    public ResponseEntity<?> updateBook(Book book) {
        ResponseMessage responseMessage = new ResponseMessage();
        Book currentBook = this.getBookById(book.getId());
        if (currentBook != null) {
            if (book.getAuthor() == null || book.getTitle() == null || book.getQuantity() <= 0) {
                responseMessage.setMessage("Invalid tittle/Author or an invalid Json format.");
                logger.info("Invalid Title/ Author or Invalid JSON.");
                return new ResponseEntity<>(responseMessage, HttpStatus.BAD_REQUEST);
            }
            currentBook.setTitle(book.getTitle());
            currentBook.setAuthor(book.getAuthor());
            currentBook.setQuantity(book.getQuantity());

            this.save(currentBook);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            responseMessage.setMessage("Book with id " + book.getId() + " not found");
            logger.info("Book with id " + book.getId() + " not found");
            return new ResponseEntity<>(responseMessage, HttpStatus.BAD_REQUEST);
        }
    }

    public Book getBookById(String id) {
        return bookRepository.findById(id);
    }

    public void save(Book book) {
        bookRepository.save(book);
    }

    public ResponseEntity<?> deleteBook(String id) {
        ResponseMessage responseMessage = new ResponseMessage();
        Book currentBook = this.getBookById(id);
        if (currentBook != null) {
            this.deleteBookById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
        	logger.info("Book with id " + id + " not found");
            responseMessage.setMessage("Book with id " + id + " not found");
        }
        return new ResponseEntity<>(responseMessage, HttpStatus.NOT_FOUND);
    }

    private void deleteBookById(String id) {
        bookRepository.deleteById(id);
    }

}

