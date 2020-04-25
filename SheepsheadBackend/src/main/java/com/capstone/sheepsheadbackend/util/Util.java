package com.capstone.sheepsheadbackend.util;

import java.util.Random;

public class Util {
    private static Random random = new Random(System.currentTimeMillis());

    public static int getRandomInt() {
        return random.nextInt();
    }

    public static int getRandomIntBound(int bound) {
        return random.nextInt(bound);
    }
}
