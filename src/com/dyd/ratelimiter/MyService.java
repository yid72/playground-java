package com.dyd.ratelimiter;

public class MyService {
    public void myApi(String message) {
        try {
            RateLimiter rateLimiter = RateLimiterFactory.getInstance().getRateLimiter(
                    RateLimiterFactory.FIXED_WINDOW_RATE_LIMITER);
            rateLimiter.check();
        } catch (final RateLimiterException e) {
            System.out.println(message + ". " + e.getMessage());
            return;
        }

        System.out.println(message + ". Success.");
    }
}
