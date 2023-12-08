package com.example.mid_submission_2022363_2022310;

import javafx.animation.*;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Point3D;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Line;
import javafx.scene.transform.Rotate;
import javafx.util.Duration;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class GameController extends Game implements Serializable  {
    @FXML
    private Label welcomeText;

    private ImageView iv2;

    private Stick stick;
    private int fallFlag = 0;

    private boolean isFlipped = false;

    private boolean isGameOver = false;

    private double platformMoveTime = 2000;

    private Cherry cherry;

    private ArrayList<Cherry> cherries = new ArrayList<>();

    private int cherryCount = 0;

    public void setCherryCount(int cherryCount)
    {
        this.cherryCount = cherryCount;
    }

    public int getCherryCount()
    {
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

    Timeline timeline;

    public EventHandler<? super MouseEvent> spawnStick(Platform platform1)
    {
        timeline = new Timeline(new KeyFrame(Duration.millis(10),event->{
            if(stick == null)
            {
                Stick stick1;
                stick1 = new Stick(platform1.getStickPosx()-2,platform1.getRectangle().getY());
                stick = stick1;
            }
            if(stick.getCanExtend() == 1)
            {
                stick.extendStick();
            }
        }));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();

        return null;
    }

    void rotStick(Platform platform1, Platform platform2,ImageView iv1) throws FileNotFoundException
    {
        timeline.stop();
//        stick.getLine().setStartX(stick.getLine().getStartX());
        stick.setCanExtend(0);

        if(stick.getCanRotate() == 1)
        {
            stick.setCanRotate(0);
            Rotate rotate = new Rotate(90, stick.getLine().getStartX(), stick.getLine().getStartY());
            stick.getLine().getTransforms().add(rotate);

            Timeline timeline1 = new Timeline(
                    new KeyFrame(Duration.ZERO, new KeyValue(rotate.angleProperty(), 0)),
                    new KeyFrame(Duration.seconds(0.4), new KeyValue(rotate.angleProperty(), 90)
                    ));
            timeline1.play();

            timeline1.setOnFinished(actionEvent -> {
                try
                {
                    sticks.add(stick);
                    moveEnvironment(platform1,  platform2, iv1);
                } catch (FileNotFoundException e) {
                    throw new RuntimeException(e);
                }
            });

        }

    }

    void flipPlayer()
    {
        iv2.setRotationAxis(new Point3D(1,0,0));
        iv2.setRotate(iv2.getRotate()+180);

        isFlipped = !isFlipped;

        if(isFlipped)
        {
            iv2.setY(iv2.getY()+60);
        }
        else
        {
            iv2.setY(iv2.getY()-60);
        }

    }

    void moveEnvironment(Platform platform1, Platform platform2,ImageView iv1) throws FileNotFoundException
    {
        isFlipped = false;
        Image runningPlayerImage = new Image(new FileInputStream("CSE201_AP_Project\\Mid_Submission_2022363_2022310\\src\\main\\resources\\com\\example\\mid_submission_2022363_2022310\\Char_01_Gif_02.gif"));
//        Image runningPlayerImage = new Image(new FileInputStream("src/main/resources/com/example/mid_submission_2022363_2022310/Batman_Running.gif"));
        iv2 = new ImageView(runningPlayerImage);
        iv1.setVisible(false);
        iv2.setScaleX(0.25);
        iv2.setScaleY(0.25);
        iv2.setY(iv1.getY());
        iv2.setX(iv1.getX());
        iv2.setRotate(0);

        try {
            Game.getRoot().getChildren().add(iv2);
        }
        catch (Exception e){}

        double distance = getPlatform2().getRectangle().getX() - getPlatform1().getRectangle().getX();

        double min_dist = getPlatform2().getRectangle().getX() - getPlatform1().getRectangle().getX()-getPlatform2().getRectangle().getWidth()/2-getPlatform1().getRectangle().getWidth()/2;
        double max_dist = distance + getPlatform2().getRectangle().getWidth()/2-getPlatform1().getRectangle().getWidth()/2;

//        System.out.println(stick.getLine().getStartY()-stick.getLine().getEndY() > max_dist || stick.getLine().getStartY()-stick.getLine().getEndY() < min_dist);
//        pos = platform2.getRectangle().getX();

//        platform1.getRectangle().setLayoutX(platform1.getRectangle().getLayoutX()-distance);
//        platform2.getRectangle().setLayoutX(platform2.getRectangle().getLayoutX()-distance);


        if(stick.getLine().getStartY()-stick.getLine().getEndY() > max_dist || stick.getLine().getStartY()-stick.getLine().getEndY() < min_dist)
        {
            distance = Math.abs(stick.getLine().getStartY()-stick.getLine().getEndY()+20);
            fallFlag = 1;
        }

        TranslateTransition translateTransitionPlatform2 = new TranslateTransition(Duration.millis(platformMoveTime), platform2.getRectangle());
        TranslateTransition translateTransitionPlatform1 = new TranslateTransition(Duration.millis(platformMoveTime), platform1.getRectangle());
        TranslateTransition translateTransitionStick = new TranslateTransition(Duration.millis(platformMoveTime),stick.getLine());

        translateTransitionStick.setToX(-distance);
        translateTransitionPlatform1.setToX(-distance);
        translateTransitionPlatform2.setToX(-distance);

        TranslateTransition translateTransitionCherry;
        if(cherry != null)
        {
            translateTransitionCherry = new TranslateTransition(Duration.millis(platformMoveTime),cherry.getCherryIv());
            translateTransitionCherry.setToX(-distance);
            translateTransitionCherry.play();
        }

        translateTransitionPlatform1.play();
        translateTransitionPlatform2.play();
        translateTransitionStick.play();

        getFlipButton().setDisable(false);
        getFlipButton().toFront();


        Timeline collisionTimeline = new Timeline(new KeyFrame(Duration.millis(10),actionEvent ->
        {
//            System.out.println(platform2.getRectangle().getX());
            if (iv1.getBoundsInParent().intersects(platform2.getRectangle().getBoundsInParent()) && isFlipped) {
                // System.out.println("Game Over");
                translateTransitionStick.stop();
                translateTransitionPlatform1.stop();
                translateTransitionPlatform2.stop();
                isGameOver = true;
                return;
            }

            if(cherry != null && iv2.getBoundsInParent().intersects(cherry.getCherryIv().getBoundsInParent()))
            {
                // System.out.println("Cherry");
                if(fallFlag == 0)
                    cherryCount += 1;
                cherry.getCherryIv().setVisible(false);
                cherry = null;
                getCherryCountLabel().setText(String.valueOf(cherryCount));
            }

        }));

        collisionTimeline.setCycleCount((int)platformMoveTime/15);
        collisionTimeline.play();

        collisionTimeline.setOnFinished(actionEvent ->
        {
            if(isGameOver)  // Flipped collision
            {
                iv2.setVisible(false);
                ImageView iv3;
                Image deadImage = null;
                try {
                    deadImage = new Image(new FileInputStream("CSE201_AP_Project\\Mid_Submission_2022363_2022310\\src\\main\\resources\\com\\example\\mid_submission_2022363_2022310\\Char_01_Gif_01.gif"));
                } catch (FileNotFoundException e) {
                    throw new RuntimeException(e);
                }
                iv3 = new ImageView(deadImage);

                iv3.setScaleX(0.25);
                iv3.setScaleY(0.25);
                iv3.setY(202);
                iv3.setX(iv3.getX()-8);
                iv3.setRotationAxis(new Point3D(1,0,0));
                iv3.setRotate(180);
                Game.getRoot().getChildren().add(iv3);

                TranslateTransition playerFall = new TranslateTransition(Duration.millis(500),iv3);
                playerFall.setToY(900);
                playerFall.play();

            }
        });

        translateTransitionPlatform2.setOnFinished(actionEvent ->
        {
            // System.out.println("Moved");
            collisionTimeline.stop();
            getFlipButton().setDisable(true);
            
            for(Cherry cherry:cherries)
            {
                cherry.getCherryIv().setVisible(false);
            }

            iv2.setVisible(false);
            iv1.setVisible(true);
            double w = getPlatform2().getRectangle().getWidth();

//            pos = platform2.getRectangle().getX();

            if(fallFlag == 0)
            {
                getPlatform1().getRectangle().setVisible(false);
                getPlatform2().getRectangle().setVisible(false);
                setPlatform1(new Platform(w,50));
                Random random = new Random();
                Platform newPlatform = new Platform(random.nextInt(90,100),random.nextInt(230,400));
                newPlatform.getRectangle().setY(900);
                setPlatform2(newPlatform);
            }


            TranslateTransition spawnPlatform = new TranslateTransition(Duration.millis(400),getPlatform2().getRectangle());
            spawnPlatform.setToY(getPlatform1().getRectangle().getY()-getPlatform2().getRectangle().getY());
            spawnPlatform.play();

            if(fallFlag == 0)
            {
                for(int i = 0; i < sticks.size()-1; i++)
                {
                    sticks.get(i).getLine().setVisible(false);
                }
            }

            spawnPlatform.setOnFinished(actionEvent1 ->
            {
                getUnivButton().toFront();
                if(fallFlag == 0)
                {
                    Random rand=new Random();
                    double d=rand.nextDouble();
                    double st=this.getPlatform1().getRectangle().getX()+((this.getPlatform1().getRectangle()).getWidth()/2);
                    double en=this.getPlatform2().getRectangle().getX()-((this.getPlatform2().getRectangle().getWidth())/2);
                    double position=st+(d*(en-st));
                    try {
                        cherry=new Cherry(position);
                        cherries.add(cherry);
                    } catch (FileNotFoundException e) {
                        throw new RuntimeException(e);
                    }

                    setScore(getScore() + 1);
                    getScoreLabel().setText(String.valueOf(getScore()));
                    stick = null;
                }
                else
                {
                    TranslateTransition playerFall = new TranslateTransition(Duration.millis(500),iv1);
                    playerFall.setToY(900);
                    playerFall.play();
                }
            });
        });

    }
}
