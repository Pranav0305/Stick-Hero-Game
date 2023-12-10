package com.example.final_submission_2022363_2022310;

import java.io.Serializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class GameOverMenu implements Menu,Serializable
{
    private Pane pane;
    private Button respawnButton;
    private Button quitButton;
    private Button mainMenuButton;
    private Label gameOverLabel;
    private Label notEnoughCherriesLabel;
    private Pane pane1;
    private Label highScoreTextLabel;
    private Label highScoreLabel;

    public Label getHighScoreLabel() {
        return highScoreLabel;
    }

    public void setHighScoreLabel(Label highScoreLabel) {
        this.highScoreLabel = highScoreLabel;
    }

    public Label getHighScoreTextLabel() {
        return highScoreTextLabel;
    }

    public void setHighScoreTextLabel(Label highScoreTextLabel) {
        this.highScoreTextLabel = highScoreTextLabel;
    }

    public Pane getPane1() {
        return pane1;
    }

    public void setPane1(Pane pane1) {
        this.pane1 = pane1;
    }

    public Label getNotEnoughCherriesLabel() {
        return notEnoughCherriesLabel;
    }

    public void setNotEnoughCherriesLabel(Label notEnoughCherriesLabel) {
        this.notEnoughCherriesLabel = notEnoughCherriesLabel;
    }

    public Label getGameOverLabel() {
        return gameOverLabel;
    }

    public void setGameOverLabel(Label gameOverLabel) {
        this.gameOverLabel = gameOverLabel;
    }

    public Pane getPane() {
        return pane;
    }

    public void setPane(Pane pane) {
        this.pane = pane;
    }


    public Button getRespawnButton() {
        return respawnButton;
    }

    public void setRespawnButton(Button respawnButton) {
        this.respawnButton = respawnButton;
    }

    public Button getMainMenuButton() {
        return mainMenuButton;
    }

    public void setMainMenuButton(Button mainMenuButton) {
        this.mainMenuButton = mainMenuButton;
    }


    public Button getQuitButton() {
        return quitButton;
    }

    public void setQuitButton(Button quitButton) {
        this.quitButton = quitButton;
    }

    public GameOverMenu()
    {
        pane1 = new Pane();
        pane1.setStyle("-fx-background-color: BLACK;");
        pane1.setOpacity(0.4);
        pane1.setPrefSize(850, 500);
        Game.getRoot().getChildren().add(pane1);

        pane = new Pane();
        pane.setStyle("-fx-background-color:  rgba(0, 100, 100, 0)");
        pane.setPrefSize(300, 320);
        pane.setLayoutX(280);
        pane.setLayoutY(150);
        // pane.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));

        mainMenuButton = new Button();
        mainMenuButton.setText("MAIN MENU");
        mainMenuButton.setTextFill(Color.BLACK);
        mainMenuButton.setScaleX(1.7);
        mainMenuButton.setScaleY(1.7);
        mainMenuButton.setLayoutX(80);
        mainMenuButton.setLayoutY(170);
        mainMenuButton.setFont(Font.font("TimesNewRoman",FontWeight.BOLD,14));
        pane.getChildren().add(mainMenuButton);

        respawnButton = new Button();
        respawnButton.setText("RESPAWN");
        respawnButton.setTextFill(Color.BLACK);
        respawnButton.setScaleX(1.7);
        respawnButton.setScaleY(1.7);
        respawnButton.setLayoutX(85);
        respawnButton.setLayoutY(100);
        respawnButton.setFont(Font.font("TimesNewRoman",FontWeight.BOLD,14));
        pane.getChildren().add(respawnButton);


        // quitButton = new Button();
        // quitButton.setText("QUIT");
        // quitButton.setTextFill(Color.BLACK);
        // quitButton.setScaleX(1.7);
        // quitButton.setScaleY(1.7);
        // quitButton.setLayoutX(105);
        // quitButton.setLayoutY(240);
        // quitButton.setFont(Font.font("TimesNewRoman",FontWeight.BOLD,14));
        // pane.getChildren().add(quitButton);

        gameOverLabel = new Label("GAME OVER!");
        gameOverLabel.setFont(Font.font("TimesNewRoman",FontWeight.BOLD,40));
        gameOverLabel.setLayoutX(15);
        gameOverLabel.setLayoutY(-50);
        pane.getChildren().add(gameOverLabel);

        highScoreTextLabel = new Label("HIGH SCORE: ");
        highScoreTextLabel.setFont(Font.font("TimesNewRoman",FontWeight.BOLD,40));
        highScoreTextLabel.setLayoutX(-10);
        highScoreTextLabel.setLayoutY(0);
        pane.getChildren().add(highScoreTextLabel);


        highScoreLabel = new Label();
        highScoreLabel.setFont(Font.font("TimesNewRoman",FontWeight.BOLD,40));
        highScoreLabel.setLayoutX(240);
        highScoreLabel.setLayoutY(0);
        pane.getChildren().add(highScoreLabel);

        notEnoughCherriesLabel=new Label("NEED 5 CHERRIES TO RESPAWN");
        notEnoughCherriesLabel.setFont(Font.font("TimesNewRoman",FontWeight.BOLD,30));
        notEnoughCherriesLabel.setLayoutX(-50);
        notEnoughCherriesLabel.setLayoutY(250);
        notEnoughCherriesLabel.setVisible(false);
        pane.getChildren().add(notEnoughCherriesLabel);

        Game.getRoot().getChildren().add(pane);

        // quitButton.setOnMouseClicked(event->{
        //     System.exit(0);
        // });
    }

    @Override
    public void resume() {}

    @Override
    public void exit() {}

}
