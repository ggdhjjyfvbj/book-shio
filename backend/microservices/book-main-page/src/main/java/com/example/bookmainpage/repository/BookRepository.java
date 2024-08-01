package com.example.bookmainpage.repository;

import com.example.bookmainpage.entity.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<BookEntity, Long> {
}
