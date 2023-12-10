package com.example.final_submission_2022363_2022310;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Line;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

public class Player implements Serializable
{
    private static HashMap<Character,Player> m = new HashMap<>();
    private Player player;

    private Player() {}

    public static Player getInstance(Character character)
    {
        if(m.containsKey(character))
            return m.get(character);
        m.put(character,new Player());
        return m.get(character);
    }

    private Character character;
    private int cherryCount;
    private int score;
    private boolean isPaused;

    public void respawnChar(double posX){}
    public void spawnPlatform(double posX){}
    public void spawnStick(double posX)
    {
        EventHandler<MouseEvent> eventHandler = new EventHandler<MouseEvent>()
        {
            @Override
            public void handle(MouseEvent e)
            {
                Line line = new Line();
                line.setStartX(posX);
                line.setEndX(200);
            }
        };
    }
    public Character getChar(){return this.character;}
    public void setCherryCount(int cherryCount){}
    public int getCherryCount(){return this.cherryCount;}
    public void setScore(int score){}
    public int getScore(){return this.score;}
    public void pause(){}

}
