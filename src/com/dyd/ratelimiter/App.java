package com.dyd.ratelimiter;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class App {
    public static void main(String[] args) {
        final ExecutorService pool = Executors.newFixedThreadPool(5);

        for (int i = 1; i <= 5; i++) {
            pool.execute(new Worker("worker " + i));
        }

        pool.shutdown();
    }
}
