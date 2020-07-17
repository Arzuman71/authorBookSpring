package com.example.authorbookspring.service;

import com.example.authorbookspring.model.Author;
import com.example.authorbookspring.repository.AuthorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthorService {


    private final AuthorRepository authorRepository;


    public List<Author> findAll() {
        return authorRepository.findAll();
    }

    public void deleteById(int id) {
        authorRepository.deleteById(id);

    }

    public void save(Author author) {
        authorRepository.save(author);
    }

    public Author getOne(int id) {
        return authorRepository.getOne(id);
    }

}
