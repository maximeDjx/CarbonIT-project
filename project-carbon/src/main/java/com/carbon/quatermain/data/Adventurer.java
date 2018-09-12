/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.carbon.quatermain.data;

import java.awt.Point;
import java.util.Queue;

/**
 *
 * @author jum_M
 */
public class Adventurer {

    public String name;
    public int orderCreation;
    public Point position;
    public Orientation orientation;
    public Queue<String> movement;
    public int treasures = 0;

    public Adventurer(int orderCreation, String name, Point pos, Orientation orient, Queue<String> movement) {
        this.orderCreation = orderCreation;
        this.name = name;
        this.position = pos;
        this.orientation = orient;
        this.movement = movement;
    }
    
     public Point newPosition() {
        Point asking = new Point(position.x, position.y);
        switch (movement.poll()) {
            case "G":
                orientation = orientation.left;
                break;
            case "D":
                orientation = orientation.right;
                break;
            case "A":
                asking.x = position.x + orientation.x;
                asking.y = position.y + orientation.y;
                break;
        }
        return asking;
    }

    public void move(Point newPosition) {
        position.x = newPosition.x;
        position.y = newPosition.y;
    }

    public boolean canMove() {
        return !movement.isEmpty();
    }

    @Override
    public String toString() {
        return  "A-"
                + name + "-"
                + position.x + "-"
                + position.y + "-"
                + orientation.toString() + "-"
                + treasures;
    }
}
