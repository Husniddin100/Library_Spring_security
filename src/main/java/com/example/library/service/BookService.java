package com.example.library.service;

import com.example.library.dto.BookDTO;
import com.example.library.entity.BookEntity;
import com.example.library.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class BookService {
    private final BookRepository bookRepository;

    public BookDTO createBook(BookDTO dto) {

        BookEntity entity = new BookEntity();
        entity.setAuthor(dto.getAuthor());
        entity.setTitle(dto.getTitle());
        entity.setPublishYear(dto.getPublishYear());

        bookRepository.save(entity);
        dto.setId(entity.getId());
        return dto;
    }

    public List<BookDTO> bookList() {
        var books = bookRepository.findAll();

        List<BookDTO> employeeDTOS = new ArrayList<>();

        for (BookEntity book : books) {
            employeeDTOS.add(toDto(book));
        }

        return employeeDTOS;
     }

    public BookDTO toDto(BookEntity employee) {
        BookDTO dto = new BookDTO();
        dto.setId(employee.getId());
        dto.setAuthor(employee.getAuthor());
        dto.setTitle(employee.getTitle());
        dto.setPublishYear(employee.getPublishYear());

        return dto;
    }
}