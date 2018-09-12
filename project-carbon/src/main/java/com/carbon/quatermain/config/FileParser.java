/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.carbon.quatermain.config;

import com.carbon.quatermain.data.Adventurer;
import com.carbon.quatermain.data.Orientation;
import com.carbon.quatermain.data.Treasure;
import java.awt.Point;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javafx.util.Pair;

/**
 *
 * @author jum_M
 */
public class FileParser {

    List<List<String>> file;
    
    public FileParser(String pathFile) {
        file = parse(pathFile);
        
    }

    public List<List<String>> parse(String pathFile) {
        List<List<String>> collect = null;
        try (Stream<String> stream = Files.lines(Paths.get(pathFile))) {

            collect = stream.filter(line -> !line.startsWith("#"))
                    .map(line -> Arrays.asList(line.split("-")))
                    .collect(Collectors.toList());

        } catch (IOException e) {
            e.printStackTrace();
        }
        return collect;
    }

    public List<Point> getMountain() {
        return file.parallelStream()
                .filter(line -> line.get(0).equalsIgnoreCase("M"))
                .map(line -> {
                    int x = Integer.parseInt(line.get(1));
                    int y = Integer.parseInt(line.get(2));
                    return new Point(x, y);
                })
                .collect(Collectors.toList());
    }

    public List<Adventurer> getAdventurer() {
        int orderCreation = 0;

        List<Adventurer> listAdventurers = new ArrayList<Adventurer>();
        for (List<String> line : file) {
            if (line.get(0).equalsIgnoreCase("A")) {
                String name = line.get(1);
                int x = Integer.parseInt(line.get(2));
                int y = Integer.parseInt(line.get(3));
                Queue<String> movements = new LinkedList<>(Arrays.asList(line.get(5).split("")));
                listAdventurers.add(new Adventurer(orderCreation, name, new Point(x, y), Orientation.valueOf(line.get(4)), movements));
                orderCreation++;
            }
        }
        return listAdventurers;
    }

    public List<Treasure> getTreasures() {
        return file.parallelStream()
                .filter(line -> line.get(0).equalsIgnoreCase("T"))
                .map(line -> {
                    int x = Integer.parseInt(line.get(1));
                    int y = Integer.parseInt(line.get(2));
                    int nbTreasure = Integer.parseInt(line.get(3));
                    return new Treasure(new Point(x,y),nbTreasure);
                })
                .collect(Collectors.toList());
    }

    public Pair<Integer, Integer> getSizeMap() {
        Pair<Integer, Integer> sizeMap = null;
        for (List<String> line : file) {
            if (line.get(0).equalsIgnoreCase("C")) {
                int sizeX = Integer.parseInt(line.get(1));
                int sizeY = Integer.parseInt(line.get(2));
                sizeMap = new Pair<>(sizeX, sizeY);
                break;
            }
        }
        return sizeMap;
    }
}
