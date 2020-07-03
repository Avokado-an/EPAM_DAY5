package com.anton.day5.service.impl;

import com.anton.day5.exception.ProgramException;
import com.anton.day5.service.TextChangeService;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexChangeServiceImplementation implements TextChangeService {
    private static final String ERROR_MESSAGE = "Wrong data";
    private static final String PUNCTUATION_SIGNS = "\\p{Punct}";
    private static final String WORD_BOUND = "\\b";
    private static final String NON_SPACE_CHARACTER = "\\S";
    private static final String NUMBER_OR_LETTER = "\\w";
    private static final String SPECIFIC_LENGTH = "{%d}";
    private static final String LENGTH_MORE_THAN = "{%d,}";
    private static final String NON_PUNCTUATION_SIGNS = "[^" + PUNCTUATION_SIGNS + "]";
    private static final String WORDS_LONGER_THAN =
            WORD_BOUND + NON_SPACE_CHARACTER + LENGTH_MORE_THAN + NON_PUNCTUATION_SIGNS + WORD_BOUND;
    private static final String SPECIFIC_LENGTH_WORDS =
            WORD_BOUND + NUMBER_OR_LETTER + SPECIFIC_LENGTH + WORD_BOUND;

    @Override
    public String changeCharacterInWords(int position, char replacement, String text) throws ProgramException {
        if (position < 0 || text == null) {
            throw new ProgramException(ERROR_MESSAGE);
        }
        String regEx = String.format(WORDS_LONGER_THAN, position);
        StringBuilder res = new StringBuilder(text);
        Pattern pattern = Pattern.compile(regEx);
        Matcher matcher = pattern.matcher(text);
        while (matcher.find()) {
            int index = matcher.start() + position;
            res.replace(index, index + 1, String.valueOf(replacement));
        }
        return res.toString();
    }

    @Override
    public String changeSubstringToEqualOne(String replaceableSubstring, String replacement, String text) throws ProgramException {
        if (replaceableSubstring == null || replacement == null || text == null) {
            throw new ProgramException(ERROR_MESSAGE);
        }
        String regEx = replaceableSubstring;
        StringBuilder res = new StringBuilder(text);
        Pattern pattern = Pattern.compile(regEx);
        Matcher matcher = pattern.matcher(text);
        while (matcher.find()) {
            res.replace(matcher.start(), matcher.end(), replacement);
        }
        return res.toString();
    }

    @Override
    public String changeWordsOfSpecificLength(String substring, int length, String text) throws ProgramException {
        if (substring == null || text == null || length < 0) {
            throw new ProgramException(ERROR_MESSAGE);
        }
        String regEx = String.format(SPECIFIC_LENGTH_WORDS, length);
        StringBuilder res = new StringBuilder(text);
        Pattern pattern = Pattern.compile(regEx);
        Matcher matcher = pattern.matcher(text);
        while (matcher.find()) {
            res.replace(matcher.start(), matcher.end(), substring);
        }
        return res.toString();
    }
}
