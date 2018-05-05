package com.dyd.throttle;

public class MyService {
    public void myApi(String message) {
        try {
            ThrottleService.getInstance().throttle(
                    MyService.class, "myApi");
        } catch (final ThrottleException e) {
            System.out.println(message + ". I am throttled. " + e.toString());
            return;
        }

        System.out.println(message + ". Success.");
    }
}
