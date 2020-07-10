package com.example.authorbookspring.cotroller;


import com.example.authorbookspring.model.Book;
import com.example.authorbookspring.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Controller
public class BookController {
    @Autowired
    private BookRepository bookRepository;

    @GetMapping("/bookHome")
    public String bookHomePage(ModelMap modelMap) {
        List<Book> allBooks = bookRepository.findAll();
        modelMap.addAttribute("books", allBooks);
        return "bookHome";
    }

    @GetMapping("/home")
    public String aboutPage() {

        return "redirect:/";
    }

    @GetMapping("/deleteBook")
    public String deleteBook(@RequestParam("id") int id) {
        bookRepository.deleteById(id);
        return "redirect:/bookHome";
    }

    @PostMapping("/addBook")
    public String addUser(@ModelAttribute Book book) {

        bookRepository.save(book);
        return "redirect:/";
    }


    @GetMapping("/bookById")
    public String BookById(ModelMap modelMap, @RequestParam("id") int id) {

        Optional<Book> book = bookRepository.findById(id);
        modelMap.addAttribute("book",book );
        return "/change";

    }

    @PostMapping("/changeBook")
    public String changeBook( @ModelAttribute Book book) {

        bookRepository.save(book);
        return "redirect:/";

    }

}

