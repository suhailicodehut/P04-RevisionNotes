package com.myapplicationdev.android.p04_revisionnotes;

public class Note {
    private int id;
    private String content;
    private int stars;

    public Note(int id, String content, int stars) {
        this.id = id;
        this.content = content;
        this.stars = stars;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getStars() {
        return stars;
    }

    public void setStars(int stars) {
        this.stars = stars;
    }

    //What's here?

}
