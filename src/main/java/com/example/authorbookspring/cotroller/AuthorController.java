package com.example.authorbookspring.cotroller;

import com.example.authorbookspring.model.Author;
import com.example.authorbookspring.repository.AuthorRepository;
import com.example.authorbookspring.service.AuthorService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.Principal;
import java.util.List;
import java.util.UUID;


@Controller
@RequiredArgsConstructor
public class AuthorController {

    private final AuthorService authorService;

    @Value("${file.upload.dir}")
    private String uploadDir;


    @GetMapping("/")
    public String homePage(@AuthenticationPrincipal Principal principal, Model modelMap, @RequestParam(name = "msg", required = false) String msg) {
       String authorName = null;
        if (principal != null){
            authorName = principal.getName();
        }
        List<Author> authors = authorService.findAll();
        modelMap.addAttribute("msg", msg);
        modelMap.addAttribute("authors", authors);
        modelMap.addAttribute("authorName", authorName);
        return "index";
    }


    @GetMapping("/authorHome")
    public String authorHome(ModelMap modelMap) {

        List<Author> all = authorService.findAll();
        modelMap.addAttribute("authors", all);
        return "authorHome";
    }


    @GetMapping("/deleteAuthor")
    public String deleteAuthor(@RequestParam("id") int id) {

        authorService.deleteById(id);
        return "redirect:/authorHome";
    }

    @PostMapping("/saveAuthor")
    public String addUser(@ModelAttribute("author") Author author, @RequestParam("image") MultipartFile file) throws IOException {
        String msg = "Author was added";

        if (file != null) {
            String name = UUID.randomUUID().toString().replace("-", " ") + "_" + file.getOriginalFilename();
            File image = new File(uploadDir, name);
            file.transferTo(image);
            author.setProfilePic(name);
            authorService.save(author);

        } else {
            msg = "image discordant";
        }
        return "redirect:/?msg=" + msg;
    }

    @GetMapping("/authorById")
    public String authorById(ModelMap modelMap, @RequestParam("id") int id) {
        Author author = authorService.getOne(id);
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

