package com.anton.day5.creator;

import com.anton.day5.exception.ProgramException;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class TextCreatorTest {
    private TextCreator creator;

    @BeforeClass
    public void setup() {
        creator = new TextCreator();
    }

    @Test
    public void createTextFromFileValidTest() {
        try {
            String path = "data/resultingData/resultingData1.txt";
            String actualText = "nike nike... nike. nik. ni...";
            String expectedText = creator.createTextFromFile(path);
            assertEquals(actualText, expectedText);
        } catch (ProgramException ex) {
            fail();
        }
    }

    @DataProvider(name = "invalidFiles")
    public Object[][] createInvalidFiles() {
        return new Object[][]{
                {"data/resultingData/resultingData1yqwertuiweytr.txt"},
                {null}
        };
    }

    @Test(dataProvider = "invalidFiles", expectedExceptions = ProgramException.class)
    public void readDataExceptionTest(String path) throws ProgramException {
        creator.createTextFromFile(path);
    }
}
