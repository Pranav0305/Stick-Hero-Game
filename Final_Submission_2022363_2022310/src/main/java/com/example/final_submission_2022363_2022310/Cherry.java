package com.example.final_submission_2022363_2022310;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.Serializable;

public class Cherry implements Serializable
{
    private Image cherryImage;

    public ImageView getCherryIv() {
        return cherryIv;
    }

    public void setCherryIv(ImageView cherryIv) {
        this.cherryIv = cherryIv;
    }

    private ImageView cherryIv;

    private double posX;

    public double getPosX() {
        return posX;
    }

    public void setPosX(double posX) {
        this.posX = posX;
    }

    public Image getCherryImage() {
        return cherryImage;
    }

    public void setCherryImage(Image cherryImage) {
        this.cherryImage = cherryImage;
    }

    public Cherry(double posX) throws FileNotFoundException {
        Image cherryImage = new Image(new FileInputStream("src\\main\\resources\\com\\example\\final_submission_2022363_2022310\\Cherry_01.png"));
        this.cherryImage = cherryImage;
        this.posX = posX;
        cherryIv = new ImageView(cherryImage);
        cherryIv.setFitWidth(40);
        cherryIv.setFitHeight(30);
        this.cherryIv.setX(posX);
        this.cherryIv.setY(310);
        Game.getRoot().getChildren().add(cherryIv);
    }
}
