package com.example.final_submission_2022363_2022310;

import javafx.scene.shape.Line;

import java.io.Serializable;

public class Stick implements Serializable
{
    private Line line;
    private double stickLength = 0;
    private int canRotate = 1;
    private int canExtend = 1;

    public int getCanExtend() {
        return canExtend;
    }

    public void setCanExtend(int canExtend) {
        this.canExtend = canExtend;
    }

    public int getCanRotate() {
        return canRotate;
    }

    public void setCanRotate(int canRotate) {
        this.canRotate = canRotate;
    }

    public void setLine(Line line) {
        this.line = line;
    }

    public double getStickLength() {
        return stickLength;
    }

    public void setStickLength(double stickLength) {
        this.stickLength = stickLength;
    }

    public Line getLine() {
        return line;
    }

    public Stick(double posX,double posY)
    {
        line = new Line();
        line.setStartX(posX);
        line.setEndX(posX);
        line.setStartY(posY);
        line.setEndY(posY);
        Game.getRoot().getChildren().add(line);
    }

    public void fall(){}


    public void extendStick()
    {
        line.setEndY(line.getEndY()-2);
    }

}
