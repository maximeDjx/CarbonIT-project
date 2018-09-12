/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.carbon.quatermain.data;

import java.awt.Point;
import java.util.Objects;

/**
 *
 * @author jum_M
 */
public class Treasure {

    public Point position;
    public Integer treasures;

    public Treasure(Point position, Integer nbTreasure) {
        this.position = position;
        this.treasures = nbTreasure;
    }

    public Point getPosition() {
        return position;
    }

    public void setPosition(Point position) {
        this.position = position;
    }

    public Integer getTreasures() {
        return treasures;
    }

    public void setTreasures(Integer treasures) {
        this.treasures = treasures;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Treasure that = (Treasure) o;
        return Objects.equals(position, that.position)
                && Objects.equals(treasures, that.treasures);
    }

    @Override
    public int hashCode() {

        return Objects.hash(position, treasures);
    }

}
