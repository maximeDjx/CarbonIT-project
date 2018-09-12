package com.carbon.quatermain.data;

import java.awt.Point;
import java.util.List;
import java.util.stream.Stream;
import javafx.util.Pair;

/**
 *
 * @author jum_M
 */
public class Map {

    public Case[][] map;
    private List<Adventurer> listAdventurer;
    private List<Treasure> listTreasure;
    List<Point> mountains;
    private int sizeX;
    private int sizeY;

    private Map() {
    }
    private static Map instance = new Map();

    public static Map getInstance() {
        return instance;
    }

    public void initialisation(Pair<Integer, Integer> sizeMap, List<Point> mountains, List<Treasure> treasures, List<Adventurer> adventurers) {
        this.sizeX = sizeMap.getKey();
        this.sizeY = sizeMap.getValue();
        setListTreasure(treasures);
        setListAdventurer(adventurers);

        this.map = Stream.generate(() -> Stream.generate(Case::new).limit(this.sizeY).toArray(Case[]::new)).limit(this.sizeX).toArray(Case[][]::new);
        this.mountains = mountains;

        mountains.parallelStream().forEach(mountain -> {
            map[mountain.x][mountain.y].changeType(TypeCase.MOUNTAIN);
        });

        treasures.parallelStream().forEach(treasure -> {
            Point caseMap = treasure.getPosition();
            int nbTreasure = treasure.getTreasures();
            if (map[caseMap.x][caseMap.y].type != TypeCase.MOUNTAIN) {
                map[caseMap.x][caseMap.y].nbTreasures += nbTreasure;
            } else {
                System.err.println("Erreur : un trésor ne peut pas être placé sur une montagne");
            }
        });

        adventurers.stream().forEach(adventurer -> {
            if (map[adventurer.position.x][adventurer.position.y].type != TypeCase.MOUNTAIN && map[adventurer.position.x][adventurer.position.y].occuped == false) {
                map[adventurer.position.x][adventurer.position.y].adventurer = adventurer;
                map[adventurer.position.x][adventurer.position.y].occuped = true;
            } else {
                System.err.println("Erreur : l'aventurier " + adventurer.name + " a été initialisé sur une montagne ou sur une case déjà occupé par un autre aventurier");
            }
        });
    }

    public boolean isValidCase(Point position) {
        if (position.x >= 0 && position.x < sizeX && position.y >= 0 && position.y < sizeY) {
            Case ask = map[position.x][position.y];
            return ask.type != TypeCase.MOUNTAIN && !ask.occuped;
        }
            return false;
    }

    public void reserveCase(Adventurer adventurer, Point newPosition) {
        map[adventurer.position.x][adventurer.position.y].occuped = false;
        map[adventurer.position.x][adventurer.position.y].adventurer = null;

        map[newPosition.x][newPosition.y].occuped = true;
        map[newPosition.x][newPosition.y].adventurer = adventurer;
    }

    public void peekTreasure(Adventurer adventurer) {
        if (map[adventurer.position.x][adventurer.position.y].nbTreasures > 0) {
            adventurer.treasures += 1;
            map[adventurer.position.x][adventurer.position.y].nbTreasures--;
        }
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        throw new CloneNotSupportedException();
    }

    @Override
    public String toString() {
        String newLine = System.getProperty("line.separator");
        StringBuilder result = new StringBuilder();
        result.append("C-" + sizeX + "-" + sizeY + newLine);
        mountains.forEach(mountain -> {
            result.append("M-" + mountain.x + "-" + mountain.y + newLine);
        });
        result.append("# {T comme Trésor} - {Axe horizontal} - {Axe vertical} - {Nb. de trésors restants}"+newLine);

        for (int l = 0; l < sizeX; l++) {
            for (int c = 0; c < sizeY; c++) {
                if (map[l][c].nbTreasures > 0) {
                    result.append("T-" + l + "-" + c + "-" + map[l][c].nbTreasures + newLine);
                }
            }
        }

        result.append("# {A comme Aventurier} - {Nom de l’aventurier} - {Axe horizontal} - {Axe vertical} - {Orientation} - {Nb. trésors ramassés}"+newLine);
        getListAdventurer().forEach(adventurer -> {
            result.append(adventurer.toString());
        });

        return result.toString();
    }

    /**
     * @return the listAdventurer
     */
    public List<Adventurer> getListAdventurer() {
        return listAdventurer;
    }

    /**
     * @param listAdventurer the listAdventurer to set
     */
    public void setListAdventurer(List<Adventurer> listAdventurer) {
        this.listAdventurer = listAdventurer;
    }

    /**
     * @return the listTreasure
     */
    public List<Treasure> getListTreasure() {
        return listTreasure;
    }

    /**
     * @param listTreasure the listTreasure to set
     */
    public void setListTreasure(List<Treasure> listTreasure) {
        this.listTreasure = listTreasure;
    }
}
