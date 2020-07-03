package com.anton.day5.service;

import com.anton.day5.exception.ProgramException;
import com.anton.day5.service.impl.ArrayRemovalServiceImplementation;
import com.anton.day5.service.impl.RegexRemovalServiceImplementation;
import com.anton.day5.service.impl.StringRemovalServiceImplementation;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.fail;

public class TextRemovalServiceTest {
    TextRemovalService service;

    @DataProvider(name = "invalidText")
    public Object[][] createInvalidText() {
        return new Object[][]{
                {new RegexRemovalServiceImplementation(), null},
                {new ArrayRemovalServiceImplementation(), null},
                {new StringRemovalServiceImplementation(), null}
        };
    }

    @DataProvider(name = "validNonLetterRemovalText")
    public Object[][] createValidNonLetterRemovalText() {
        return new Object[][]{
                {new RegexRemovalServiceImplementation(), "q1 e34 4...r", "q  e       r"},
                {new ArrayRemovalServiceImplementation(), "q1 e34 4...r", "q  e       r"},
                {new StringRemovalServiceImplementation(), "q1 e34 4...r", "q e r"}
        };
    }

    @Test(dataProvider = "validNonLetterRemovalText")
    public void removeNonLetterCharactersValidTest(TextRemovalService removalService, String startingText, String actualText) {
        try {
            service = removalService;
            String expectedText = service.removeNonLetterCharacters(startingText);
            assertEquals(expectedText, actualText);
        } catch (ProgramException ex) {
            fail();
        }
    }

    @Test(dataProvider = "invalidText", expectedExceptions = ProgramException.class)
    public void removeNonLetterCharactersExceptionTest(TextRemovalService removalService, String startingText) throws ProgramException {
        service = removalService;
        service.removeNonLetterCharacters(startingText);
    }

    @DataProvider(name = "validConsonantWordsText")
    public Object[][] createValidConsonantWordsText() {
        return new Object[][]{
                {new RegexRemovalServiceImplementation(), "iice Kcin gciin fcin Gciin", "iice      gciin      Gciin", 4},
                {new ArrayRemovalServiceImplementation(), "iice Kcin gciin fcin Gciin", "iice      gciin      Gciin", 4},
                {new StringRemovalServiceImplementation(), "iice Kcin gciin fcin Gciin", "iice gciin Gciin", 4}
        };
    }

    @Test(dataProvider = "validConsonantWordsText")
    public void removeConsonantWordsByLengthValidTest(TextRemovalService removalService, String startingText, String actualText, int length) {
        try {
            service = removalService;
            String expectedText = service.removeConsonantWordsByLength(length, startingText);
            assertEquals(expectedText, actualText);
        } catch (ProgramException ex) {
            fail();
        }
    }

    @Test(dataProvider = "invalidText", expectedExceptions = ProgramException.class)
    public void regexRemoveNonLetterCharactersExceptionTest(TextRemovalService removalService, String startingText) throws ProgramException {
        service = removalService;
        service.removeNonLetterCharacters(startingText);
    }
}
