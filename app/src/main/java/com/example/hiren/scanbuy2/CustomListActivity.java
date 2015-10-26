package com.example.hiren.scanbuy2;

/**
 * Created by Hiren on 10/24/2015.
 */
import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class CustomListActivity extends AppCompatActivity{

    ListView list;
    Intent intent3;
    public static final String  TAG = "MyLog";
    DatabaseHandler db;


    ArrayList<BookData> arrBook=new ArrayList<>();

   // ArrayList<String> itemname=new ArrayList<>();
   // ArrayList<Integer> imgid=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listactivity);

         intent3 = new Intent(this,BookViewActivity.class);
            db = new DatabaseHandler(this);
        ////
        List<BookData> books = db.getAllBooks();

        int i=0;
        for(BookData b1 : books){

            arrBook.add(b1);
            i++;
        }
        ////
        //Log.i(TAG,"arrays created");
        CustomListAdapter adapter=new CustomListAdapter(CustomListActivity.this, arrBook);
        list=(ListView)findViewById(R.id.list);
        list.setAdapter(adapter);

        list.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                // TODO Auto-generated method stub
                String Slecteditem= arrBook.get(position).getB_title();
                Toast.makeText(getApplicationContext(), Slecteditem, Toast.LENGTH_SHORT).show();
                BookData bookData=arrBook.get(position);
                intent3.putExtra("bookdata", bookData);

                startActivity(intent3);

            }
        });
    }
}
