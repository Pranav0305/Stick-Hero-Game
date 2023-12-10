package com.example.final_submission_2022363_2022310;

import javafx.stage.Stage;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

public class GameTest {

    @Test
    public void platformDistanceTest()
    {
        Game g = new Game();
        try {
            g.start(new Stage());
            assertTrue(g.getPlatform2().getPosX() - g.getPlatform1().getPosX() > 0);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @Test
    public void stickLengthTest()
    {
        Stick stick = new Stick(0,0);
        assertTrue(stick.getStickLength() >= 0);
    }


}