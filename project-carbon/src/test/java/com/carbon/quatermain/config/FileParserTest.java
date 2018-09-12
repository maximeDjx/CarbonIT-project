package com.carbon.quatermain.config;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.Assert.assertEquals;

public class FileParserTest {

    Path testFile;
    FileParser fileParser;
    private String testPath = "test.txt";

    @Before
    public void setUp() throws IOException {
        String config = "C-3-4\n"
                + "# comment\n"
                + "M-1-1\n"
                + "M-2-2\n"
                + "T-0-3-2\n"
                + "T-1-3-1\n"
                + "A-indiana-1-1-S-AADAA";
       Files.write(Paths.get(testPath), config.getBytes());
        
        fileParser = new FileParser(testPath);
    }

    @Test
    public void parse() {

        assertEquals(2, fileParser.getTreasures().size());
        assertEquals(2, (int)fileParser.getTreasures().get(0).getTreasures());

        assertEquals(2, fileParser.getMountain().size());
        assertEquals(5, fileParser.getAdventurer().get(0).movement.size());
    }

    @After
    public void cleanUp() throws IOException {
        Files.delete(new File(testPath).toPath());
    }
}
