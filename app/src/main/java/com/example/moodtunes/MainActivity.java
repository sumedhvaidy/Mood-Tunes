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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    private NumberPicker moodPicker;
    private String[] pickerVals;
    private String pickedMood;
    private Intent mood;


    private Map<String, List<String>> playlists = new HashMap<>();

    private List<String> happyPlaylists;
    private List<String> sadPlaylists;
    private List<String> angryPlaylists;
    private List<String> chillPlaylists;

    private Map<String, List<String>> allPlaylists = new HashMap<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mood = new Intent(this, PlaylistScreen.class);
        pickedMood = "Chill";


        happyPlaylists = new ArrayList<>();
        happyPlaylists.add("https://open.spotify.com/playlist/19Y5Q4ulqoI1UxuJe2TnIM?si=z8XQHwjARTSstBWeTKMamw");
        happyPlaylists.add("https://open.spotify.com/playlist/44U4Qq3fDVpzIM4EVodJkP?si=25fStNluQaecBtH6MJZfWw");
        happyPlaylists.add("https://open.spotify.com/playlist/0yjXYfvxF99qWaRyCdhJJW?si=67UU8qVFQ3uIdNQkeN-_Wg");

        sadPlaylists = new ArrayList<>();
        sadPlaylists.add("https://open.spotify.com/playlist/5ylYSUfuhyL80OUi0WjPRz?si=unWnBUOoRoyDGN323QkzOg");
        sadPlaylists.add("https://open.spotify.com/playlist/0k3rrcAk6hg5PNveSdFGe9?si=FzQHFVjUSsOKVmUqH_rAgA");
        sadPlaylists.add("https://open.spotify.com/playlist/5JRNjr60ABjFTTzntqfirC?si=6BSct92JTaeLr9e_sOSAoQ");

        angryPlaylists = new ArrayList<>();
        angryPlaylists.add("https://open.spotify.com/playlist/1UEE5AwkRv4rSmKfojDeZZ?si=y4qrUo4ST7qHQLpj_-ytUA");
        angryPlaylists.add("https://open.spotify.com/playlist/5Di4fRWCZvpopb8Onc3RAE?si=A7-tvPkLQWq32fnRUFr4Ig");
        angryPlaylists.add("https://open.spotify.com/playlist/3qCbIkEbmaSWCo3VVJ5W1A?si=fVEFRAScTZK23A-remFodw");

        chillPlaylists = new ArrayList<>();
        chillPlaylists.add("https://open.spotify.com/playlist/7stpP6Ydg7I817k1ASlUtL?si=ZaoSpRuaS7CXJmLO2hwyiQ");
        chillPlaylists.add("https://open.spotify.com/playlist/5DwF25KM2rAdg2Dj6hnHEj?si=CRmgzdzPRTqLmQaRFD2ucg");
        chillPlaylists.add("https://open.spotify.com/playlist/6TMmthAiHWqVjBpEtRhSXu?si=FIowc7_NRpqaAKkNxXoFKg");
//        angryPlaylists.add("")
        playlists.put("Happy", happyPlaylists);
        playlists.put("Sad", sadPlaylists);
        playlists.put("Angry", angryPlaylists);
        playlists.put("Chill", chillPlaylists);

        FloatingActionButton nextButton = findViewById(R.id.nextButton);
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buttonClicked();
            }
        });
        Toolbar toolbar = findViewById(R.id.toolbar);
        final TextView chosenMood = findViewById(R.id.chosenMood);
        moodPicker = findViewById(R.id.moodPicker);
        chosenMood.setText(pickedMood);
        moodPicker.setTextSize(70);
        moodPicker.setTextColor(Color.parseColor("#1ED760"));
        moodPicker.setMaxValue(3);
        moodPicker.setMinValue(0);
        pickerVals = new String[] {"Chill", "Sad", "Happy", "Angry"};
        moodPicker.setDisplayedValues(pickerVals);
        moodPicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker numberPicker, int i, int i1) {
                int moodPickerVal = moodPicker.getValue();
                pickedMood = pickerVals[moodPickerVal];
                Log.d("picker value", pickedMood);
                chosenMood.setText(pickedMood);
            }
        });
        setSupportActionBar(toolbar);
        //Hey
    }


    public String[] convertStringListToArray(List<String> originalList)
    {
        String convertedArray[] = new String[originalList.size()];
        for (int j = 0; j < originalList.size(); j++) {
            convertedArray[j] = originalList.get(j);
        }
        return convertedArray;
    }


    public void buttonClicked() {
        System.out.println("Before: " + pickedMood);
        mood.putExtra("mood", pickedMood);
        mood.putExtra(pickedMood, convertStringListToArray(playlists.get(pickedMood)));
        startActivity(mood);
        finish();
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
    // Hope this works....
}
