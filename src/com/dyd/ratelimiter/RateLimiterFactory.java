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
        services.put(FIXED_WINDOW_RATE_LIMITER, new FixedWindowRateLimiter(THRESHOLD));
    }

    public RateLimiter getRateLimiter(final String name) {
        if (!services.containsKey(name)) {
            if (FIXED_WINDOW_RATE_LIMITER.equalsIgnoreCase(name)) {
                services.put(FIXED_WINDOW_RATE_LIMITER, new FixedWindowRateLimiter(THRESHOLD));
            } else if (TOKEN_BUCKET_RATE_LIMITER.equalsIgnoreCase(name)) {
                services.put(TOKEN_BUCKET_RATE_LIMITER, new TokenBucketRateLimiter(timer, THRESHOLD));
            }
        }

        if (services.containsKey(name)) {
            return services.get(name);
        }

        throw new IllegalArgumentException("Unknown service " + name);
    }
}
