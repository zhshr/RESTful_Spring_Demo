/**
 * Book controller
 * Takes care of books RESTful API
 * 
 * Important: This controller doesn't check value collisions
 * i.e. there can be several books with same name or same ISBN
 * but not same id
 */

package com.example;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.undertow.attribute.RequestMethodAttribute;

@RestController
public class BookController {

	Map<Integer, Book> books;

	public BookController() {
		books = new HashMap<Integer, Book>();
	}

	@RequestMapping(value = "/books", method = RequestMethod.GET)
	public Map<Integer, Book> getBooks() {
		return books;
	}

	@RequestMapping(value = "/books", method = RequestMethod.DELETE)
	public void deleteBooks() {
		books = new HashMap<Integer, Book>();
	}

	@RequestMapping(value = "/books/{bookid}", method = RequestMethod.GET)
	public Book getBook(@PathVariable("bookid") int id) {
		return books.get(id);
	}

	@RequestMapping(value = "/books/{bookid}", method = RequestMethod.POST)
	public Book addBook(@PathVariable("bookid") int id, @RequestParam("name") String name,
			@RequestParam("ISBN") String ISBN) {
		Book temp = new Book(id, ISBN, name);
		if(books.get(id)!=null){
			//this id already exists, thus fails
			return null;
		}else{
			books.put(id, temp);
			return temp;
		}
		
	}

	@RequestMapping(value = "/books/{bookid}", method = RequestMethod.PUT)
	public Book modifyBook(@PathVariable("bookid") int id, @RequestParam(value = "name", required = false) String name,
			@RequestParam(value = "ISBN", required = false) String ISBN) {
		Book book = books.get(id);
		if (book == null){
			//this book doesn't exist, should use post instead of put
			return null;
		}
		if (name != null) {
			book.setName(name);
		}
		if (ISBN != null) {
			book.setISBN(ISBN);
		}
		return book;
	}
	@RequestMapping(value = "/books/{bookid}", method = RequestMethod.DELETE)
	public Book deleteBook(@PathVariable("bookid") int id){
		return books.remove(id);
	}
	
	@RequestMapping(value = "/books/ISBN/{isbn}", method = RequestMethod.GET)
	public Book findISBN(@PathVariable("isbn") String ISBN){
		for (Book book : books.values()){
			if (book.getISBN().equals(ISBN)){
				return book;
			}
		}
		return null;
	}
}
