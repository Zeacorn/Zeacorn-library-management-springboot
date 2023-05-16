package com.zea.springboot.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class Book {
    private int id;
    private String name;
    private String description;
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    private Date publishDate;
    private String author;
    private String publisher;
    private String category;
    private String bookNo;
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    private Date createTime;
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    private Date updateTime;
    private String cover;
    private List<String> categories;
    private Integer nums;
}
