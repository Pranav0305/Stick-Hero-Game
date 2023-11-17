package com.example.mid_submission_2022363_2022310;

import java.io.Serializable;

public class Player implements Serializable
{
    private Character character;
    private int cherryCount;
    private int score;
    private boolean isPaused;

    public void respawnChar(double posX){}
    public void spawnPlatform(double posX){}
    public void spawnStick(double posX){}
    public Character getChar(){return this.character;}
    public void setCherryCount(int cherryCount){}
    public int getCherryCount(){return this.cherryCount;}
    public void setScore(int score){}
    public int getScore(){return this.score;}
    public void pause(){}

}
