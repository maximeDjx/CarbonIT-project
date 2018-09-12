package com.carbon.quatermain.data;

import org.junit.Before;
import org.junit.Test;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import javafx.util.Pair;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class MapTest {

    private List<Adventurer> listAdventurer;
    private List<Treasure> listTreasure;
    private List<Point> mountains;
    Pair<Integer, Integer> sizeMap;
    Map map;

    @Before
    public void init() {
        mountains = new ArrayList<Point>() {
            {
                add(new Point(0, 1));
                add(new Point(2, 2));
            }
        };

        listTreasure = new ArrayList<Treasure>() {
            {
                add(new Treasure(new Point(0, 2), 2));
                add(new Treasure(new Point(1, 1), 5));
            }
        };
        listAdventurer = new ArrayList<Adventurer>() {
            {
                add(new Adventurer(1,"Lara",new Point(2, 3), Orientation.N,null));
            }
        };
        sizeMap = new Pair<>(3, 4);
        
        map = Map.getInstance();
    }

    @Test
    public void initialisationTest() {
        map.initialisation(sizeMap, mountains, listTreasure, listAdventurer);
        assertEquals(true, map.map.length == 3 && map.map[0].length == 4);
        assertTrue(map.map[0][1].type == TypeCase.MOUNTAIN);
        assertTrue(map.map[2][2].type == TypeCase.MOUNTAIN);
        assertTrue(map.isValidCase(new Point(1,1)));
        assertTrue(2 == map.map[0][2].nbTreasures);
        assertTrue(5 == map.map[1][1].nbTreasures);

    }
}
