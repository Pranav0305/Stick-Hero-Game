package com.example.final_submission_2022363_2022310;

import java.io.Serializable;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class PauseMenu implements Menu, Serializable {
    private boolean gameOver;

    private Pane pane;
    private Button resumeButton;
    private Button saveButton;
    private Button mainMenuButton;
    private Label gameOverLabel;
    private Label notEnoughCherriesLabel;
    private Pane pane1;
    private Label highScoreTextLabel;
    private Label highScoreLabel;

    private static PauseMenu pauseMenu;

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

    public Button getresumeButton() {
        return resumeButton;
    }

    public void setresumeButton(Button resumeButton) {
        this.resumeButton = resumeButton;
    }

    public Button getMainMenuButton() {
        return mainMenuButton;
    }

    public void setMainMenuButton(Button mainMenuButton) {
        this.mainMenuButton = mainMenuButton;
    }

    public Button getsaveButton() {
        return saveButton;
    }

    public void setsaveButton(Button saveButton) {
        this.saveButton = saveButton;
    }

    private PauseMenu() {
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
        // pane.setBorder(new Border(new BorderStroke(Color.BLACK,
        // BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));

        mainMenuButton = new Button();
        mainMenuButton.setText("MAIN MENU");
        mainMenuButton.setTextFill(Color.BLACK);
        mainMenuButton.setScaleX(1.7);
        mainMenuButton.setScaleY(1.7);
        mainMenuButton.setLayoutX(80);
        mainMenuButton.setLayoutY(170);
        mainMenuButton.setFont(Font.font("TimesNewRoman", FontWeight.BOLD, 14));
        pane.getChildren().add(mainMenuButton);

        resumeButton = new Button();
        resumeButton.setText("RESUME");
        resumeButton.setTextFill(Color.BLACK);
        resumeButton.setScaleX(1.7);
        resumeButton.setScaleY(1.7);
        resumeButton.setLayoutX(95);
        resumeButton.setLayoutY(100);
        resumeButton.setFont(Font.font("TimesNewRoman", FontWeight.BOLD, 14));
        pane.getChildren().add(resumeButton);

        gameOverLabel = new Label("GAME PAUSED");
        gameOverLabel.setFont(Font.font("TimesNewRoman", FontWeight.BOLD, 40));
        gameOverLabel.setLayoutX(15);
        gameOverLabel.setLayoutY(-50);
        pane.getChildren().add(gameOverLabel);

        saveButton = new Button();
        saveButton.setText("SAVE GAME");
        saveButton.setTextFill(Color.BLACK);
        saveButton.setScaleX(1.7);
        saveButton.setScaleY(1.7);
        saveButton.setLayoutX(85);
        saveButton.setLayoutY(240);
        saveButton.setFont(Font.font("TimesNewRoman",FontWeight.BOLD,14));
        pane.getChildren().add(saveButton);

        Game.getRoot().getChildren().add(pane);
        // saveButton.setOnMouseClicked(event->{
        // System.exit(0);
        // });
    }

    public static PauseMenu getInstance()
    {
        if(pauseMenu != null)
        {
            return pauseMenu;
        }
        pauseMenu = new PauseMenu();
        return pauseMenu;
    }

    @Override
    public void resume()
    {

    }

    public void save() {
    }

    @Override
    public void exit() {
    }

    public boolean getGameOver() {
        return this.gameOver;
    }

    public void setGameOver() {
    }

    public void Respawn() {
    }

    public void restart() {
    }

}
