package com.example.moodtunes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

public class PlaylistScreen extends AppCompatActivity {

    public List<Song> songs = new ArrayList<Song>();
    public Song song = new Song();
    public String pickedMood;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_playlist_screen);
        Intent moodIntent = getIntent();
        pickedMood = moodIntent.getStringExtra("mood");
        Log.i("blah", pickedMood);
        TextView mood = findViewById(R.id.mood);
        mood.bringToFront();
        mood.setTextColor(Color.parseColor("#FFFFFF"));
        mood.setText(pickedMood);
        Button backButton = findViewById(R.id.backButton);
        backButton.bringToFront();

        readCSV();

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }

    public void readCSV() {
        InputStream csvStream = getResources().openRawResource(R.raw.tagmoods);
        BufferedReader reader = new BufferedReader(new InputStreamReader(csvStream, Charset.forName("UTF-8")));
        String songRawString = "";

        try {
            while((songRawString = reader.readLine()) != null) {
                String[] fields = songRawString.split(",");
                if (fields.length > 0) {
                    if (fields[3].equals(pickedMood)) {
                        song.setNumber(fields[0]);
                        song.setArtist(fields[1]);
                        song.setTitle(fields[2]);
                        song.setMood(fields[3]);
                        songs.add(song);
                        Log.i("BLAH","Songs added a song");
                    }
                }

            }
        } catch (IOException e) {
            // do some logging
        }
        finally {
            try {
                reader.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        //System.out.println("BLAH" + songs.size());

    }

}
