package com.example.ccdbookingapp;
// Christos Giapitzakis 3200034
// Dimitris Louridas 3200281
//Christos Katsaros 3210070
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.SeekBar;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    private Button searchBtn;
    private EditText areaTxt;
    private CalendarView fromCld;
    private CalendarView toCld;
    private SeekBar seekBar;
    private RatingBar ratingBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        searchBtn = (Button) findViewById(R.id.button);
        areaTxt = (EditText) findViewById(R.id.editTextText3);
        fromCld = (CalendarView) findViewById(R.id.calendarView2);
        toCld = (CalendarView) findViewById(R.id.calendarView3);
        seekBar = (SeekBar) findViewById(R.id.seekBar2);
        ratingBar = (RatingBar) findViewById(R.id.ratingBar);

        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String area = areaTxt.getText().toString();
                long fromDate = fromCld.getDate();
                long toDate = toCld.getDate();
                int persons = seekBar.getProgress();
                float rating = ratingBar.getRating();

                Intent intent = new Intent(MainActivity.this, ListActivity.class);
                intent.putExtra("area", area);
                intent.putExtra("from", fromDate);
                intent.putExtra("to", toDate);
                intent.putExtra("persons", persons);
                intent.putExtra("rating", rating);
                MainActivity.this.startActivity(intent);

            }
        });
    }
}