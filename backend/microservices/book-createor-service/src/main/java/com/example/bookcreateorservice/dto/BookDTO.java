package com.example.bookcreateorservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class BookDTO {

    private String bookname;

    private String bookDescription;

    private long views;

    private long date;

    private long readers;

    private String author;
}
