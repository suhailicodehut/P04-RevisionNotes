package com.myapplicationdev.android.p04_revisionnotes;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText etNotes;
    RadioGroup rgStars;
    Button btnInsert,btnShow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etNotes = (EditText)findViewById(R.id.editTextNote);
        rgStars = (RadioGroup)findViewById(R.id.radioGroupStars);
        btnInsert = (Button)findViewById(R.id.buttonInsertNote);
        btnShow = (Button)findViewById(R.id.buttonShowList);



        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper db = new DBHelper(MainActivity.this);
                int selectedButtonId = rgStars.getCheckedRadioButtonId();
                // Get the radio button object from the Id we had gotten above
                RadioButton rb = (RadioButton) findViewById(selectedButtonId);

                // Insert a task
                String notes = etNotes.getText().toString();
                int stars = Integer.parseInt(rb.getText().toString());
                db.insertNote(notes,stars);
                db.close();
                Toast.makeText(MainActivity.this, "Inserted", Toast.LENGTH_SHORT).show();
            }
        });

        btnShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this,SecondActivity.class);
                startActivity(i);
            }
        });
    }
}
