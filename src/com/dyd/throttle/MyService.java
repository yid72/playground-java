package com.dyd.throttle;

public class MyService {
    public void myApi(String message) {
        try {
            ThrottleService throttleService = ThrottleServiceFactory.getInstance().getThrottleService(
                    ThrottleServiceFactory.TOKEN_BUCKET_THROTTLE_SERVICE);
            throttleService.check();
        } catch (final ThrottleException e) {
            System.out.println(message + ". " + e.getMessage());
            return;
        }

        System.out.println(message + ". Success.");
    }
}
