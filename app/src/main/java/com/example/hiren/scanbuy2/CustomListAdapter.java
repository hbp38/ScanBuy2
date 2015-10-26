package com.example.hiren.scanbuy2;

/**
 * Created by Hiren on 10/24/2015.
 */
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class CustomListAdapter extends BaseAdapter {

    private final Context context;
    private  ArrayList<BookData> books=new ArrayList<>() ;
//    private final ArrayList<Integer> imgid;

    public CustomListAdapter(Context context, ArrayList<BookData> books) {
//        super(context, R.layout.mylist, itemname);
        // TODO Auto-generated constructor stub

        this.context=context;
        this.books=books;
//        this.imgid=imgid;
    }

    @Override
    public int getCount() {
        return books.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    public View getView(int position,View view,ViewGroup parent) {
        LayoutInflater inflater=(LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView=inflater.inflate(R.layout.mylist, null, true);

        TextView txtTitle = (TextView) rowView.findViewById(R.id.listbookname);
        ImageView imageView = (ImageView) rowView.findViewById(R.id.icon);
        TextView extratxt = (TextView) rowView.findViewById(R.id.listbookdata);

        BookData bookData=books.get(position);

        txtTitle.setText(bookData.getB_title());
        imageView.setImageResource(R.drawable.listicon);
        extratxt.setText("Barcode "+bookData.getB_code());
        return rowView;

    };
}