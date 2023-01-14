package com.luxoft.decipherpuzzle.core;


import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class Cipher {

    private final static String DATA = "abcdefghijklmnopqrstuvwxyz";
    Random random = new Random();

    public String encode(String input) {
        String encodedString = input;
        final int maxDigits = 10;
        int[] randomNums = random.ints(maxDigits, 0, DATA.length()).toArray();
        for (int i = 0; i < maxDigits; i++) {
            encodedString = encodedString.replace(Character.forDigit(i, 10), DATA.charAt(randomNums[i]));
        }
        System.out.println(input + " => " + encodedString);
        return encodedString;
    }

}
