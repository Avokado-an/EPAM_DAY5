package com.anton.day5.service.impl;

import com.anton.day5.exception.ProgramException;
import com.anton.day5.service.TextChangeService;

public class StringChangeServiceImplementation implements TextChangeService {
    private static final String SPACE = " ";
    private static final String ERROR_MESSAGE = "Wrong data";

    @Override
    public String changeCharacterInWords(int position, char replacement, String text) throws ProgramException {
        if (position < 0 || text == null) {
            throw new ProgramException(ERROR_MESSAGE);
        }
        String[] words = text.split(SPACE);
        StringBuilder replacedText = new StringBuilder();
        for (String word : words) {
            int size = word.length();
            while (containsPunctuationSign(word.substring(0, size))) {
                size--;
            }
            if (size > position) {
                replacedText.append(replaceCharacter(word, position, replacement)).append(SPACE);
            } else {
                replacedText.append(word).append(SPACE);
            }
        }
        replacedText.deleteCharAt(replacedText.length() - 1);
        return replacedText.toString();
    }

    @Override
    public String changeSubstringToEqualOne(String replaceableSubstring, String replacement, String text) throws ProgramException {
        if (replaceableSubstring == null || replacement == null || text == null) {
            throw new ProgramException(ERROR_MESSAGE);
        }
        String[] words = text.split(SPACE);
        StringBuilder replacedText = new StringBuilder();
        for (String word : words) {
            if (word.contains(replaceableSubstring)) {
                replacedText.append(word.replace(replaceableSubstring, replacement)).append(" ");
            } else {
                replacedText.append(word).append(" ");
            }
        }
        replacedText.deleteCharAt(replacedText.length() - 1);
        return replacedText.toString();
    }

    @Override
    public String changeWordsOfSpecificLength(String substring, int length, String text) throws ProgramException {
        if (substring == null || text == null || length < 0) {
            throw new ProgramException(ERROR_MESSAGE);
        }
        String[] words = text.split(SPACE);
        StringBuilder replacedText = new StringBuilder();
        for (String word : words) {
            int size = word.length();
            while (containsPunctuationSign(word.substring(0, size))) {
                size--;
            }
            if (size == length) {
                replacedText.append(substring).append(SPACE);
            } else {
                replacedText.append(word).append(SPACE);
            }
        }
        replacedText.deleteCharAt(replacedText.length() - 1);
        return replacedText.toString();
    }

    private boolean containsPunctuationSign(String str) {
        boolean flag = false;
        if (str.contains(".") ||
                str.contains("?") ||
                str.contains("!") ||
                str.contains(":") ||
                str.contains(";") ||
                str.contains(",")
        ) {
            flag = true;
        }
        return flag;
    }

    private String replaceCharacter(String str, int position, char replacement) {
        StringBuilder replacedString = new StringBuilder(str);
        replacedString.setCharAt(position, replacement);
        return replacedString.toString();
    }
}
