/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.carbon.quatermain.data;

import java.awt.Point;

/**
 *
 * @author jum_M
 */
public class Case {
    TypeCase type = TypeCase.PLAIN;
    public int nbTreasures = 0;
    public Adventurer adventurer = null;
    public boolean occuped = false;  
    
    public void changeType(TypeCase type){
        this.type = type; 
    }
}
