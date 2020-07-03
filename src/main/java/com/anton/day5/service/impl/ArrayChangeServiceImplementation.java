package com.anton.day5.service.impl;

import com.anton.day5.exception.ProgramException;
import com.anton.day5.service.TextChangeService;

public class ArrayChangeServiceImplementation implements TextChangeService {
    private static final String SPACE = " ";
    private static final String ERROR_MESSAGE = "Wrong data";
    private static final String PUNCTUATION_SIGNS = ",.:;!? \n\r";

    @Override
    public String changeCharacterInWords(int position, char replacement, String text) throws ProgramException {
        if (position < 0 || text == null) {
            throw new ProgramException(ERROR_MESSAGE);
        }
        String[] words = text.split(SPACE);
        StringBuilder res = new StringBuilder();
        char[] wordChars;
        for (String word : words) {
            wordChars = word.toCharArray();
            int size = wordChars.length;
            while (isWordEnd(wordChars[size - 1])) {
                size--;
            }
            if (size > position) {
                wordChars[position] = replacement;
            }
            res.append(wordChars).append(SPACE);
        }
        res.deleteCharAt(res.length() - 1);
        return res.toString();
    }

    @Override
    public String changeSubstringToEqualOne(String replaceableSubstring, String replacement, String text) throws ProgramException {
        if (replaceableSubstring == null || replacement == null || text == null) {
            throw new ProgramException(ERROR_MESSAGE);
        }
        char[] words = text.toCharArray();
        char[] replaceableArr = replaceableSubstring.toCharArray();
        char[] replacementArr = replacement.toCharArray();
        for (int i = 0; i < text.length(); i++) {
            boolean isChosenSubstring = true;
            for (int j = 0; j < replaceableSubstring.length(); j++) {
                if (words[i + j] != replaceableArr[j]) {
                    isChosenSubstring = false;
                    break;
                }
            }
            if (isChosenSubstring) {
                int currentCharPosition = 0;
                for (int k = i; k < i + replaceableArr.length; k++) {
                    words[k] = replacementArr[currentCharPosition];
                    currentCharPosition++;
                }
            }
        }
        return new String(words);
    }

    @Override
    public String changeWordsOfSpecificLength(String substring, int length, String text) throws ProgramException {
        if (substring == null || text == null || length < 0) {
            throw new ProgramException(ERROR_MESSAGE);
        }
        char[] words = text.toCharArray();
        for (int i = 0; i < words.length; i++) {
            int j = i;
            while (j < words.length && !isWordEnd(words[j])) {
                j++;
            }
            if ((j - i) == length) {
                words = replaceSubstring(words, i, j, substring.toCharArray());
            }
            i = j + substring.length() - length;
        }
        return new String(words);
    }

    private char[] replaceSubstring(char[] text, int start, int end, char[] replacement) {
        char[] res = new char[text.length + replacement.length - end + start];
        int i = 0;
        int j = 0;
        for (; i < start; i++) {
            res[i] = text[i];
        }
        int range = replacement.length;
        for (; i < range; i++) {
            res[i] = replacement[j];
            j++;
        }
        for (int k = end; k < text.length; k++) {
            res[i] = text[k];
            i++;
        }
        return res;
    }

    private boolean isWordEnd(char ch) {
        return PUNCTUATION_SIGNS.indexOf(ch) != -1;
    }
}
