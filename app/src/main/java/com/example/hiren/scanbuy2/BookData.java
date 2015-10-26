package com.example.hiren.scanbuy2;

import java.io.Serializable;

/**
 * Created by Hiren on 10/22/2015.
 */
public class BookData implements Serializable {

    String b_code;
    String b_title;
    String b_author;
    String b_read;
    String num_pages;

    public BookData() {
    }

    public BookData(String b_code, String b_title, String b_author, String b_read, String num_pages) {
        this.b_code = b_code;
        this.b_title = b_title;
        this.b_author = b_author;
        this.b_read = b_read;
        this.num_pages = num_pages;
    }

    public String getB_code() {
        return b_code;
    }

    public void setB_code(String b_code) {
        this.b_code = b_code;
    }

    public String getB_title() {
        return b_title;
    }

    public void setB_title(String b_title) {
        this.b_title = b_title;
    }

    public String getB_author() {
        return b_author;
    }

    public void setB_author(String b_author) {
        this.b_author = b_author;
    }

    public String getB_read() {
        return b_read;
    }

    public void setB_read(String b_read) {
        this.b_read = b_read;
    }

    public String getNum_pages() {
        return num_pages;
    }

    public void setNum_pages(String num_pages) {
        this.num_pages = num_pages;
    }
}
