package com.example.authorbookspring.cotroller;

import com.example.authorbookspring.model.Author;

import com.example.authorbookspring.repository.AuthorRepository;
import lombok.RequiredArgsConstructor;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@RequiredArgsConstructor
@Controller
public class AuthorController {

    private final AuthorRepository authorRepository;

    @Value("${file.upload.dir}")
    private String uploadDir;

    @GetMapping("/")
    public String homePage(Model modelMap, @RequestParam(name = "msg", required = false) String msg) {
        List<Author> authors = authorRepository.findAll();
        modelMap.addAttribute("msg", msg);
        modelMap.addAttribute("authors", authors);
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

    @PostMapping("/saveAuthor")
    public String addUser(@ModelAttribute("author") Author author, @RequestParam("image") MultipartFile file) throws IOException {
        String name = System.currentTimeMillis() + "_" + file.getOriginalFilename();
        File image = new File(uploadDir, name);
        file.transferTo(image);
        author.setProfilePic(name);
        authorRepository.save(author);
        return "redirect:/?msg=Author was added";
    }

    @GetMapping("/authorById")
    public String authorById(ModelMap modelMap, @RequestParam("id") int id) {
        Author author = authorRepository.getOne(id);
        modelMap.addAttribute("author", author);
        return "change";

    }

    @GetMapping(value = "/image",
            produces = MediaType.IMAGE_JPEG_VALUE
    )
    public @ResponseBody
    byte[] getImage(@RequestParam("name") String imageName) throws IOException {
        InputStream in = new FileInputStream(uploadDir + File.separator + imageName);
        return IOUtils.toByteArray(in);
    }

}

