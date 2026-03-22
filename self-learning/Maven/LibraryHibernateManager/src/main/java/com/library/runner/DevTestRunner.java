package com.library.runner;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.library.model.Author;
import com.library.model.Book;
import com.library.model.BorrowedBook;
import com.library.model.User;
import com.library.service.AuthorService;
import com.library.service.BookService;
import com.library.service.BorrowService;
import com.library.service.UserService;

public class DevTestRunner {
	private static final Map<Class<?>, Object> services = new HashMap<>();
	
	static {
		services.put(User.class, new UserService());
		services.put(Book.class, new BookService());
		services.put(Author.class, new AuthorService());
		services.put(BorrowedBook.class, new BorrowService());
	}
	
	public void run() {
    	createSampleEntities();
    	testBookFindAll();
    	testBookUpdateTitleById("Head First Java: 2nd Edition");
    	testBookDeleteById();
    	testBorrowFindAll();
    	testBorrowFindByLibraryCardNumber();
	}
	
	@SuppressWarnings("unchecked")
	public <T, S> S getService(Class<T> entityClass) {
		S service = (S) services.get(entityClass);
		
		if (service == null) {
			throw new IllegalArgumentException("No serive registed for entity: " + entityClass.getSimpleName());
		}
		
		return service;
	}

	public void createSampleEntities() {
		UserService userService = getService(User.class);
		userService.createSampleUsers();

		BookService bookService = getService(Book.class);
		bookService.createSampleBooks();

		BorrowService borrowService = getService(BorrowedBook.class);
		borrowService.createSampleBorrows();
		
		System.out.println("Successfully Created Sample Entities");
	}
	
	public void testBookFindAll() {
		BookService bookService = getService(Book.class);
		List<Book> books = bookService.findAll();
		System.out.println("Book Titles: [");

//		if (books.isEmpty()) {
//			System.out.println("]");
//			return;
//		}

		for(Book b: books) {
			if (b != books.getLast())
				System.out.println("  " + b.getTitle() + ",");
		}
		System.out.println("  " + books.getLast().getTitle());
		System.out.println("]");
	}
	
	public void testBookUpdateTitleById(String newTitle) {
		BookService bookService = getService(Book.class);
		bookService.updateTitleById(4, newTitle);
		
		Book saved = bookService.findById(4);
		if (saved == null) {
			System.out.println("Error fetching persistent Book (id:4)");
			return;
		}
		
		if (saved.getTitle().equalsIgnoreCase(newTitle)) {
			System.out.println("Successfully updated Book(id:4) title to \"" + newTitle + "\".");
		} else {	
			System.out.println("Failed to update Book(id:4) title");
		}
	}
	
	public void testBookDeleteById() {
		BookService bookService = getService(Book.class);
		AuthorService authorService = getService(Author.class);
		
		Author author = new Author();
		author.setFirstName("Ken");
	    author.setLastName("Kousen");

	    Book book = new Book();
	    book.setTitle("Modern Java Recipes");
	    book.setIsbn("978-1491973172");
	    
	    book.getAuthors().add(author);
	    
	    bookService.save(book);
	    int bookId = book.getBookId();
	    System.out.println("Saved BookID: " + bookId);
	    
	    System.out.println("Deleting BookID: " + bookId);
	    bookService.deleteById(bookId);
	    
	    Book foundBook = bookService.findById(bookId);
	    if (foundBook == null) {
	        System.out.println("Book was removed from the database.");
	    }

	    List<Author> authors = authorService.findByLastNameIgnoreCase("Kousen");
	    if (!authors.isEmpty()) {
	        System.out.println("Author 'Ken Kousen' still exists in the database.");
	        System.out.println("Deleting the author also...");
	        authorService.deleteById(authors.get(0).getAuthorId());
	        System.out.println("Author 'Ken Kousen' deleted succesfully");
	    } else {
	        System.out.println("The author was deleted with the book");
	    }
	}
	
	public void testBorrowFindAll() {
		BorrowService borrowService = getService(BorrowedBook.class);
		
		List<BorrowedBook> borrows = borrowService.findAll();
		
		System.out.println("Borrow Records: [");
		borrows.forEach(b -> {
			if (b == borrows.getLast()) return;
			
			Book book = b.getBook();
			User user = b.getUser();
			
			System.out.println("  {");
			System.out.println("    \"book\": \"%s\",".formatted(book.getTitle()));
			System.out.println("    \"user\": \"%s %s\",".formatted(user.getFirstName(),
																	user.getLastName()));
			System.out.println("    \"borrow_date\": \"%s\"".formatted(b.getBorrowDate()));
			System.out.println("  },");
		});
		
		BorrowedBook b = borrows.getLast();
		Book book = b.getBook();
		User user = b.getUser();

		System.out.println("  {");
		System.out.println("    \"book\": \"%s\",".formatted(book.getTitle()));
		System.out.println("    \"user\": \"%s %s\",".formatted(user.getFirstName(),
				user.getLastName()));
		System.out.println("    \"borrow_date\": \"%s\"".formatted(b.getBorrowDate()));
		System.out.println("  }\n]");
	}
	
	public void testBorrowFindByLibraryCardNumber() {
		BorrowService borrowService = getService(BorrowedBook.class);
		
		String libraryCardNumber = "LC-1001";
		List<BorrowedBook> borrows = borrowService.findByLibraryCardNumber(libraryCardNumber);
		
		System.out.println("Borrow Records of Card Number: " + libraryCardNumber);
		System.out.println("[");
		borrows.forEach(b -> {
			if (b == borrows.getLast()) return;
			
			Book book = b.getBook();
			User user = b.getUser();
			
			System.out.println("  {");
			System.out.println("    \"book\": \"%s\",".formatted(book.getTitle()));
			System.out.println("    \"user\": \"%s %s\",".formatted(user.getFirstName(),
																	user.getLastName()));
			System.out.println("    \"borrow_date\": \"%s\"".formatted(b.getBorrowDate()));
			System.out.println("  },");
		});
		
		BorrowedBook b = borrows.getLast();
		Book book = b.getBook();
		User user = b.getUser();

		System.out.println("  {");
		System.out.println("    \"book\": \"%s\",".formatted(book.getTitle()));
		System.out.println("    \"user\": \"%s %s\",".formatted(user.getFirstName(),
				user.getLastName()));
		System.out.println("    \"borrow_date\": \"%s\"".formatted(b.getBorrowDate()));
		System.out.println("  }\n]");
	}
}
