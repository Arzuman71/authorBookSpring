package com.example.authorbookspring.cotroller;


import com.example.authorbookspring.model.Author;
import com.example.authorbookspring.model.Book;
import com.example.authorbookspring.repository.AuthorRepository;
import com.example.authorbookspring.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class BookController {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

    @GetMapping("/bookHome")
    public String bookHomePage(ModelMap modelMap) {
        List<Book> allBooks = bookRepository.findAll();
        modelMap.addAttribute("books", allBooks);
        return "bookHome";
    }


    @GetMapping("/deleteBook")
    public String deleteBook(@RequestParam("id") int id) {
        bookRepository.deleteById(id);
        return "redirect:/bookHome";
    }

    @PostMapping("/saveBook")
    public String add(@ModelAttribute Book book) {
        String msg = book.getId() > 0 ? "Book was updated" : "Book was added";
        bookRepository.save(book);
        return "redirect:/?msg=" + msg;
    }


    @GetMapping("/bookById")
    public String bookById(ModelMap modelMap, @RequestParam("id") int id) {

        Book book = bookRepository.getOne(id);
        modelMap.addAttribute("book", book);
        return "/change";

    }



}

