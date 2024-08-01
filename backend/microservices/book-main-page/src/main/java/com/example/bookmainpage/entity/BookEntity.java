package com.example.bookmainpage.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Data
public class BookEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public long id;

    private String bookname;

    private String bookDescription;

    private long views;

    private int date;

    private long readers;

    private String author;
}
