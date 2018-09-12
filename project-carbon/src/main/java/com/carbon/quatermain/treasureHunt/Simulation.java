/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.carbon.quatermain.treasureHunt;

import com.carbon.quatermain.data.Adventurer;
import com.carbon.quatermain.data.Map;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author jum_M
 */
public class Simulation {

    Map map;

    public Simulation() {
        map = Map.getInstance();
    }

    public String run() {

        List<Adventurer> adventurers = map.getListAdventurer();
        List<Adventurer> adventurerStopped = new ArrayList<>();
        Integer nbAdventurer = adventurers.size();

        while (adventurerStopped.size() < nbAdventurer) {
            adventurers.stream().filter(adventurer -> {
                if (!adventurer.canMove()) {
                    adventurerStopped.add(adventurer);
                }
                return adventurer.canMove();
            }).forEach((adventurer) -> {
                Point destinationAsked = adventurer.newPosition();
                if (map.isValidCase(destinationAsked)) {
                    map.reserveCase(adventurer, destinationAsked);
                    adventurer.move(destinationAsked);
                    map.peekTreasure(adventurer);
                }
            });
        }
        return map.toString();
    }
}
