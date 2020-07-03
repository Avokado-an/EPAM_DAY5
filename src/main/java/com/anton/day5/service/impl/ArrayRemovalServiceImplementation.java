package com.anton.day5.service.impl;

import com.anton.day5.exception.ProgramException;
import com.anton.day5.service.TextRemovalService;

public class ArrayRemovalServiceImplementation implements TextRemovalService {
    private static final String ERROR_MESSAGE = "Wrong data";

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
        boolean flag = false;
        if (ch == ' ' || ch == '.' || ch == ',' || ch == ';' || ch == ':' || ch == '!' ||
                ch == '?' || ch == '\n' || ch == '\r'
        ) {
            flag = true;
        }
        return flag;
    }

    private boolean isVowel(char ch) {
        boolean flag = false;
        if (ch == 'e' || ch == 'y' || ch == 'u' || ch == 'i' || ch == 'o' || ch == 'a' ||
                ch == 'у' || ch == 'е' || ch == 'э' || ch == 'о' || ch == 'а' ||
                ch == 'ы' || ch == 'я' || ch == 'и' || ch == 'ю' || ch == 'E' ||
                ch == 'Y' || ch == 'U' || ch == 'I' || ch == 'O' || ch == 'A' ||
                ch == 'У' || ch == 'Е' || ch == 'Э' || ch == 'О' || ch == 'А' ||
                ch == 'Ы' || ch == 'Я' || ch == 'И' || ch == 'Ю'

        ) {
            flag = true;
        }
        return flag;
    }

    private boolean isLetterOrSpace(char ch) {
        return (Character.isLetter(ch) || ch == ' ');
    }
}
