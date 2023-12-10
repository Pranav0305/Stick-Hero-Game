package com.example.final_submission_2022363_2022310;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class MainMenu implements Menu, Serializable
{
    private boolean gameOver;
    public boolean isGameOver() {
        return gameOver;
    }

    public void setGameOver(boolean gameOver) {
        this.gameOver = gameOver;
    }
    private  Pane pane;

    public Pane getPane() {
        return pane;
    }

    public void setPane(Pane pane) {
        this.pane = pane;
    }

    private Button newGameButton;


    public Button getNewGameButton() {
        return newGameButton;
    }

    public void setNewGameButton(Button newGameButton) {
        this.newGameButton = newGameButton;
    }

    private Button loadButton;

    public Button getLoadGameButton() {
        return loadButton;
    }

    public void setLoadGameButton(Button loadButton) {
        this.loadButton = loadButton;
    }

    public MainMenu()
    {
        pane = new Pane();
        newGameButton = new Button();
        Button exitButton=new Button();
        loadButton=new Button();
        try {
            Image bgImage = new Image(new FileInputStream("src\\main\\resources\\com\\example\\final_submission_2022363_2022310\\bgimage_mainMenu.png"));
            ImageView img=new ImageView(bgImage);
            pane.getChildren().add(img);
            img.setLayoutX(-125);
            img.setLayoutY(-25);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        loadButton.setText("LOAD GAME");
        loadButton.setFont(Font.font("TimesNewRoman",FontWeight.BOLD,14));
        loadButton.setScaleX(1.50);
        loadButton.setScaleY(1.50);
        loadButton.setTextFill(Color.BLACK);
        loadButton.setLayoutX(175);
        loadButton.setLayoutY(215);
        newGameButton.setText("NEW GAME");
        newGameButton.setFont(Font.font("TimesNewRoman",FontWeight.BOLD,14));
        newGameButton.setScaleX(1.50);
        newGameButton.setScaleY(1.50);
        newGameButton.setTextFill(Color.BLACK);
        newGameButton.setLayoutX(175);
        newGameButton.setLayoutY(150);
        exitButton.setText("EXIT");
        exitButton.setFont(Font.font("TimesNewRoman",FontWeight.BOLD,14));
        exitButton.setScaleX(1.50);
        exitButton.setScaleY(1.50);
        exitButton.setTextFill(Color.BLACK);
        exitButton.setLayoutX(163);
        exitButton.setLayoutY(280);
        pane.getChildren().add(newGameButton);
        pane.getChildren().add(exitButton);
        pane.getChildren().add(loadButton);
        Game.getRoot().getChildren().add(pane);


        exitButton.setOnMouseClicked(event->{
            System.exit(0);
        });


    }

    public void resume(){}
    public void newGame(){}
    public void exit(){}
}
