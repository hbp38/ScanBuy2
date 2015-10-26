package com.example.hiren.scanbuy2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

/**
 * Created by Hiren on 10/22/2015.
 */
public class RegisterActivity extends AppCompatActivity {

    public static final String  TAG = "MyLog";
    String barcode;
    private EditText title;
    private EditText author;
    private EditText pages;
    private Button submit;
    private Button clear;
    private RadioGroup rg;
    private RadioButton selectedradio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_book);

       Intent intentExtra = getIntent();
        barcode = intentExtra.getStringExtra("barC");
        //Log.i(TAG,"passed"+intentExtra.getStringExtra("barC"));
       // Log.i(TAG,"passed"+intentExtra.toString());

        EditText t1 = (EditText)findViewById(R.id.editText1);
        t1.setText(barcode);


    }

    public void submitOnClick(View view){
        DatabaseHandler db = new DatabaseHandler(this);
        ValidateForm vd = new ValidateForm();

        title = (EditText)findViewById(R.id.editTextTitle);
        author = (EditText) findViewById(R.id.editTextAuthor);
        pages = (EditText) findViewById(R.id.editTextPages);
        rg = (RadioGroup) findViewById(R.id.radioBook);
        int selectedId = rg.getCheckedRadioButtonId();
        selectedradio = (RadioButton) findViewById(selectedId);
        submit = (Button) findViewById(R.id.submit);
        clear = (Button) findViewById(R.id.clear);

        if(vd.isEmpty(title.getText().toString()))
        {
            title.setError("Title can't be empty!!");
        }
        else if(vd.isEmpty(author.getText().toString()))
        {
            author.setError("Author can't be Empty");
        }
        else if(vd.isEmpty(pages.getText().toString()))
        {
            pages.setError("Number of Pages can't be Empty");
        }
        else if(!vd.isNumber(pages.getText().toString()))
        {
            pages.setError("Enter Numbers only");
        }
        else
        {
            db.addBook(new BookData(barcode, title.getText().toString(), author.getText().toString(), selectedradio.getText().toString(), pages.getText().toString()));
            Toast.makeText(RegisterActivity.this, "Book Added Successfully!!", Toast.LENGTH_SHORT).show();
            List<BookData> books = db.getAllBooks();
            for(BookData b1 : books){
                Log.i(TAG,"barcode ="+b1.getB_code() + "title =" + b1.getB_title() + "author =" + b1.getB_author() + "read??" + b1.getB_read() + "pages=" + b1.getNum_pages());
            }
            db.close();
            Intent i2;
            i2 = new Intent(this,MainActivity.class);
            startActivity(i2);
        }





    }

    public void clearOnClick(View view)
    {
        title = (EditText)findViewById(R.id.editTextTitle);
        author = (EditText) findViewById(R.id.editTextAuthor);
        pages = (EditText) findViewById(R.id.editTextPages);

        selectedradio = (RadioButton) findViewById(R.id.radioButtonNo);

        clear = (Button) findViewById(R.id.clear);

        title.setText("");
        title.requestFocus();
        author.setText("");
        pages.setText("");
        selectedradio.setChecked(true);


    }

}
