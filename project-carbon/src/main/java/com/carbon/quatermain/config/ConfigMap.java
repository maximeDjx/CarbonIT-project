/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.carbon.quatermain.config;

import com.carbon.quatermain.data.Map;

/**
 *
 * @author jum_M
 */
public class ConfigMap {
    Map map;
    
    public ConfigMap(){
        
        map = Map.getInstance();
        
    }
    
    public void initialiseMap(String filePath)
    {
        FileParser parser = new FileParser(filePath);
        map.initialisation(parser.getSizeMap(),parser.getMountain(), parser.getTreasures(), parser.getAdventurer());
    }
}
