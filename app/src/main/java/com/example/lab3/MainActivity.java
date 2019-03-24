package com.example.lab3;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import static com.example.lab3.DataBaseConnection.TABLE_NAME;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    Button btnAdd, btnRead, btnClear;
    EditText etName, etSurname, etAge, etEmail, etPassword;

    DataBaseConnection dataBaseConnection;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnAdd = (Button) findViewById(R.id.btnAdd);
        btnAdd.setOnClickListener(this);

        btnRead = (Button) findViewById(R.id.btnRead);
        btnRead.setOnClickListener(this);

        btnClear = (Button) findViewById(R.id.btnClear);
        btnClear.setOnClickListener(this);

        etName = (EditText) findViewById(R.id.etName);
        etSurname = (EditText) findViewById(R.id.etSurname);
        etAge = (EditText) findViewById(R.id.etAge);
        etEmail = (EditText) findViewById(R.id.etEmail);
        etPassword = (EditText) findViewById(R.id.etPassword);

        dataBaseConnection = new DataBaseConnection(this);
    }

    @Override
    public void onClick(View v) {

        String name = etName.getText().toString();
        String surname = etSurname.getText().toString();
        String age = etAge.getText().toString();
        String email = etEmail.getText().toString();
        String password = etPassword.getText().toString();


        SQLiteDatabase database = dataBaseConnection.getWritableDatabase();

        ContentValues contentValues = new ContentValues();


        switch (v.getId()) {

            case R.id.btnAdd:
                contentValues.put(DataBaseConnection.KEY_NAME, name);
                contentValues.put(DataBaseConnection.KEY_SURNAME, surname);
                contentValues.put(DataBaseConnection.KEY_AGE, age);
                contentValues.put(DataBaseConnection.KEY_EMAIL, email);
                contentValues.put(DataBaseConnection.KEY_PASSWORD, password);

                database.insert(DataBaseConnection.TABLE_NAME, null, contentValues);
                break;

            case R.id.btnRead:
                Cursor cursor = database.query(DataBaseConnection.TABLE_NAME, null, null, null, null, null, null);

                if (cursor.moveToFirst()) {
                    int idIndex = cursor.getColumnIndex(DataBaseConnection.KEY_ID);
                    int nameIndex = cursor.getColumnIndex(DataBaseConnection.KEY_NAME);
                    int surnameIndex = cursor.getColumnIndex(DataBaseConnection.KEY_SURNAME);
                    int ageIndex = cursor.getColumnIndex(DataBaseConnection.KEY_AGE);
                    int emailIndex = cursor.getColumnIndex(DataBaseConnection.KEY_EMAIL);
                    int passwordIndex = cursor.getColumnIndex(DataBaseConnection.KEY_PASSWORD);

                    do {
                        Log.d("mLog", "ID = " + cursor.getInt(idIndex) +
                                ", name = " + cursor.getString(nameIndex) +
                                ", surname = " + cursor.getString(surnameIndex) +
                                ", age = " + cursor.getString(ageIndex) +
                                ", email = " + cursor.getString(emailIndex) +
                                ", password = " + cursor.getString(passwordIndex)
                        );
                    } while (cursor.moveToNext());
                } else
                    Log.d("mLog", "0 rows");

                cursor.close();
                break;

            case R.id.btnClear:
                database.delete(DataBaseConnection.TABLE_NAME, null, null);
                break;
        }
        dataBaseConnection.close();

    }


    void init() {
        ContentValues values = new ContentValues();
        values.put("id", 1);
        values.put("name", "Vova");
        values.put("surname", "Bakuro");
        values.put("age", 20);
        values.put("email", "fantom4407@gmail.com");
        SQLiteDatabase database = dataBaseConnection.getWritableDatabase();
        database.insert("students", null, values);

    }

    void getTable() {
        SQLiteDatabase database = dataBaseConnection.getWritableDatabase();
        Cursor cursor = database.query(TABLE_NAME, null, null, null, null, null, null);
        Log.i("INFO", cursor.toString());
    }


}
