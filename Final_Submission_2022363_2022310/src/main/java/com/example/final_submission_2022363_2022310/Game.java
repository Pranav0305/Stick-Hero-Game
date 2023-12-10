package com.example.final_submission_2022363_2022310;

import javafx.animation.TranslateTransition;
import javafx.application.*;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.text.Font;
import javafx.scene.media.Media;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.*;
// import javafx.scene.media.Media;

public class Game extends Application{
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

    private static MainMenu mainMenu;

    private static int respawn = 0;

    private static Button pauseButton;

    public Button getPauseButton() {
        return pauseButton;
    }

    public void setPauseButton(Button pauseButton) {
        this.pauseButton = pauseButton;
    }


    public static int getRespawn() {
        return respawn;
    }

    public static void setRespawn(int respawn) {
        Game.respawn = respawn;
    }

    public static MainMenu getMainMenu() {
        return mainMenu;
    }

    public void setMainMenu(MainMenu mainMenu) {
        this.mainMenu = mainMenu;
    }

    private static Label scoreLabel;
    private static Label cherryCountLabel;

    private FileInputStream cherriesSaveRead;
    private FileOutputStream cherriesSaveWrite;

    private FileInputStream highScoreSaveRead;
    private FileOutputStream highScoreSaveWrite;

    private static int cherriesSaved;

    public FileOutputStream getHighScoreSaveWrite() {
        return highScoreSaveWrite;
    }

    public void setHighScoreSaveWrite(FileOutputStream highScoreSaveWrite) {
        this.highScoreSaveWrite = highScoreSaveWrite;
    }


    public int getCherriesSaved() {
        return cherriesSaved;
    }

    public void setCherriesSaved(int cherriesSaved) {
        this.cherriesSaved = cherriesSaved;
    }

    public FileOutputStream getCherriesSaveWrite() {
        return cherriesSaveWrite;
    }

    public void setCherriesSaveWrite(FileOutputStream cherriesSaveWrite) {
        this.cherriesSaveWrite = cherriesSaveWrite;
    }


    public FileInputStream getHighScoreSaveRead() {
        return highScoreSaveRead;
    }

    public void setHighScoreSaveRead(FileInputStream highScoreSave) {
        this.highScoreSaveRead = highScoreSave;
    }

    public FileInputStream getCherriesSaveRead() {
        return cherriesSaveRead;
    }

    public void setCherriesSaveRead(FileInputStream cherriesSave) {
        this.cherriesSaveRead = cherriesSave;
    }

    public void setCherryCountLabel(Label cherryCountLabel)
    {
        this.cherryCountLabel = cherryCountLabel;
    }

    public Label getCherryCountLabel()
    {
        return cherryCountLabel;
    }


    public static Button getFlipButton() {
        return flipButton;
    }

    public static void setFlipButton(Button flipButton) {
        Game.flipButton = flipButton;
    }


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

    private Media clickMedia=new Media(new File("src/Files/click_Audio.mp3").toURI().toString());
    private MediaPlayer clickMediaPlayer=new MediaPlayer(clickMedia);

    private Media cherryMedia=new Media(new File("src/Files/Cherry_Collect_01.mp3").toURI().toString());

    public MediaPlayer getCherryMediaPlayer() {
        return cherryMediaPlayer;
    }

    public void setCherryMediaPlayer(MediaPlayer cherryMediaPlayer) {
        this.cherryMediaPlayer = cherryMediaPlayer;
    }

    private MediaPlayer cherryMediaPlayer=new MediaPlayer(cherryMedia);
    private MediaView cherryMediaView=new MediaView(cherryMediaPlayer);

    public MediaView getClickMediaView() {
        return clickMediaView;
    }

    public void setClickMediaView(MediaView clickMediaView) {
        this.clickMediaView = clickMediaView;
    }

    private MediaView clickMediaView=new MediaView(clickMediaPlayer);

    public void setPlatform2(Platform platform2) {
        Game.platform2 = platform2;
    }

    public void setPlayerImage(Image playerImage) {
        this.playerImage = playerImage;
    }


    public MediaPlayer getClickMediaPlayer() {
        return clickMediaPlayer;
    }

    public void setClickMediaPlayer(MediaPlayer clickMediaPlayer) {
        this.clickMediaPlayer = clickMediaPlayer;
    }

    public void playCherryMedia(){
        cherryMediaPlayer.play();
    }

    @Override
    public void start(Stage stage) throws IOException
    {
        root = new Group();
//        root.getChildren().add(clickMediaView);
        root.getChildren().add(cherryMediaView);
        Media media=new Media(new File("src/Files/Audio.mp3").toURI().toString());
        MediaPlayer mediaPlayer=new MediaPlayer(media);
        mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
        mediaPlayer.play();
        // Flyweight design pattern
        // Player has a unique character associated with it
        Character character = new Character();
        Player player = Player.getInstance(character);
        MediaView mediaView=new MediaView(mediaPlayer);
        root.getChildren().add(mediaView);
        GameController gameController = new GameController();

        Scene scene = new Scene(Game.getRoot(), 825, 500);
        stage.setTitle("Stick Hero");
        stage.setScene(scene);
        stage.show();
        mainMenu=new MainMenu();

        mainMenu.getNewGameButton().setOnMouseClicked(event->
        {
            Media clickMedia=new Media(new File("src/Files/click_Audio.mp3").toURI().toString());
            MediaPlayer clickMediaPlayer=new MediaPlayer(clickMedia);
            clickMediaPlayer.play();
            score = 0;
            scoreLabel.setText(String.valueOf(0));
            gameController.setNewGame(1);
            gameController.respawnChar();
        });

        mainMenu.getLoadGameButton().setOnMouseClicked(event->
        {
            Media clickMedia=new Media(new File("src/Files/click_Audio.mp3").toURI().toString());
            MediaPlayer clickMediaPlayer=new MediaPlayer(clickMedia);
            clickMediaPlayer.play();
            // System.out.println("Loade");
            gameController.loadGame();
        });

        Image bgImage = new Image(new FileInputStream("src\\main\\resources\\com\\example\\final_submission_2022363_2022310\\BG 01.jpg"));
        ImageView iv = new ImageView(bgImage);
        iv.setFitWidth(825);
        iv.setFitHeight(500);
        root.getChildren().add(iv);


//        playerImage = new Image(new FileInputStream("src/main/resources/com/example/mid_submission_2022363_2022310/Batman_Idle.png"));
        playerImage = new Image(new FileInputStream("src\\main\\resources\\com\\example\\final_submission_2022363_2022310\\Char_01_Gif_01.gif"));
        iv1 = new ImageView(playerImage);

        iv1.setScaleX(0.25);
        iv1.setScaleY(0.25);
        iv1.setY(142);
        iv1.setX(iv1.getX()-8);
        root.getChildren().add(iv1);

//        root.getChildren().add(bgImage);
        platform1 = new Platform(100,50);
        platform2 = new Platform(100,290);

        platform2.getRectangle().setY(500);
        TranslateTransition intitalMove = new TranslateTransition(Duration.millis(500),platform2.getRectangle());
        intitalMove.setToY(-200);
        intitalMove.play();

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

        cherriesSaveRead = new FileInputStream("src\\Files\\cherries.txt");
        int cherriesSaved = cherriesSaveRead.read();
        cherriesSaved = Math.max(0,cherriesSaved);
        gameController.setCherryCount(cherriesSaved);
        cherryCountLabel = new Label();
        cherryCountLabel.setText(String.valueOf(cherriesSaved));
        cherryCountLabel.setScaleX(3);
        cherryCountLabel.setScaleY(3);
        cherryCountLabel.setLayoutX(750);
        cherryCountLabel.setLayoutY(50);
//        scoreLabel.setTextFill(Color.color(1,1,1));
        root.getChildren().add(cherryCountLabel);

        Image cherryImageInd = new Image(new FileInputStream("src\\main\\resources\\com\\example\\final_submission_2022363_2022310\\Cherry_01.png"));
        ImageView cherryImageView = new ImageView(cherryImageInd);
        cherryImageView.setFitWidth(50);
        cherryImageView.setFitHeight(40);
        cherryImageView.setLayoutX(690);
        cherryImageView.setLayoutY(40);
        root.getChildren().add(cherryImageView);

        flipButton = new Button();
        flipButton.setPrefHeight(1000);
        flipButton.setPrefWidth(1000);
        flipButton.setOpacity(0);
        root.getChildren().add(flipButton);
        flipButton.setDisable(true);

        flipButton.setOnMouseClicked(mouseEvent -> {

            gameController.flipPlayer();
        });

        pauseButton = new Button();
        pauseButton.setText("||");
        pauseButton.setFont(Font.font("TimesNewRoman",FontWeight.BOLD,14));
        pauseButton.setScaleX(1.5);
        pauseButton.setScaleY(1.5);
        pauseButton.setLayoutX(20);
        pauseButton.setLayoutY(20);
        root.getChildren().add(pauseButton);

        mainMenu.getPane().toFront();
        // pauseButton.toFront();

        pauseButton.setOnMouseClicked(event->
        {
            Media clickMedia=new Media(new File("src/Files/click_Audio.mp3").toURI().toString());
            MediaPlayer clickMediaPlayer=new MediaPlayer(clickMedia);
            clickMediaPlayer.play();
            gameController.pauseGame();
        });

    }


    public static void main(String[] args) {
        launch();
    }
}
