package com.example.mid_submission_2022363_2022310;

import javafx.scene.shape.Rectangle;

import java.io.Serializable;

public class Platform implements Serializable
{
    private double width;
    private double bonusPosx;
    private double charPosx;
    private double stickPosx;
    private Rectangle rectangle;

    private double posX;

    public void setPosX(double posX) {
        this.posX = posX;
    }

    public double getPosX() {
        return posX;
    }

    public void setRectangle(Rectangle rectangle) {
        this.rectangle = rectangle;
    }

    public Rectangle getRectangle() {
        return rectangle;
    }

    public Platform(double width, double posX)
    {
        rectangle = new Rectangle();
        rectangle.setHeight(220);
        rectangle.setWidth(width);
        rectangle.setX(posX);
        rectangle.setY(300);
        this.stickPosx = posX + width;
        this.posX = posX;
        Game.getRoot().getChildren().add(rectangle);
    }

    public double getWidth(){return this.width;}
    public void setWidth(double width){}
    public double getBonusPosx(){return this.bonusPosx;}
    public void setBonusPosx(double bonusPosx){}
    public double getCharPosx(){return this.charPosx;}
    public void setCharPosx(double charPosx){}
    public double getStickPosx(){return this.stickPosx;}
    public void setStickPosx(double stickPosx){}
    public void spawnCherry(){}

}
