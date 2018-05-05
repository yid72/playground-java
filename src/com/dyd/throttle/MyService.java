package com.dyd.throttle;

public class MyService {
    public void myApi(String message) {
        try {
            ThrottleService.getInstance().throttle(MyService.class, "myApi");
        } catch (final ThrottleException e) {
            System.out.println("I am throttled. " + e.toString());
            return;
        }

        System.out.println("Hello World. " + message);
    }

    public static void main(String[] args) {
        final MyService myService = new MyService();
        for (int i = 1; i <= 5; i++) {
            myService.myApi(String.valueOf(i));
        }
    }
}
