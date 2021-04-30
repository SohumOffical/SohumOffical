package com.sngs.swayam.business.activity;


import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.NumberPicker;
import com.sngs.swayam.business.R;
import java.util.Calendar;


public class TestActivity extends AppCompatActivity {

    NumberPicker picker_year;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        picker_year = (NumberPicker) findViewById(R.id.picker_year);

        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        picker_year.setMinValue(1900);
        picker_year.setMaxValue(3500);
        picker_year.setValue(year);


    }
}
