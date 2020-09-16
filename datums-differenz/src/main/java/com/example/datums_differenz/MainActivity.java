package com.example.datums_differenz;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    private Calendar firstCalendar;
    private Calendar secondCalendar;
    private DatePicker firstDatePicker;
    private DatePicker secondDatePicker;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        firstCalendar = Calendar.getInstance();
        secondCalendar = Calendar.getInstance();

        firstDatePicker = findViewById(R.id.firstDatePicker);
        secondDatePicker = findViewById(R.id.secondDatePicker);

        textView = findViewById(R.id.textView);

        Button button = findViewById(R.id.button1);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                textView.setText(String.valueOf(brechnen(firstDatePicker, secondDatePicker)));
            }
        });
    }



    private int brechnen(DatePicker firstDatePicker, DatePicker secondDatePicker){
        Calendar brechnenFirstCalendar = updateCalendarFromDatePicker(firstCalendar, firstDatePicker);
        Calendar brechnenSecondCalendar = updateCalendarFromDatePicker(secondCalendar, secondDatePicker);

        if (!brechnenFirstCalendar.before(brechnenSecondCalendar)){
            Calendar change;
            change = brechnenFirstCalendar;
            brechnenFirstCalendar = brechnenSecondCalendar;
            brechnenSecondCalendar = change;
            System.out.println(change);
        }
        int i = 0;
        while (!brechnenFirstCalendar.equals(brechnenSecondCalendar)){
            brechnenFirstCalendar.add(Calendar.DAY_OF_YEAR, 1);
            i += 1;
        }
        return i;
    }

    public Calendar updateCalendarFromDatePicker(Calendar calendar, DatePicker datePicker){
        calendar.set(datePicker.getYear(), datePicker.getMonth(), datePicker.getDayOfMonth());
        return calendar;
    }
}
