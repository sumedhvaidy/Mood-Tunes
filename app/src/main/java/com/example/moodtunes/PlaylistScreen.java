package com.example.moodtunes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.w3c.dom.Text;

public class PlaylistScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_playlist_screen);
        Intent moodIntent = getIntent();
        String pickedMood = moodIntent.getStringExtra("mood");
        System.out.println("after: " + pickedMood);
        TextView mood = findViewById(R.id.mood);
        mood.bringToFront();
        mood.setTextColor(Color.parseColor("#FFFFFF"));
        mood.setText(pickedMood);
        Button backButton = findViewById(R.id.backButton);
        backButton.bringToFront();
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
