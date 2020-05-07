package com.capstone.sheepsheadbackend.util;

import java.util.Random;

public class Util {
    private static Random random = new Random(System.currentTimeMillis());

    /**
     *
     * @return
     */
    public static int getRandomInt() {
        return random.nextInt();
    }

    /**
     *
     * @param bound
     * @return
     */
    public static int getRandomIntBound(int bound) {
        return random.nextInt(bound);
    }
}
