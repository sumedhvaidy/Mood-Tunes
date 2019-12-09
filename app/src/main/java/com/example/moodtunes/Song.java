package com.example.moodtunes;

public class Song {
    private String number;
    private String title;
    private String artist;
    private String songMood;

    public String getTitle() {
        return title;
    }
    public String getNumber() { return number; }

    public void setNumber(String numberName) { this.number = numberName; }

    public void setTitle(String titleName) {
        this.title = titleName;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artistName) {
        this.artist = artistName;
    }

    public String getMood() {
        return songMood;
    }

    public void setMood(String moodName) {
        this.songMood = moodName;
    }
}
