package com.anton.day5.service.impl;

import com.anton.day5.exception.ProgramException;
import com.anton.day5.service.TextRemovalService;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexRemovalServiceImplementation implements TextRemovalService {
    private static final String ERROR_MESSAGE = "Wrong data";
    private static final String VOWELS = "EIOUAeuioaуеэоаыяиюЮИЯЫАОЭЕУ";
    private static final String NON_VOWELS = "[^" + VOWELS + "]";
    private static final String NON_LETTERS = "^а-яА-Яa-zA-Z";
    private static final String SPACE = " ";
    private static final String WORD_BOUND = "\\b";
    private static final String NON_SPACE_CHARACTER = "\\S";
    private static final String LETTERS_AND_SPACE = "([" + NON_LETTERS + ", " + SPACE + "]+)";
    private static final String SPECIAL_LENGTH_CONSONANT = WORD_BOUND + NON_VOWELS + NON_SPACE_CHARACTER + "{%d}" + WORD_BOUND;

    @Override
    public String removeNonLetterCharacters(String text) throws ProgramException {
        if (text == null) {
            throw new ProgramException(ERROR_MESSAGE);
        }
        StringBuilder res = new StringBuilder(text);
        Pattern pattern = Pattern.compile(LETTERS_AND_SPACE);
        Matcher matcher = pattern.matcher(text);
        while (matcher.find()) {
            String spaces = fillSpaces(matcher.end() - matcher.start());
            res.replace(matcher.start(), matcher.end(), spaces);
        }
        return res.toString();
    }

    @Override
    public String removeConsonantWordsByLength(int length, String text) throws ProgramException {
        if (text == null || length < 0) {
            throw new ProgramException(ERROR_MESSAGE);
        }
        String regEx = String.format(SPECIAL_LENGTH_CONSONANT, length - 1);
        StringBuilder res = new StringBuilder(text);
        Pattern pattern = Pattern.compile(regEx);
        Matcher matcher = pattern.matcher(text);
        while (matcher.find()) {
            String spaces = fillSpaces(matcher.end() - matcher.start());
            res.replace(matcher.start(), matcher.end(), spaces);
        }
        return res.toString();
    }

    private String fillSpaces(int amount) {
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < amount; i++) {
            res.append(SPACE);
        }
        return res.toString();
    }
}
