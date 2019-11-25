package com.example.moodtunes;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private NumberPicker moodPicker;
    private String[] pickerVals;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        moodPicker = findViewById(R.id.moodPicker);
        final TextView chosenMood = findViewById(R.id.chosenMood);
        moodPicker.setTextSize(70);
        moodPicker.setTextColor(Color.parseColor("#1ED760"));
        moodPicker.setMaxValue(3);
        moodPicker.setMinValue(0);
        pickerVals = new String[] {"Angry", "Sad", "Happy", "Chill"};
        moodPicker.setDisplayedValues(pickerVals);
        moodPicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker numberPicker, int i, int i1) {
                int moodPickerVal = moodPicker.getValue();
                Log.d("picker value", pickerVals[moodPickerVal]);
                chosenMood.setText(pickerVals[moodPickerVal]);
            }
        });
        setSupportActionBar(toolbar);
        FloatingActionButton nextButton = findViewById(R.id.nextButton);
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buttonClicked();
            }
        });
    }
    public void buttonClicked() {
        Intent intent = new Intent(this, PlaylistScreen.class);
        startActivity(intent);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
