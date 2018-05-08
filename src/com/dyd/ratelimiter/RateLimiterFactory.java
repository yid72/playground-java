package com.dyd.ratelimiter;

import java.util.HashMap;
import java.util.Map;
import java.util.Timer;

public class RateLimiterFactory {
    public static final String FIXED_WINDOW_RATE_LIMITER = "FixedWindowRateLimiter";
    public static final String TOKEN_BUCKET_RATE_LIMITER = "TokenBucketRateLimiter";

    private static final int THRESHOLD = 10;

    private static final RateLimiterFactory singleton = new RateLimiterFactory();

    public static RateLimiterFactory getInstance() {
        return singleton;
    }

    private Map<String, RateLimiter> services;
    private Timer timer;

    private RateLimiterFactory() {
        this.timer = new Timer();

        services = new HashMap<>();
        services.put(FIXED_WINDOW_RATE_LIMITER, new FixedWindowRateLimiter(timer, THRESHOLD));
        services.put(TOKEN_BUCKET_RATE_LIMITER, new TokenBucketRateLimiter(timer, THRESHOLD));
    }

    public RateLimiter getRateLimiter(final String name) {
        if (services.containsKey(name)) {
            return services.get(name);
        }
        throw new IllegalArgumentException("Unknown service " + name);
    }
}
