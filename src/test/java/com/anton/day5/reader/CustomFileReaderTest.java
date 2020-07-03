package com.anton.day5.reader;

import com.anton.day5.exception.ProgramException;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.fail;

public class CustomFileReaderTest {
    CustomFileReader reader;

    @BeforeClass
    public void setup() {
        reader = new CustomFileReader();
    }

    @DataProvider(name = "validFiles")
    public Object[][] createValidFiles() {
        return new Object[][]{
                {"data/resultingData/resultingData1.txt", "nike nike... nike. nik. ni..."},
                {"data/startingData/startingData1.txt", "nice nice... nice. nic. ni..."}
        };
    }

    @Test(dataProvider = "validFiles")
    public void readDataValidTest(String path, String actualText) {
        try {
            String expectedText = reader.readData(path);
            assertEquals(expectedText, actualText);
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
        reader.readData(path);
    }
}
