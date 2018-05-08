package com.dyd.ratelimiter;

public class MyService {
    public void myApi(String message) {
        try {
            RateLimiter throttleService = RateLimiterFactory.getInstance().getRateLimiter(
                    RateLimiterFactory.TOKEN_BUCKET_RATE_LIMITER);
            throttleService.check();
        } catch (final RateLimiterException e) {
            System.out.println(message + ". " + e.getMessage());
            return;
        }

        System.out.println(message + ". Success.");
    }
}
