/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.carbon.quatermain.data;

/**
 *
 * @author jum_M
 */
public enum Orientation {

    N("N", 0, -1),
    E("E", 1, 0),
    S("S", 0, 1),
    O("O", -1, 0);

    static {
        N.right = E;
        N.left = O;

        E.right = S;
        E.left = N;

        S.right = O;
        S.left = E;

        O.right = N;
        O.left = S;
    }

    String nominal;
    Integer x;
    Integer y;
    Orientation left;
    Orientation right;

    Orientation(String direction, Integer x, Integer y) {
        nominal = direction;
        this.x = x;
        this.y = y;
        this.left = left;
        this.right = right;

    }
}
