package com.anton.day5.service;

import com.anton.day5.exception.ProgramException;
import com.anton.day5.service.impl.ArrayChangeServiceImplementation;
import com.anton.day5.service.impl.RegexChangeServiceImplementation;
import com.anton.day5.service.impl.StringChangeServiceImplementation;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.fail;

public class TextChangeServiceTest {
    TextChangeService service;

    @DataProvider(name = "invalidText")
    public Object[][] createInvalidText() {
        return new Object[][]{
                {new ArrayChangeServiceImplementation(), null, 2},
                {new RegexChangeServiceImplementation(), null, 2},
                {new StringChangeServiceImplementation(), null, 2},
                {new ArrayChangeServiceImplementation(), "qwer", -2},
                {new RegexChangeServiceImplementation(), "qwer", -2},
                {new StringChangeServiceImplementation(), "qwer", -2},
                {new ArrayChangeServiceImplementation(), null, -2},
                {new RegexChangeServiceImplementation(), null, -2},
                {new StringChangeServiceImplementation(), null, -2}
        };
    }

    @DataProvider(name = "textChangers")
    public Object[][] createTextChangers() {
        return new Object[][]{
                {new ArrayChangeServiceImplementation()},
                {new RegexChangeServiceImplementation()},
                {new StringChangeServiceImplementation()}
        };
    }

    @Test(dataProvider = "textChangers")
    public void changeCharacterInWordsValidTest(TextChangeService changeService) {
        try {
            String startingText = "nice nice... nice. nic. ni...";
            String actualText = "nike nike... nike. nik. ni...";
            char ch = 'k';
            int position = 2;
            service = changeService;
            String expectedText = service.changeCharacterInWords(position, ch, startingText);
            assertEquals(expectedText, actualText);
        } catch (ProgramException ex) {
            fail();
        }
    }

    @Test(dataProvider = "invalidText", expectedExceptions = ProgramException.class)
    public void changeCharacterInWordsExceptionTest(
            TextChangeService changeService, String text, int position) throws ProgramException {
        service = changeService;
        char ch = 'c';
        service.changeCharacterInWords(position, ch, text);
    }

    @Test(dataProvider = "textChangers")
    public void changeSubstringToEqualOneValidTest(TextChangeService changeService) {
        try {
            String text = "papaer pao paopoq";
            String actualText = "popoer poo poopoq";
            String changeable = "pa";
            String changer = "po";
            service = changeService;
            String expectedText = service.changeSubstringToEqualOne(changeable, changer, text);
            assertEquals(expectedText, actualText);
        } catch (ProgramException ex) {
            fail();
        }
    }

    @Test(dataProvider = "textChangers", expectedExceptions = ProgramException.class)
    public void changeSubstringToEqualOneExceptionTest(TextChangeService changeService) throws ProgramException {
        service = changeService;
        String text = null;
        String replaceable = null;
        String replacement = null;
        service.changeSubstringToEqualOne(text, replaceable, replacement);
    }

    @Test(dataProvider = "textChangers")
    public void changeWordsOfSpecificLengthValidTest(TextChangeService changeService) {
        try {
            String startingText = "nic nice... ni. notNice";
            String actualText = "nisss nice... ni. notNice";
            String replacement = "nisss";
            int length = 3;
            service = changeService;
            String expectedText = service.changeWordsOfSpecificLength(replacement, length, startingText);
            assertEquals(expectedText, actualText);
        } catch (ProgramException ex) {
            fail();
        }
    }

    @Test(dataProvider = "textChangers", expectedExceptions = ProgramException.class)
    public void changeWordsOfSpecificLengthExceptionTest(
            TextChangeService changeService) throws ProgramException {
        String text = null;
        int length = 4;
        String replaceable = null;
        service = changeService;
        service.changeWordsOfSpecificLength(replaceable, length, text);
    }
}
