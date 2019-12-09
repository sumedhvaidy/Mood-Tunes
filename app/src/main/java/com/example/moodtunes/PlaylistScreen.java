package com.example.moodtunes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.moodtunes.Connectors.UserService;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PlaylistScreen extends AppCompatActivity {

    public List<Song> songs = new ArrayList<Song>();
    public Song song = new Song();
    public String pickedMood;

    private String[] playlists;

    private Button optionOne;
    private Button optionTwo;
    private Button optionThree;

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

        playlists = moodIntent.getStringArrayExtra(pickedMood);

        optionOne = findViewById(R.id.playlistOne);
        optionTwo = findViewById(R.id.playlistTwo);
        optionThree = findViewById(R.id.playlistThree);

        String optionOneText = pickedMood + " Playlist 1";
        String optionTwoText = pickedMood + " Playlist 2";
        String optionThreeText = pickedMood + " Playlist 3";
        optionOne.setText(optionOneText);
        optionTwo.setText(optionTwoText);
        optionThree.setText(optionThreeText);




        optionOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openPlaylist(0);
            }
        });

        optionTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openPlaylist(1);
            }
        });

        optionThree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openPlaylist(2);
            }
        });




        Intent mainIntent = new Intent(this, MainActivity.class);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(mainIntent);
                finish();
            }
        });
    }

    public void openPlaylist(int playlistIndex) {
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(playlists[playlistIndex]));
        startActivity(browserIntent);
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


    public void createPlayList() {

    }





    private void sendWorkPostRequest() {
        RequestQueue queue = Volley.newRequestQueue(this);
        try {
            String URL = "";
            JSONObject jsonBody = new JSONObject();
            //User getUser = UserService.getUser();
            //Log.i("BANANA", getUser.id);
            jsonBody.put("name", "My Awesome Tracks");

            JsonObjectRequest jsonObject = new JsonObjectRequest(Request.Method.POST, URL, jsonBody, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    Toast.makeText(getApplicationContext(), "Response:  " + response.toString(), Toast.LENGTH_SHORT).show();
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                    onBackPressed();

                }
            }) {
                @Override
                public Map<String, String> getHeaders() throws AuthFailureError {
                    final Map<String, String> headers = new HashMap<>();
                    headers.put("Authorization", "31d76a4540fe4b5ea9f57696acbb4309");//put your token here
                    headers.put("Content-Type", "application/json");
                    return headers;
                }
            };
            queue.add(jsonObject);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        // Toast.makeText(getApplicationContext(), "done", Toast.LENGTH_LONG).show();

    }
}























