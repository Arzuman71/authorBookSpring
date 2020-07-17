package com.example.authorbookspring.cotroller;


import com.example.authorbookspring.model.Book;
import com.example.authorbookspring.service.BookService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    @GetMapping("/bookHome")
    public String bookHomePage(ModelMap modelMap) {
        List<Book> allBooks = bookService.findAll();
        modelMap.addAttribute("books", allBooks);
        return "bookHome";
    }


    @GetMapping("/deleteBook")
    public String deleteBook(@RequestParam("id") int id) {
        bookService.deleteById(id);
        return "redirect:/bookHome";
    }

    @PostMapping("/saveBook")
    public String add(@ModelAttribute Book book) {
        String msg = book.getId() > 0 ? "Book was updated" : "Book was added";
        bookService.save(book);
        return "redirect:/?msg=" + msg;
    }


    @GetMapping("/bookById")
    public String bookById(ModelMap modelMap, @RequestParam("id") int id) {

        Book book = bookService.getOne(id);
        modelMap.addAttribute("book", book);
        return "/change";

    }


}

