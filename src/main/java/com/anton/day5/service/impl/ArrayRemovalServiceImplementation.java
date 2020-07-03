package com.anton.day5.service.impl;

import com.anton.day5.exception.ProgramException;
import com.anton.day5.service.TextRemovalService;

public class ArrayRemovalServiceImplementation implements TextRemovalService {
    private static final String ERROR_MESSAGE = "Wrong data";
    private static final String VOWEL = "euioaуеэоаыяию";
    private static final String PUNCTUATION_SIGNS = ",.:;!? \n\r";

    @Override
    public String removeNonLetterCharacters(String text) throws ProgramException {
        if (text == null) {
            throw new ProgramException(ERROR_MESSAGE);
        }
        char[] words = text.toCharArray();
        for (int i = 0; i < words.length; i++) {
            if (!isLetterOrSpace(words[i])) {
                words[i] = ' ';
            }
        }
        return new String(words);
    }

    @Override
    public String removeConsonantWordsByLength(int length, String text) throws ProgramException {
        if (text == null || length < 0) {
            throw new ProgramException(ERROR_MESSAGE);
        }
        int positionShift;
        char[] words = text.toCharArray();
        for (int i = 0; i < words.length; i++) {
            int j = i;
            positionShift = 0;
            while (j < words.length && !isWordEnd(words[j])) {
                j++;
            }
            if ((j - i) == length && !isVowel(words[i])) {
                for (int k = i; k < j; k++) {
                    words[k] = ' ';
                }
                positionShift = i - j;
            }
            i = j + positionShift;
        }
        return new String(words);
    }

    private boolean isWordEnd(char ch) {
        return PUNCTUATION_SIGNS.indexOf(ch) != -1;
    }

    private boolean isVowel(char ch) {
        char firstLetter = Character.toLowerCase(ch);
        return VOWEL.indexOf(firstLetter) != -1;
    }

    private boolean isLetterOrSpace(char ch) {
        return (Character.isLetter(ch) || ch == ' ');
    }
}
