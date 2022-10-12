package com.example.appsqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    //instanciar la clase clsDB
    clsDb ohDb = new clsDb(this,"dblibrary",null,1);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //instanciar y referenciar los ids del archivo xml(activity_main.xml)
        EditText fullname = findViewById(R.id.etfullname);
        EditText email = findViewById(R.id.etemail);
        EditText password = findViewById(R.id.etpassword);
        ImageButton btnsave = findViewById(R.id.btnsave);
        ImageButton btnsearch = findViewById(R.id.btnsearch);
        ImageButton btnupdate = findViewById(R.id.btnupdate);
        ImageButton btndelete = findViewById(R.id.btndelete);

        btnsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveUser(fullname.getText().toString(),email.getText().toString(),password.getText().toString());
            }
        });


    }

    private void saveUser(String sfullname, String semail, String spassword) {
        //instanciar la clase clsDB
        //clsDb ohDb = new clsDb(this,"dblibrary",null,1);

        //instanciar la base de datos en modo de escritura(INSERT, UPDATE, DELETE);
        SQLiteDatabase db = ohDb.getWritableDatabase();

        //try sirve para elk manejo de excepxiones
        try{
            //crear un objeto de contentvalues para que contenga los mismos campos de tabla tabla user
            ContentValues cvUser = new ContentValues();
            cvUser.put("fullname", sfullname);
            cvUser.put("email",semail);
            cvUser.put("password",spassword);

            //Guardar en la tabla user lo que contiene cvUser
            db.insert("user",null,cvUser);
            db.close();
            Toast.makeText(getApplicationContext(),"usuario guardado exitosamente...",Toast.LENGTH_SHORT).show();
        }
        catch (Exception e){
            Toast.makeText(getApplicationContext(),"Error.."+ e.getMessage(),Toast.LENGTH_SHORT).show();
        }


    }}
