package com.dyd.throttle;

public class Worker implements Runnable {
    private final String name;

    public Worker(final String name) {
        this.name = name;
    }

    public void run() {
        final MyService myService = new MyService();
        for (int i = 1; i <= 5; i++) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
            }
            myService.myApi(name + ": counter " + String.valueOf(i));
        }
    }
}