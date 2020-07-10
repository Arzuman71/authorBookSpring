package com.example.authorbookspring.repository;

import com.example.authorbookspring.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;


public interface AuthorRepository extends JpaRepository<Author, Integer> {


}
