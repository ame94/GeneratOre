package ca.ame94.generatore.util;

import java.util.Random;

public class Rand {

    private static Random rand = new Random();

    public static void init() {
        rand = new Random(System.currentTimeMillis());
    }

    public static int getInt(int min, int max) {
        return rand.nextInt(max - min + 1) + min;
    }
}
