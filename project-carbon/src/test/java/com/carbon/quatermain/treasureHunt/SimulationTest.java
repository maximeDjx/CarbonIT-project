package com.carbon.quatermain.treasureHunt;

import com.carbon.quatermain.config.ConfigMap;
import com.carbon.quatermain.data.Map;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.Assert.assertEquals;

public class SimulationTest {

    private String testPath = "test.txt";

    private Simulation simu;
    private ConfigMap confMap;

    @Before
    public void setUp() throws IOException {
        String config = "C-3-4\n"
                + "M-2-0\n"
                + "T-1-3-2\n"
                + "T-1-1-1\n"
                + "A-indiana-1-1-S-AADA";
        Files.write(Paths.get(testPath), config.getBytes());
        simu = new Simulation();
        confMap = new ConfigMap();
        confMap.initialiseMap(testPath);

    }

    @Test
    public void run() {
        simu.run();
        Map map = Map.getInstance();
        assertEquals(1, simu.map.map[1][3].nbTreasures);

        assertEquals(0, map.getListAdventurer().get(0).position.x);
        assertEquals(3, map.getListAdventurer().get(0).position.y);

    }

    @After
    public void cleanUp() throws IOException {
        Files.delete(new File(testPath).toPath());
    }
}
