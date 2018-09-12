package com.carbon.quatermain.data;


import org.junit.Before;
import org.junit.Test;

import java.awt.*;
import java.util.ArrayList;
import java.util.LinkedList;
import static org.junit.Assert.assertEquals;

public class AdventurerTest {

    private Adventurer adventurer = new Adventurer(1,"Jones", new Point(1, 1), Orientation.N, new LinkedList<>());

    @Before
    public void setUp() {
        adventurer.position = new Point(1, 1);
    }

    @Test
    public void askNewPositionNordToMove() {
        adventurer.movement.add("A");
        Point nextPosition = adventurer.newPosition();
        assertEquals(0, nextPosition.y);
        assertEquals(1, nextPosition.x);
    }

    @Test
    public void askNewPositionEastToMove() {
        adventurer.movement.add("A");
        adventurer.orientation = Orientation.E;

        Point nextPosition = adventurer.newPosition();
        assertEquals(1, nextPosition.y);
        assertEquals(2, nextPosition.x);
    }

    @Test
    public void askNewPositionOuestToMove() {
        adventurer.movement.add("A");
        adventurer.orientation = Orientation.O;

        Point nextPosition = adventurer.newPosition();
        assertEquals(1, nextPosition.y);
        assertEquals(0, nextPosition.x);
    }

    @Test
    public void askNewPositionOuestToTurnLeft() {
        adventurer.movement.add("G");
        adventurer.orientation = Orientation.O;

        Point nextPosition = adventurer.newPosition();
        assertEquals(1, nextPosition.y);
        assertEquals(1, nextPosition.x);
        assertEquals(Orientation.S, adventurer.orientation);
    }
    
        @Test
    public void askNewPositionOuestToTurnRight() {
        adventurer.movement.add("D");
        adventurer.orientation = Orientation.O;

        Point nextPosition = adventurer.newPosition();
        assertEquals(1, nextPosition.y);
        assertEquals(1, nextPosition.x);
        assertEquals(Orientation.N, adventurer.orientation);
    }
}
