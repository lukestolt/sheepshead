package com.capstone.sheepsheadbackend.util;

import java.util.Random;

public class Util {
    private static Random random = new Random(System.currentTimeMillis());

    /**
     * Get a random Integer
     * @return A random Integer
     */
    public static int getRandomInt() {
        return random.nextInt();
    }

    /**
     * Get a random Integer within a bound
     * @param bound Bound on integer
     * @return Random Integer within Bound
     */
    public static int getRandomIntBound(int bound) {
        return random.nextInt(bound);
    }
}
