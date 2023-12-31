package ua.com.library.controllers;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import ua.com.library.dao.BookDAO;
import ua.com.library.dao.PersonDAO;
import ua.com.library.models.Book;
import ua.com.library.models.Person;

/**
 * @author Anton Muzhytskyi
 */

@Controller
@RequestMapping("/books")
public class BooksController {
	
	private final BookDAO bookDAO;
	private final PersonDAO personDAO;
	
	@Autowired
	public BooksController(BookDAO bookDAO, PersonDAO personDAO) {
		super();
		this.bookDAO = bookDAO;
		this.personDAO = personDAO;
	}
	
	@GetMapping() 
	public String index (Model model) {
		model.addAttribute("books", bookDAO.index());
		return "books/index";
	}
	
	@GetMapping("/{id}")
	public String show(@PathVariable("id") int id, Model model, @ModelAttribute("person") Person person) {
		model.addAttribute("book", bookDAO.show(id));
		
		Optional<Person> bookOwner = bookDAO.getBookOwner(id);
	
		if(bookOwner.isPresent()) model.addAttribute("owner", bookOwner.get());
		else model.addAttribute("people", personDAO.index());
		return "books/show";
	}
	
	
	@GetMapping("/new")
    public String newBook(@ModelAttribute("book") Book book) {
        return "books/new";
    }
		
	@PostMapping()
    public String create(@ModelAttribute("books") @Valid Book book, BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "books/new";

        bookDAO.save(book);
        return "redirect:/books";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("book", bookDAO.show(id));
        return "books/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("books") @Valid Book book, BindingResult bindingResult,
                         @PathVariable("id") int id) {
        if (bindingResult.hasErrors())
            return "books/edit";

        bookDAO.update(id, book);
        return "redirect:/books";
    }
	
	@DeleteMapping("/{id}")
	public String delete(@PathVariable("id") int id) {
		bookDAO.delete(id);
		return "redirect:/books";
	}
	
	@PatchMapping("/{id}/release")
	public String release(@PathVariable("id") int id) {
		bookDAO.release(id);
		return "redirect:/books/" + id;
		
	}
	
	@PatchMapping("/{id}/assign")
	public String assign(@PathVariable("id") int id, @ModelAttribute("person") Person selectedPerson) {
		bookDAO.assign(id, selectedPerson);
		return "redirect:/books/" + id;
	}
}
