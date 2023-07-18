package ua.com.library.models;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

/**
 * @author Anton Muzhytskyi
 */

public class Book {

	private int id;
	
	@NotEmpty(message = "Name should not be empty")
	@Size(min = 2, max = 100, message = "Name should be between 2 and 100 characters")
	private String bookName;
	
	@NotEmpty(message = "Name should not be empty")
	@Size(min = 2, max = 100, message = "Name should be between 2 and 100 characters")
	private String author;
	
	@Min(value = 0, message = "Year of print should be greater than 0")
	private int yearOfPrint;

	
	public Book() {}

	public Book(
			@NotEmpty(message = "Name should not be empty") @Size(min = 2, max = 100, message = "Name should be between 2 and 100 characters") String bookName,
			@NotEmpty(message = "Name should not be empty") @Size(min = 2, max = 100, message = "Name should be between 2 and 100 characters") String author,
			@Min(value = 0, message = "Year of print should be greater than 0") int yearOfPrint) {
		this.bookName = bookName;
		this.author = author;
		this.yearOfPrint = yearOfPrint;
	}

	public int getId() {return id;}
	public void setId(int id) {this.id = id;}

	public String getBookName() {return bookName;}
	public void setBookName(String bookName) {this.bookName = bookName;}

	public String getAuthor() {return author;}
	public void setAuthor(String author) {this.author = author;}

	public int getYearOfPrint() {return yearOfPrint;}
	public void setYearOfPrint(int yearOfPrint) {this.yearOfPrint = yearOfPrint;}

	@Override
	public String toString() {
		return "Book [id=" + id + ", bookName=" + bookName + ", author=" + author + ", yearOfPrint=" + yearOfPrint
				+ "]";
	}
}
