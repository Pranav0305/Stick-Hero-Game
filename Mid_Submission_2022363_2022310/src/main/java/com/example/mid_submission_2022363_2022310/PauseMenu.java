package com.example.mid_submission_2022363_2022310;

import java.io.Serializable;

public class PauseMenu implements Menu, Serializable
{
    private boolean gameOver;

    @Override
    public void resume(){}

    public void save(){}

    @Override
    public void exit(){}

    public boolean getGameOver(){return this.gameOver;}
    public void setGameOver(){}
    public void Respawn(){}
    public void restart(){}

}
