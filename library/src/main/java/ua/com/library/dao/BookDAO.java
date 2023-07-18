package ua.com.library.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import ua.com.library.models.Book;
import ua.com.library.models.Person;

/**
 * @author Anton Muzhytskyi
 */

@Component
public class BookDAO {

	private final JdbcTemplate jdbcTemplate;

	@Autowired
	public BookDAO(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public List<Book> index() {
		return jdbcTemplate.query("SELECT * FROM Book", new BeanPropertyRowMapper<>(Book.class));
	}

	public Book show(int id) {
		return jdbcTemplate.query("SELECT * FROM Book WHERE id=?", new Object[]{id}, new BeanPropertyRowMapper<>(Book.class))
				.stream().findAny().orElse(null);
	}

	public void save(Book book) {
		jdbcTemplate.update("INSERT INTO Book(book_name, author, year_of_print) VALUES(?, ?, ?)", book.getBookName(),book.getAuthor(), book.getYearOfPrint());
	}

	public void update(int id, Book updatedBook) {
		jdbcTemplate.update("UPDATE Book SET book_name=?, author=?, year_of_print=? WHERE id=?", updatedBook.getBookName(),
				updatedBook.getAuthor(), updatedBook.getYearOfPrint(),  id);
	}

	public void delete(int id) {
		jdbcTemplate.update("DELETE FROM Book WHERE id=?", id);
	}

	public Optional<Person> getBookOwner(int id){
		return jdbcTemplate.query("SELECT Person.name FROM Book JOIN Person ON Book.person_id = Person.id" +
				" WHERE Book.id=?", new Object[]{id}, new BeanPropertyRowMapper<>(Person.class)).stream().findAny();
	}

	public void release(int id) {
		jdbcTemplate.update("UPDATE Book SET person_id=NULL WHERE id=?", id);
	}

	public void assign (int id, Person selectedPerson) {
		jdbcTemplate.update("UPDATE Book SET person_id=? WHERE id=?", selectedPerson.getId(), id);
	}
}
 