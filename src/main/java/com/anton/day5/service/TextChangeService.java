package com.anton.day5.service;

import com.anton.day5.exception.ProgramException;

public interface TextChangeService {
    String changeCharacterInWords(int position, char replacement, String text) throws ProgramException;

    String changeSubstringToEqualOne(String replaceableSubstring, String replacement, String text) throws ProgramException;

    String changeWordsOfSpecificLength(String substring, int length, String text) throws ProgramException;
}
