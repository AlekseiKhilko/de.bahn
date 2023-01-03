package de.bahn.ui.utils;

public class Util {
    public static void waitSeconds(int seconds) {
        try {
            Thread.sleep(seconds);
        }catch (InterruptedException e) {}
    }
}
