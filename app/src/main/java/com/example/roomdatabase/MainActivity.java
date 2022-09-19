package com.example.roomdatabase;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    EditText name, email, pincode, city, phoneNumber;
    Button button;
    int mPersonId;
    Intent intent;
    private AppDatabase mDb;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        mDb = AppDatabase.getInstance(getApplicationContext());

    }



    private void initViews() {
        name = findViewById(R.id.edit_name);
        email = findViewById(R.id.edit_email);
        pincode = findViewById(R.id.edit_pincode);
        city = findViewById(R.id.edit_city);
        phoneNumber = findViewById(R.id.edit_number);
        button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onSaveButtonClicked();
            }
        });
    }



    public void onSaveButtonClicked() {
        final Person person = new Person(
                name.getText().toString(),
                email.getText().toString(),
                phoneNumber.getText().toString(),
                pincode.getText().toString(),
                city.getText().toString());

        AppExecutors.getInstance().diskIO().execute(new Runnable() {
            @Override
            public void run() {
                mDb.personDao().insertPerson(person);
            }
        });

    }


}