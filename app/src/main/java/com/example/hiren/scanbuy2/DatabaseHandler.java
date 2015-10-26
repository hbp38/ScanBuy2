package com.example.hiren.scanbuy2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.nfc.Tag;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Hiren on 10/22/2015.
 */
public class DatabaseHandler extends SQLiteOpenHelper {

    public static final String  TAG = "MyLog";
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "BookInventory";

    // Contacts table name
    private static final String TABLE_name = "Books";

    // Contacts Table Columns names
    private static final String KEY_bCode = "Barcode";
    private static final String KEY_title = "Title";
    private static final String KEY_author = "Author";
    private static final String KEY_num_pages = "Pages";
    private static final String KEY_read = "Read";
    // Table Names


    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {


        String CREATE_BOOKS_TABLE = "CREATE TABLE " + TABLE_name + "("
             + KEY_bCode + " INTEGER PRIMARY KEY," + KEY_title + " TEXT,"
                + KEY_author + " TEXT,"+ KEY_read + " TEXT," + KEY_num_pages + " INTEGER"
               + ")";
        db.execSQL(CREATE_BOOKS_TABLE);


        Log.i(TAG, "Db created");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_name);

       onCreate(db);

    }

    void addBook(BookData oBook) {
        Log.i(TAG,"ADDCalled");

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_bCode,oBook.getB_code());
        values.put(KEY_title, oBook.getB_title());
        values.put(KEY_author, oBook.getB_author());
        values.put(KEY_read, oBook.getB_read());
        values.put(KEY_num_pages, oBook.getNum_pages());

        db.insert(TABLE_name, null, values);
        db.close();
    }

    public List<BookData> getAllBooks() {
        List<BookData> BookList = new ArrayList<BookData>();

        String selectQuery = "SELECT  * FROM " + TABLE_name;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);



        if (cursor!=null && cursor.moveToLast()) {
            do {
                BookData b = new BookData();

                b.setB_code(cursor.getString(0));
                b.setB_title(cursor.getString(1));
                b.setB_author(cursor.getString(2));
                b.setB_read(cursor.getString(3));
                b.setNum_pages(cursor.getString(4));



                BookList.add(b);

            }while (cursor.moveToPrevious());
        }
            cursor.close();
        db.close();

        return BookList;
    }

    // method to check existing barcode data

    public boolean getExistingBooks(String barCode) {

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        String sql ="SELECT " + KEY_bCode +" FROM "+TABLE_name+" WHERE "+ KEY_bCode+"="+barCode;
        cursor= db.rawQuery(sql,null);
      //  Log("Cursor Count : " + cursor.getCount());

        if(cursor.getCount()>0){

            cursor.close();
            db.close();
            return true;
        }else{
            cursor.close();
            db.close();
            return false;


        }



    }

    public BookData getSingleBook(String barCode) {

        SQLiteDatabase db = this.getReadableDatabase();
        BookData b = new BookData();
        Cursor cursor = null;
        String sql ="SELECT *" +  " FROM "+TABLE_name+" WHERE "+ KEY_bCode+"="+barCode;
        cursor= db.rawQuery(sql,null);
        //  Log("Cursor Count : " + cursor.getCount());

        if(cursor.getCount()>0){
                cursor.moveToFirst();

            b.setB_code(cursor.getString(0));
            b.setB_title(cursor.getString(1));
            b.setB_author(cursor.getString(2));
            b.setB_read(cursor.getString(3));
            b.setNum_pages(cursor.getString(4));


            cursor.close();
            db.close();

        }

        return b;



    }
}
