package com.example.authorbookspring.cotroller;


import com.example.authorbookspring.model.Author;

import com.example.authorbookspring.repository.AuthorRepository;
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
public class AuthorController {
    @Autowired
    private AuthorRepository authorRepository;


    @GetMapping("/")
    public String homePage() {
        return "index";
    }


    @GetMapping("/authorHome")
    public String authorHome(ModelMap modelMap) {

        List<Author> all = authorRepository.findAll();
        modelMap.addAttribute("authors", all);
        return "authorHome";
    }


    @GetMapping("/deleteAuthor")
    public String deleteAuthor(@RequestParam("id") int id) {

        authorRepository.deleteById(id);
        return "redirect:/authorHome";
    }

    @PostMapping("/addAuthor")
    public String addUser(@ModelAttribute("author") Author author) {

        authorRepository.save(author);
        return "redirect:/";
    }

    @GetMapping("/authorById")
    public String authorById(ModelMap modelMap, @RequestParam("id") int id) {

        Optional<Author> author = authorRepository.findById(id);
        modelMap.addAttribute("author", author);
        return "change";

    }

    @PostMapping("/changeAuthor")
    public String changeAuthor(@ModelAttribute Author author) {
        authorRepository.save(author);

        return "redirect:/";

    }

    //  @GetMapping("/allAuthorId")
    //  public String allAuthorId(ModelMap modelMap) {
    //      List authors = authorRepository.findAll();
    //      modelMap.addAttribute("authors",authors);
    //      return "redirect:/";

    //  }

}

