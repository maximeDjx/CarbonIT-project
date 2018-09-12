/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.carbon.quatermain;

import com.carbon.quatermain.config.ConfigMap;
import com.carbon.quatermain.data.Map;
import com.carbon.quatermain.treasureHunt.Simulation;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jum_M
 */
public class Main {

    public static void main(String[] args) {
        if (args.length < 2) {
            System.out.println("you have to provide path to initial file...");
            System.exit(3);
        }
        ConfigMap confMap = new ConfigMap();
        confMap.initialiseMap(args[0]);
        Simulation simu = new Simulation();
        String result = simu.run();
        System.out.println(result);
        try {
            Files.write(Paths.get(args[1]), result.getBytes());
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
