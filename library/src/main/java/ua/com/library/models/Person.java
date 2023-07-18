package ua.com.library.models;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

/**
 * @author Anton Muzhytskyi
 */

public class Person {
	
	private int id;
	
	@NotEmpty(message = "Name shouldnot be empty")
	@Size(min = 2, max = 30, message = "Name should be between 2 and 30 characters")
	private String name;
	
	@Min(value = 1920, message = "Year of birth should be greater than 1920")
	private int yearOfBirth;
	
	
	public Person () {}
	
	public Person(
			@NotEmpty(message = "Name shouldnot be empty") @Size(min = 2, max = 30, message = "Name should be between 2 and 30 characters") String name,
			@Min(value = 1920, message = "Year of birth should be greater than 1920") int yearOfBirth) {
		this.name = name;
		this.yearOfBirth = yearOfBirth;
	}

	public int getId() {return id;}
	public void setId(int id) {this.id = id;}

	public String getName() {return name;}
	public void setName(String name) {this.name = name;}

	public int getYearOfBirth() {return yearOfBirth;}
	public void setYearOfBirth(int yearOfBirth) {this.yearOfBirth = yearOfBirth;}

	@Override
	public String toString() {
		return "Person [id=" + id + ", name=" + name + ", yearOfBirth=" + yearOfBirth + "]";
	}
}
