package com.example.hiren.scanbuy2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Hiren on 10/24/2015.
 */
public class BookViewActivity extends AppCompatActivity {

    public static final String  TAG = "MyLog";
    private EditText barcode;
    private EditText title;
    private EditText author;
    private EditText pages;
    private TextView t1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.book_view);

        Intent intentExtra = getIntent();
        BookData b1;

        b1 = (BookData) intentExtra.getSerializableExtra("bookdata");

        barcode = (EditText) findViewById(R.id.editTextbarcode);
        title = (EditText)findViewById(R.id.editTextTitle);
        author = (EditText) findViewById(R.id.editTextAuthor);
        pages = (EditText) findViewById(R.id.editTextPages);
        t1 = (TextView) findViewById(R.id.textViewread);

        barcode.setText(b1.getB_code().toString());
        title.setText(b1.getB_title().toString());
        author.setText(b1.getB_author().toString());
        pages.setText(b1.getNum_pages().toString());

        String read = b1.getB_read().toString();

        if(read.equals("Yes"))
        {
            //t1.setVisibility(View.VISIBLE);
            t1.setText("Book Already read by You!!");
        }
        else
            t1.setText("Book Yet To Read!!");
    }
}
