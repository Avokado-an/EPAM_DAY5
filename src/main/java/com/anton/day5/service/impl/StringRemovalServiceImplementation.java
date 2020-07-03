package com.anton.day5.service.impl;

import com.anton.day5.exception.ProgramException;
import com.anton.day5.service.TextRemovalService;

public class StringRemovalServiceImplementation implements TextRemovalService {
    private static final String ERROR_MESSAGE = "Wrong data";
    private static final String SPACE = " ";
    private static final String VOWELS = "euioaуеэоаыяию";
    private static final String LETTERS_AND_SPACE = "([^а-яА-Яa-zA-Z, \\s]+)";

    @Override
    public String removeNonLetterCharacters(String text) throws ProgramException {
        if (text == null) {
            throw new ProgramException(ERROR_MESSAGE);
        }
        StringBuilder lettersOnlyString = new StringBuilder();
        String[] lettersOnlyArray = text.split(LETTERS_AND_SPACE);
        for (String letter : lettersOnlyArray) {
            lettersOnlyString.append(letter);
        }
        return lettersOnlyString.toString();
    }

    @Override
    public String removeConsonantWordsByLength(int length, String text) throws ProgramException {
        if (text == null || length < 0) {
            throw new ProgramException(ERROR_MESSAGE);
        }
        StringBuilder res = new StringBuilder();
        String[] words = text.split(SPACE);
        for (String word : words) {
            if (word.length() != length || startsWithVowel(word)) {
                res.append(word).append(SPACE);
            }
        }
        res.deleteCharAt(res.length() - 1);
        return res.toString();
    }

    private boolean startsWithVowel(String word) {
        char[] letters = word.toCharArray();
        boolean flag = false;
        if (VOWELS.contains(String.valueOf(letters[0]).toLowerCase())) {
            flag = true;
        }
        return flag;
    }
}
