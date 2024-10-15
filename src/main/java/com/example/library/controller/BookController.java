package com.example.library.controller;

import com.example.library.dto.BookDTO;
import com.example.library.service.BookService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@Slf4j
@RequestMapping("/book")
public class BookController {

    private final BookService bookService;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/create")
    public ResponseEntity<BookDTO> createBook(@RequestBody BookDTO bookDTO) {
        log.info("Create book: {}", bookDTO);
        return ResponseEntity.ok(bookService.createBook(bookDTO));
    }

    @GetMapping("/get-all")
    public ResponseEntity<List<BookDTO>> getAllBooks() {
        log.info("Get all books");
        return ResponseEntity.ok(bookService.bookList());
    }


}
