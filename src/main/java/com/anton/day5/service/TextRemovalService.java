package com.anton.day5.service;

import com.anton.day5.exception.ProgramException;

public interface TextRemovalService {
    String removeNonLetterCharacters(String text) throws ProgramException;

    String removeConsonantWordsByLength(int length, String text) throws ProgramException;
}