package com.example.hiren.scanbuy2;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ArrayList<BookData> arrBook=new ArrayList<>();
    public static final String  TAG = "MyLog";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       // DatabaseHandler db = new DatabaseHandler(this);
    }

    public void addBookonClick(View view){

        IntentIntegrator integrator = new IntentIntegrator(MainActivity.this);
        integrator.initiateScan();
    }

    public void onActivityResult(int requestCode,int resultCode,Intent intent){

        IntentResult scanResult = IntentIntegrator.parseActivityResult(requestCode,resultCode,intent);
        if(scanResult != null)
        {
            String barcode;
            String type;
            DatabaseHandler db = new DatabaseHandler(this);

            barcode = scanResult.getContents();
            type = scanResult.getFormatName();

             if(db.getExistingBooks(barcode))
             {
                 Toast.makeText(MainActivity.this, "Book Already Exist!!", Toast.LENGTH_LONG).show();
                 Intent i2;
                 BookData b2;
                 b2 = db.getSingleBook(barcode);
                 i2 = new Intent(this,BookViewActivity.class);
                 i2.putExtra("bookdata", b2);

                 startActivity(i2);
             }
            else
             {
                 Intent intent1;
                 intent1 = new Intent(this, RegisterActivity.class);
                 intent1.putExtra("barC",barcode);
                 startActivity(intent1);
             }



        }

    }


    public void listOnClick(View view){

        Intent intent2 = new Intent(this, CustomListActivity.class);
        startActivity(intent2);
    }
}
