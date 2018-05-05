package com.dyd.throttle;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class App {
    public static void main(String[] args) {
        final ExecutorService pool = Executors.newFixedThreadPool(5);

        for (int i = 1; i <= 3; i++) {
            pool.execute(new Worker("worker " + i));
        }
    }
}
