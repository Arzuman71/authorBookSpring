package com.example.authorbookspring.cotroller;


import com.example.authorbookspring.model.Author;

import com.example.authorbookspring.model.Gender;
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
    public String addUser(@RequestParam("name") String name, @RequestParam("surname") String surname, @RequestParam("email") String email
            , @RequestParam("gender") String gender, @RequestParam("bio") String bio) {
        Author author = Author.builder()
                .name(name)
                .surname(surname)
                .email(email)
                .gender(Gender.valueOf(gender))
                .bio(bio)
                .build();

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
    public String changeAuthor(ModelMap modelMap, @ModelAttribute Author author) {
        authorRepository.save(author);

        return "redirect:/";

    }
}
