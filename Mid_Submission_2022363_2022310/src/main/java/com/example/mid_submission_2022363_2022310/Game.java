package com.example.mid_submission_2022363_2022310;
import javafx.application.*;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.OverrunStyle;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class Game extends Application
{
    private static Group root;

    public static Group getRoot() {
        return root;
    }

    public static void setRoot(Group root) {
        Game.root = root;
    }

    private static Platform platform1;
    private static Platform platform2;

    private int score = 0;
    private Image playerImage;
    private static ImageView iv1;

    private static Button univButton;
    private static Button flipButton;

    public static Button getFlipButton() {
        return flipButton;
    }

    public static void setFlipButton(Button flipButton) {
        Game.flipButton = flipButton;
    }

    private static Label scoreLabel;

    public Label getScoreLabel() {
        return scoreLabel;
    }

    public void setScoreLabel(Label scoreLabel) {
        this.scoreLabel = scoreLabel;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void setUnivButton(Button univButton) {
        this.univButton = univButton;
    }

    public Button getUnivButton() {
        return univButton;
    }

    public Image getPlayerImage() {
        return playerImage;
    }

    public ImageView getIv1() {
        return iv1;
    }

    public Platform getPlatform1() {
        return platform1;
    }

    public Platform getPlatform2() {
        return platform2;
    }

    public void setIv1(ImageView iv1) {
        this.iv1 = iv1;
    }

    public void setPlatform1(Platform platform1) {
        Game.platform1 = platform1;
    }

    public void setPlatform2(Platform platform2) {
        Game.platform2 = platform2;
    }

    public void setPlayerImage(Image playerImage) {
        this.playerImage = playerImage;
    }

    @Override
    public void start(Stage stage) throws IOException
    {
        root = new Group();

        GameController gameController = new GameController();

        Scene scene = new Scene(root, 825, 500);
        stage.setTitle("Stick Hero");
        stage.setScene(scene);
        stage.show();

        Image bgImage = new Image(new FileInputStream("CSE201_AP_Project\\Mid_Submission_2022363_2022310\\src\\main\\resources\\com\\example\\mid_submission_2022363_2022310\\BG 01.jpg"));
        ImageView iv = new ImageView(bgImage);
        root.getChildren().add(iv);


//        playerImage = new Image(new FileInputStream("src/main/resources/com/example/mid_submission_2022363_2022310/Batman_Idle.png"));
        playerImage = new Image(new FileInputStream("CSE201_AP_Project\\Mid_Submission_2022363_2022310\\src\\main\\resources\\com\\example\\mid_submission_2022363_2022310\\Char_01_Gif_01.gif"));
        iv1 = new ImageView(playerImage);

        iv1.setScaleX(0.25);
        iv1.setScaleY(0.25);
        iv1.setY(142);
        iv1.setX(iv1.getX()-8);
        root.getChildren().add(iv1);

//        root.getChildren().add(bgImage);
        platform1 = new Platform(100,50);
        platform2 = new Platform(100,290);

        univButton = new Button();
        univButton.setPrefHeight(1000);
        univButton.setPrefWidth(1000);
        univButton.setOpacity(0);

        univButton.setOnMousePressed(actionEvent ->
        {
            try {
                gameController.spawnStick(platform1);
            } catch (Exception e) {
                System.out.println(e.getMessage());
                System.exit(1);
            }
        });

        univButton.setOnMouseReleased(actionEvent->{
            try {
                gameController.rotStick(platform1, platform2,iv1);
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
        });

        root.getChildren().add(univButton);

        scoreLabel = new Label();
        scoreLabel.setText(String.valueOf(score));
        scoreLabel.setScaleX(5);
        scoreLabel.setScaleY(5);
        scoreLabel.setLayoutX(412);
        scoreLabel.setLayoutY(50);
//        scoreLabel.setTextFill(Color.color(1,1,1));
        root.getChildren().add(scoreLabel);

        flipButton = new Button();
        flipButton.setPrefHeight(1000);
        flipButton.setPrefWidth(1000);
        flipButton.setOpacity(0);
        root.getChildren().add(flipButton);
        flipButton.setDisable(true);

        flipButton.setOnMouseClicked(mouseEvent -> {
            gameController.flipPlayer();
        });

    }

    public static void main(String[] args) {
        launch();
    }
}
