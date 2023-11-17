package com.example.mid_submission_2022363_2022310;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.io.Serializable;

public class GameController implements Serializable {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }
}