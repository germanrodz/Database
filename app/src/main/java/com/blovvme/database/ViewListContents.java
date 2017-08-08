package com.blovvme.database;

import android.content.DialogInterface;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by Blovvme on 8/8/17.
 */

public class ViewListContents extends AppCompatActivity {

    DatabaseHelper myDb;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.viewcontents_layout);

        ListView listView = (ListView)findViewById(R.id.listview);
        myDb = new DatabaseHelper(this);

        final ArrayList<String> theList = new ArrayList<>();
        Cursor data = myDb.getAllData();
//        byte[] array = data.getBlob(3);
//        Bitmap bitmap  = BitmapFactory.decodeByteArray(array, 0, array.length);
        if (data.getCount() == 0){
            Toast.makeText(ViewListContents.this,"The Database was empty",Toast.LENGTH_SHORT).show();;
        }
        else {
            while (data.moveToNext()){
                //reference the ID in this case
                theList.add(data.getString(0)+ "\n" + data.getString(1)+ "\n" + data.getString(2)+ "\n" );

                ListAdapter listAdapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,theList);
                listView.setAdapter(listAdapter);
            }
        }
//        //TO make the listview clicable
//        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                AlertDialog.Builder alertadd = new AlertDialog.Builder(ViewListContents.this);
//                LayoutInflater factory = LayoutInflater.from(ViewListContents.this);
//                final View v = factory.inflate(R.layout.image_layout, null);
//               ImageView image= ((ImageView)(v.findViewById(R.id.dialog_imageview)));
//                alertadd.setView(v);
//                alertadd.setNeutralButton("Here!", new DialogInterface.OnClickListener() {
//                    public void onClick(DialogInterface dlg, int sumthin) {
//
//                    }
//                });
//
//                alertadd.show();
//
//                Toast.makeText(ViewListContents.this, theList.get(i), Toast.LENGTH_SHORT).show();
//            }
//        });

    }//onCreate last key
}//last key
