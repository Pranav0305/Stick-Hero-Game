package com.example.final_submission_2022363_2022310;

import javafx.animation.*;
import javafx.event.EventHandler;
import javafx.geometry.Point3D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.MediaView;
import javafx.scene.transform.Rotate;
import javafx.util.Duration;

import java.io.*;
import java.util.ArrayList;
import java.util.Random;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class GameController extends Game implements Serializable {
    private ImageView iv2;

    private Stick stick;
    private int fallFlag = 0;


    private boolean isFlipped = false;

    private boolean isGameOver = false;

    private double platformMoveTime = 2000;

    private Cherry cherry;

    private ArrayList<Cherry> cherries = new ArrayList<>();

    private int cherryCount = getCherriesSaved();

    private boolean canFlip = false;

    private boolean collectedCherry;

    Timeline timeline;

    private GameOverMenu gameOverMenu;

    private int newGame = 0;

    private int minCherries = 5;

    private int highscore;

    private PauseMenu pauseMenu;

    private boolean isCherry = false;

    public PauseMenu getPauseMenu() {
        return pauseMenu;
    }

    public void setPauseMenu(PauseMenu pauseMenu) {
        this.pauseMenu = pauseMenu;
    }

    public int getHighscore() {
        return highscore;
    }

    public void setHighscore(int highscore) {
        this.highscore = highscore;
    }

    public int getMinCherries() {
        return minCherries;
    }

    public void setMinCherries(int minCherries) {
        this.minCherries = minCherries;
    }

    public int getNewGame() {
        return newGame;
    }

    public void setNewGame(int newGame) {
        this.newGame = newGame;
    }

    public GameOverMenu getGameOverMenu() {
        return gameOverMenu;
    }

    public void setGameOverMenu(GameOverMenu gameOverMenu) {
        this.gameOverMenu = gameOverMenu;
    }

    public boolean isCollectedCherry() {
        return collectedCherry;
    }

    public void setCollectedCherry(boolean collectedCherry) {
        this.collectedCherry = collectedCherry;
    }

    public void setCherryCount(int cherryCount) {
        this.cherryCount = cherryCount;
    }

    public int getCherryCount() {
        return this.cherryCount;
    }

    public boolean getIsGameOver() {
        return isGameOver;
    }

    public void setIsGameOver(boolean gameOver) {
        isGameOver = gameOver;
    }

    public boolean getIsFlipped() {
        return isFlipped;
    }

    public void setIsFlipped(boolean flipped) {
        isFlipped = flipped;
    }

    public ImageView getIv2() {
        return iv2;
    }

    public void setIv2(ImageView iv2) {
        this.iv2 = iv2;
    }

    public int getFallFlag() {
        return fallFlag;
    }

    public void setFallFlag(int fallFlag) {
        this.fallFlag = fallFlag;
    }

    private ArrayList<Stick> sticks = new ArrayList<>();

    public ArrayList<Stick> getSticks() {
        return sticks;
    }

    public void setSticks(ArrayList<Stick> sticks) {
        this.sticks = sticks;
    }

    public double getPlatformMoveTime() {
        return platformMoveTime;
    }

    public void setPlatformMoveTime(double platformMoveTime) {
        this.platformMoveTime = platformMoveTime;
    }

    public Stick getStick() {
        return stick;
    }

    public void setStick(Stick stick) {
        this.stick = stick;
    }

    public EventHandler<? super MouseEvent> spawnStick(Platform platform1)
    {
        GameTest gameTest = new GameTest();

        timeline = new Timeline(new KeyFrame(Duration.millis(10), event -> {
            if (stick == null) {
                Stick stick1;
                stick1 = new Stick(platform1.getStickPosx() - 2, platform1.getRectangle().getY());
                stick = stick1;
            }
            if (stick.getCanExtend() == 1) {
                stick.extendStick();
            }
        }));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();

        return null;
    }

    void rotStick(Platform platform1, Platform platform2, ImageView iv1) throws FileNotFoundException {
        timeline.stop();
        // stick.getLine().setStartX(stick.getLine().getStartX());
        stick.setCanExtend(0);

        if (stick.getCanRotate() == 1) {
            stick.setCanRotate(0);
            Rotate rotate = new Rotate(90, stick.getLine().getStartX(), stick.getLine().getStartY());
            stick.getLine().getTransforms().add(rotate);

            Timeline timeline1 = new Timeline(
                    new KeyFrame(Duration.ZERO, new KeyValue(rotate.angleProperty(), 0)),
                    new KeyFrame(Duration.seconds(0.4), new KeyValue(rotate.angleProperty(), 90)));
            timeline1.play();

            timeline1.setOnFinished(actionEvent -> {
                try {
                    sticks.add(stick);
                    moveEnvironment(platform1, platform2, iv1);
                } catch (FileNotFoundException e) {
                    throw new RuntimeException(e);
                }
            });

        }

    }

    void flipPlayer() {

        if (canFlip) {
            iv2.setRotationAxis(new Point3D(1, 0, 0));
            iv2.setRotate(iv2.getRotate() + 180);

            isFlipped = !isFlipped;

            if (isFlipped) {
                iv2.setY(iv2.getY() + 60);
            } else {
                iv2.setY(iv2.getY() - 60);
            }

        }

    }

    void moveEnvironment(Platform platform1, Platform platform2, ImageView iv1) throws FileNotFoundException {
        isFlipped = false;
        canFlip = true;
        Image runningPlayerImage = new Image(new FileInputStream("src\\main\\resources\\com\\example\\final_submission_2022363_2022310\\Char_01_Gif_02.gif"));
        // Image runningPlayerImage = new Image(new
        // FileInputStream("src/main/resources/com/example/mid_submission_2022363_2022310/Batman_Running.gif"));
        iv2 = new ImageView(runningPlayerImage);
        iv1.setVisible(false);
        iv2.setScaleX(0.25);
        iv2.setScaleY(0.25);
        iv2.setY(142);
        iv2.setX(iv1.getX());
        iv2.setRotate(0);

        try {
            Game.getRoot().getChildren().add(iv2);
        } catch (Exception e) {
        }

        double distance = getPlatform2().getRectangle().getX() - getPlatform1().getRectangle().getX();

        double min_dist = getPlatform2().getRectangle().getX() - getPlatform1().getRectangle().getX()
                - getPlatform2().getRectangle().getWidth() / 2 - getPlatform1().getRectangle().getWidth() / 2;
        double max_dist = distance + getPlatform2().getRectangle().getWidth() / 2
                - getPlatform1().getRectangle().getWidth() / 2;

        // System.out.println(stick.getLine().getStartY()-stick.getLine().getEndY() >
        // max_dist || stick.getLine().getStartY()-stick.getLine().getEndY() <

        // min_dist);
        // pos = platform2.getRectangle().getX();

        // platform1.getRectangle().setLayoutX(platform1.getRectangle().getLayoutX()-distance);
        // platform2.getRectangle().setLayoutX(platform2.getRectangle().getLayoutX()-distance);

        if (stick.getLine().getStartY() - stick.getLine().getEndY() > max_dist
                || stick.getLine().getStartY() - stick.getLine().getEndY() < min_dist) {
            distance = Math.abs(stick.getLine().getStartY() - stick.getLine().getEndY() + 20);
            fallFlag = 1;
        }

        TranslateTransition translateTransitionPlatform2 = new TranslateTransition(Duration.millis(platformMoveTime),
                platform2.getRectangle());
        TranslateTransition translateTransitionPlatform1 = new TranslateTransition(Duration.millis(platformMoveTime),
                platform1.getRectangle());
        TranslateTransition translateTransitionStick = new TranslateTransition(Duration.millis(platformMoveTime),
                stick.getLine());

        translateTransitionStick.setToX(-distance);
        translateTransitionPlatform1.setToX(-distance);
        translateTransitionPlatform2.setToX(-distance);

        TranslateTransition translateTransitionCherry;
        if (cherry != null) {
            translateTransitionCherry = new TranslateTransition(Duration.millis(platformMoveTime),
                    cherry.getCherryIv());
            translateTransitionCherry.setToX(-distance);
            translateTransitionCherry.play();
        }

        translateTransitionPlatform1.play();
        translateTransitionPlatform2.play();
        translateTransitionStick.play();
        // getIv2().setVisible(true);
        // getIv2().toFront();

        getFlipButton().setDisable(false);
        getFlipButton().toFront();
        // getPauseButton().toFront();

        Timeline collisionTimeline = new Timeline(new KeyFrame(Duration.millis(10), actionEvent -> {

            // System.out.println(platform2.getRectangle().getX());
            if (iv1.getBoundsInParent().intersects(platform2.getRectangle().getBoundsInParent()) && isFlipped) {
                // System.out.println("Game Over");
                if (collectedCherry == true) {
                    cherryCount -= 1;
                    getCherryCountLabel().setText(String.valueOf(cherryCount));
                    collectedCherry = false;

                    try {
                        setCherriesSaveWrite(new FileOutputStream(
                                "src\\Files\\cherries.txt"));
                        getCherriesSaveWrite().write(cherryCount);

                        try {
                            setHighScoreSaveRead(new FileInputStream(
                                    "src\\Files\\highscore.txt"));

                            highscore = Math.max(getScore(), getHighScoreSaveRead().read());

                            setHighScoreSaveWrite(new FileOutputStream(
                                    "src\\Files\\highscore.txt"));
                            getHighScoreSaveWrite().write(highscore);

                            // System.out.println(highscore);

                            getHighScoreSaveWrite().close();
                        } catch (Exception e) {
                        }

                    } catch (Exception e) {
                    }

                }
                translateTransitionStick.stop();
                translateTransitionPlatform1.stop();
                translateTransitionPlatform2.stop();
                isGameOver = true;

                return;
            }

            if (iv1.getBoundsInParent().intersects(platform2.getRectangle().getBoundsInParent()) && !isFlipped) {
                // System.out.println("Reached");
                canFlip = false;
            }

            if (isCherry && iv2.getBoundsInParent().intersects(cherry.getCherryIv().getBoundsInParent())) {
                // System.out.println("Cherry");
                if (fallFlag == 0) {
                    cherryCount += 1;
                    collectedCherry = true;
                    Media cherryMedia=new Media(new File("src/Files/Cherry_Collect_01.mp3").toURI().toString());
                    MediaPlayer cherryMediaPlayer=new MediaPlayer(cherryMedia);
                    MediaView cherryMediaView=new MediaView(cherryMediaPlayer);
                    cherryMediaPlayer.play();
//                    Game.getRoot().getChildren().add(cherryMediaView);
                }

                cherry.getCherryIv().setVisible(false);
                isCherry = false;
                getCherryCountLabel().setText(String.valueOf(cherryCount));

            }

        }));

        collisionTimeline.setCycleCount((int) platformMoveTime / 15);
        collisionTimeline.play();

        collisionTimeline.setOnFinished(actionEvent -> {
            if (isGameOver) // Flipped collision
            {
                iv2.setVisible(false);
                ImageView iv3;
                Image deadImage = null;
                try {
                    deadImage = new Image(new FileInputStream(
                            "src\\main\\resources\\com\\example\\final_submission_2022363_2022310\\Char_01_Gif_01.gif"));
                } catch (FileNotFoundException e) {
                    throw new RuntimeException(e);
                }

                iv3 = new ImageView(deadImage);

                iv3.setScaleX(0.25);
                iv3.setScaleY(0.25);
                iv3.setY(202);
                iv3.setX(iv3.getX() - 8);
                iv3.setRotationAxis(new Point3D(1, 0, 0));
                iv3.setRotate(180);
                Game.getRoot().getChildren().add(iv3);

                TranslateTransition playerFall = new TranslateTransition(Duration.millis(500), iv3);
                playerFall.setToY(900);
                playerFall.play();

                gameOverMenu = new GameOverMenu();

                gameOverMenu.getRespawnButton().setOnMouseClicked(event1 -> {
                    // gameOverLabel.setVisible(false);
                    Media clickMedia=new Media(new File("src/Files/click_Audio.mp3").toURI().toString());
                    MediaPlayer clickMediaPlayer=new MediaPlayer(clickMedia);
                    clickMediaPlayer.play();
                    if (cherryCount >= minCherries) {
                        cherryCount -= minCherries;
                        cherryCount = Math.max(0, cherryCount);
                        getCherryCountLabel().setText(String.valueOf(cherryCount));
                        respawnChar();
                    } else {
                        gameOverMenu.getNotEnoughCherriesLabel().setVisible(true);
                    }
                });

                gameOverMenu.getMainMenuButton().setOnMouseClicked(event -> {
                    // gameOverMenu.getPane().setVisible(false);
                    Media clickMedia=new Media(new File("src/Files/click_Audio.mp3").toURI().toString());
                    MediaPlayer clickMediaPlayer=new MediaPlayer(clickMedia);
                    clickMediaPlayer.play();
                    getMainMenu().getPane().toFront();
                });

                try {
                    setHighScoreSaveRead(new FileInputStream(
                            "src\\Files\\highscore.txt"));
                    highscore = Math.max(getScore(), getHighScoreSaveRead().read());

                    gameOverMenu.getHighScoreLabel().setText(String.valueOf(highscore));
                    gameOverMenu.getHighScoreLabel().setVisible(true);
                } catch (Exception e) {
                }

            }
        });

        translateTransitionPlatform2.setOnFinished(actionEvent -> {
            // System.out.println("Moved");
            collisionTimeline.stop();
            getFlipButton().setDisable(true);

            for (Cherry cherry : cherries) {
                cherry.getCherryIv().setVisible(false);
            }

            iv2.setVisible(false);
            iv1.setVisible(true);
            double w = getPlatform2().getRectangle().getWidth();

            // pos = platform2.getRectangle().getX();

            if (fallFlag == 0) {
                getPlatform1().getRectangle().setVisible(false);
                getPlatform2().getRectangle().setVisible(false);
                setPlatform1(new Platform(w, 50));
                Random random = new Random();
                Platform newPlatform = new Platform(random.nextInt(70, 100), random.nextInt(230, 400));
                newPlatform.getRectangle().setY(900);
                setPlatform2(newPlatform);
                getPauseButton().toFront();
            }

            TranslateTransition spawnPlatform = new TranslateTransition(Duration.millis(400),
                    getPlatform2().getRectangle());
            spawnPlatform.setToY(getPlatform1().getRectangle().getY() - getPlatform2().getRectangle().getY());
            spawnPlatform.play();

            if (fallFlag == 0) {
                for (int i = 0; i < sticks.size() - 1; i++) {
                    sticks.get(i).getLine().setVisible(false);
                }
            }

            spawnPlatform.setOnFinished(actionEvent1 -> {
                getUnivButton().toFront();
                getPauseButton().toFront();
                if (fallFlag == 0) {
                    Random rand = new Random();
                    double d = rand.nextDouble();
                    double st = this.getPlatform1().getRectangle().getX()
                            + ((this.getPlatform1().getRectangle()).getWidth() / 2);
                    double en = this.getPlatform2().getRectangle().getX()
                            - ((this.getPlatform2().getRectangle().getWidth()) / 2);
                    double position = st + 40 + (d * (en - st - 30));
                    try {
                        cherry = new Cherry(position);
                        cherries.add(cherry);
                        int r;
                        Random random = new Random();
                        r = random.nextInt(0,10);
                        if(r < 2)
                        {
                            cherry.getCherryIv().setVisible(false);
                            isCherry = false;
                        }
                        else
                        {
                            isCherry = true;
                        }
                    } catch (FileNotFoundException e) {
                        throw new RuntimeException(e);
                    }

                    setScore(getScore() + 1);
                    getScoreLabel().setText(String.valueOf(getScore()));
                    stick = null;
                } else if (fallFlag == 1) {
                    // System.out.println("Fall");
                    Image fallImage = null;
                    try {
                        fallImage = new Image(new FileInputStream(
                                "src\\main\\resources\\com\\example\\final_submission_2022363_2022310\\Char_01_Gif_01.gif"));
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }

                    ImageView fallIv = new ImageView(fallImage);

                    fallIv.setScaleX(0.25);
                    fallIv.setScaleY(0.25);
                    fallIv.setY(142);
                    fallIv.setX(iv1.getX());
                    Game.getRoot().getChildren().add(fallIv);

                    iv1.setVisible(false);
                    TranslateTransition playerFall = new TranslateTransition(Duration.millis(500), fallIv);
                    playerFall.setToY(900);
                    playerFall.play();

                    playerFall.setOnFinished(event -> {
                        // Timeline gameOverTimeline = new Timeline(new
                        // KeyFrame(Duration.millis(20),event1->
                        // {
                        // gameOverLabel=new Label("Game Over!");
                        // gameOverLabel.setFont(Font.font("TimesNewRoman",FontWeight.BOLD,40));
                        // gameOverLabel.setLayoutX(310);
                        // gameOverLabel.setLayoutY(100);
                        // try{
                        // Game.getRoot().getChildren().add(gameOverLabel);}
                        // catch(Exception e){}
                        // gameOverLabel.setVisible(true);
                        // }));

                        // gameOverTimeline.setCycleCount(100);
                        // gameOverTimeline.play();

                        // Button resButton = new Button();
                        // resButton.setScaleX(2);
                        // resButton.setScaleY(2);

                        // Game.getRoot().getChildren().add(resButton);
                        // resButton.setOnMouseClicked(event3->
                        // {
                        // respawnChar();
                        // });

                        // gameOverTimeline.setOnFinished(event2->Game.getMainMenu().getPane().toFront());
                    });

                    try {
                        setCherriesSaveWrite(new FileOutputStream(
                                "src\\Files\\cherries.txt"));
                        getCherriesSaveWrite().write(cherryCount);

                        setHighScoreSaveRead(new FileInputStream(
                                "src\\Files\\highscore.txt"));
                        highscore = Math.max(getScore(), getHighScoreSaveRead().read());

                        setHighScoreSaveWrite(new FileOutputStream(
                                "src\\Files\\highscore.txt"));
                        getHighScoreSaveWrite().write(highscore);

                        // System.out.println(highscore);

                        getCherriesSaveWrite().close();
                        getHighScoreSaveWrite().close();

                        gameOverMenu = new GameOverMenu();

                        gameOverMenu.getRespawnButton().setOnMouseClicked(event1 -> {
                            // gameOverLabel.setVisible(false);
                            Media clickMedia=new Media(new File("src/Files/click_Audio.mp3").toURI().toString());
                            MediaPlayer clickMediaPlayer=new MediaPlayer(clickMedia);
                            clickMediaPlayer.play();
                            if (cherryCount >= minCherries) {
                                cherryCount -= minCherries;
                                cherryCount = Math.max(0, cherryCount);
                                getCherryCountLabel().setText(String.valueOf(cherryCount));
                                respawnChar();
                            } else {
                                gameOverMenu.getNotEnoughCherriesLabel().setVisible(true);
                            }
                        });
                        gameOverMenu.getMainMenuButton().setOnMouseClicked(event1 -> {
                            Media clickMedia=new Media(new File("src/Files/click_Audio.mp3").toURI().toString());
                            MediaPlayer clickMediaPlayer=new MediaPlayer(clickMedia);
                            clickMediaPlayer.play();
                            gameOverMenu.getPane().setVisible(false);
                            getMainMenu().getPane().toFront();
                        });

                        gameOverMenu.getHighScoreLabel().setText(String.valueOf(highscore));
                        gameOverMenu.getHighScoreLabel().setVisible(true);

                    } catch (Exception e) {
                    }
                }
            });
        });

    }

    public double calcDistance()
    {
        return getPlatform2().getPosX() - getPlatform1().getPosX();
    }

    public void pauseGame() {

        // Singleton design pattern
        // Only one instance of pause menu can exist.
        pauseMenu = PauseMenu.getInstance();

        pauseMenu.getPane().setVisible(true);
        pauseMenu.getPane1().setVisible(true);

        pauseMenu.getPane1().toFront();
        pauseMenu.getPane().toFront();

        pauseMenu.getresumeButton().setOnMouseClicked(event -> {
            Media clickMedia=new Media(new File("src/Files/click_Audio.mp3").toURI().toString());
            MediaPlayer clickMediaPlayer=new MediaPlayer(clickMedia);
            clickMediaPlayer.play();
            pauseMenu.getPane().setVisible(false);
            pauseMenu.getPane1().setVisible(false);
        });

        pauseMenu.getMainMenuButton().setOnMouseClicked(event -> {
            Media clickMedia=new Media(new File("src/Files/click_Audio.mp3").toURI().toString());
            MediaPlayer clickMediaPlayer=new MediaPlayer(clickMedia);
            clickMediaPlayer.play();
            pauseMenu.getPane().setVisible(false);
            pauseMenu.getPane1().setVisible(false);
            getMainMenu().getPane().toFront();

        });

        pauseMenu.getsaveButton().setOnMouseClicked(event -> {
            Media clickMedia=new Media(new File("src/Files/click_Audio.mp3").toURI().toString());
            MediaPlayer clickMediaPlayer=new MediaPlayer(clickMedia);
            clickMediaPlayer.play();
            saveGame();
        });
    }

    public void saveGame() {
        try {
            FileOutputStream objSave = new FileOutputStream("src\\Files\\levelSave.txt");
            FileOutputStream xPosSave = new FileOutputStream("src\\Files\\platform2X.txt");
            FileOutputStream scoreSave = new FileOutputStream("src\\Files\\scoreSave.txt");
            xPosSave.write((int) getPlatform2().getRectangle().getX());
            scoreSave.write(getScore());
            ObjectOutputStream objStream = new ObjectOutputStream(objSave);
            objStream.writeObject(this);

            pauseMenu.getPane().setVisible(false);
            pauseMenu.getPane1().setVisible(false);

            xPosSave.close();
            scoreSave.close();
            objSave.close();
            objStream.close();

        } catch (Exception e) {}
    }

    public void respawnChar() {

        if (newGame == 1) {
            setScore(0);
        }

        if (gameOverMenu != null) {
            gameOverMenu.getPane().setVisible(false);
            gameOverMenu.getPane1().setVisible(false);
        }

        if(pauseMenu != null)
        {
            pauseMenu.getPane().setVisible(false);
            pauseMenu.getPane1().setVisible(false);
        }

        getPlatform1().getRectangle().setVisible(false);
        getPlatform2().getRectangle().setVisible(false);

        setPlatform1(new Platform(100, 50));
        setPlatform2(new Platform(100, 290));

        getIv1().setVisible(true);
        // getIv1().setY(-757);
        for (Stick stick : sticks) {
            stick.getLine().setVisible(false);
        }

        stick = null;
        collectedCherry = false;
        isFlipped = false;
        isGameOver = false;
        fallFlag = 0;
        // Flyweight design pattern
        // Player has a unique character associated with it
        Character character = new Character();
        Player player = Player.getInstance(character);
        getUnivButton().toFront();
        Game.getMainMenu().getPane().toBack();
        newGame = 0;
        getPauseButton().toFront();
    }

    public void loadGame() {
        try {

            if (gameOverMenu != null) {
                gameOverMenu.getPane().setVisible(false);
                gameOverMenu.getPane1().setVisible(false);
            }

            if(pauseMenu != null)
            {
                pauseMenu.getPane().setVisible(false);
                pauseMenu.getPane1().setVisible(false);
            }

            FileInputStream platformXread = new FileInputStream("src\\Files\\platform2X.txt");
            FileInputStream scoreRead = new FileInputStream("src\\Files\\scoreSave.txt");
            FileInputStream objFileRead = new FileInputStream("src\\Files\\levelSave.txt");
            ObjectInputStream objRead = new ObjectInputStream(objFileRead);
//            GameController gameController1 = (GameController) objRead.readObject();

            getPlatform1().getRectangle().setVisible(false);
            getPlatform2().getRectangle().setVisible(false);

            setPlatform1(new Platform(100, 50));
            setPlatform2(new Platform(100, 290));

            getIv1().setVisible(true);
            // getIv1().setY(-757);
            for (Stick stick : sticks) {
                stick.getLine().setVisible(false);
            }

            stick = null;
            collectedCherry = false;
            isFlipped = false;
            isGameOver = false;
            fallFlag = 0;
            getUnivButton().toFront();
            Game.getMainMenu().getPane().toBack();
            newGame = 0;
            getPauseButton().toFront();

            setScore(Math.max(0,scoreRead.read()));
            getScoreLabel().setText(String.valueOf(getScore()));

            platformXread.close();
            scoreRead.close();
            objFileRead.close();
            objRead.close();

        } catch (Exception e) {}
    }

}
