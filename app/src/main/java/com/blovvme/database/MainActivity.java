package com.blovvme.database;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import static android.R.attr.value;
import static android.os.Build.ID;


public class MainActivity extends AppCompatActivity {

    //Declare the variables
    TextView etName,etSurname,etAddress,etId;
    Button btnSave,btnCamera,btnDatabase,btnSecondActivity,btnUpdate,btnDelete;
    EditText et1,et2,et3,et4;

    DatabaseHelper myDb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myDb = new DatabaseHelper(this);

        etName = (TextView)findViewById(R.id.etName);
        etSurname = (TextView)findViewById(R.id.etSurname);
        etAddress = (TextView)findViewById(R.id.etSurname);
        etId = (TextView)findViewById(R.id.etId);
       // etId.setEnabled(false);
        et1 = (EditText)findViewById(R.id.et1);
        et2 = (EditText)findViewById(R.id.et2);
        et3 = (EditText)findViewById(R.id.et3);
        et4 = (EditText)findViewById(R.id.et4);
       // et4.setEnabled(false);
        btnSecondActivity = (Button) findViewById(R.id.btnSecondActivity);
        btnUpdate = (Button) findViewById(R.id.btnUpdate);
        btnDelete = (Button) findViewById(R.id.btnDelete);
        btnSave = (Button)findViewById(R.id.btnSave);
        //btnCamera = (Button)findViewById(R.id.btnCamera);
        btnDatabase = (Button)findViewById(R.id.btnDatabase);
        //Button that inserts data in the Database
        addData();
        //Button that inserts data in the Database
        viewAll();
        //Button that update data by ID the Database
        updateData();
        //Button that delete data by ID in the Database
        deleteData();

        //Show the ListView in second activity
        btnSecondActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,ViewListContents.class);
                startActivity(intent);
            }
        });

    }//onCreate last key



    //To add Data in the Database using the Submit Button
    public void addData(){
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //We don't need the boolean. THat's just to verify if it's inserted in the Database
                boolean isInserted = myDb.insertData(et1.getText().toString(),
                        et2.getText().toString(),
                        et3.getText().toString());
                if (isInserted == true){
                    Toast.makeText(MainActivity.this,"Data Inserted in Database",Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(MainActivity.this,"Data Not Inserted in Database",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }//

    //To retrieve the data in the Database
    public void viewAll(){
    btnDatabase.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {

          Cursor cursor = myDb.getAllData();
            if (cursor.getCount() == 0){
                //showMessage Function
                showMessage("Error","No data found!");
                return;
            }

            StringBuffer buffer = new StringBuffer();
            while (cursor.moveToNext()){
                //buffer appends the colums ID,NAME,SURNAME,ADDRESS

                buffer.append("Id: " + cursor.getString(0) + "\n") ;
                buffer.append("Name: " + cursor.getString(1) + "\n") ;
                buffer.append("Surname: " + cursor.getString(2) + "\n") ;
                buffer.append("Address: " + cursor.getString(3) + "\n\n") ;
            }
            //Show all Data
            showMessage("Data",buffer.toString());
            et1.setText("");
            et2.setText("");
            et3.setText("");
            et4.setText("");
        }
    });
    }

    //Shows a DialogBox with all the data in the Database
    public void showMessage(String title, String message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }

    //Method to Update data in the Database
    public void updateData(){
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                boolean isUpdated = myDb.updateData(et4.getText().toString(),et1.getText().toString(),et2.getText().toString(),et3.getText().toString());
                if (isUpdated == true){
                    if (isUpdated == true){
                        et1.setText("");
                        et2.setText("");
                        et3.setText("");
                        et4.setText("");
                        Toast.makeText(MainActivity.this,"Data Updated in Database",Toast.LENGTH_SHORT).show();
                    }
                    else{
                        Toast.makeText(MainActivity.this,"Data Not Updated in Database",Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });
    }

    //Method to Delete data from the Database
    public void deleteData(){
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Integer deleteRows = myDb.dataDelete(et4.getText().toString());
                if (deleteRows > 0){
                    et1.setText("");
                    et2.setText("");
                    et3.setText("");
                    et4.setText("");
                    Toast.makeText(MainActivity.this,"Data Deleted in Database",Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(MainActivity.this,"Data Not Deleted in Database",Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

//    Button that goes to the Second Activity
//    public void goToAnActivity(View view) {
//        Intent Intent = new Intent(this, ListView.class);
//        startActivity(Intent);
//    }



}//last key
