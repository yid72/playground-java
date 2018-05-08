package com.dyd.ratelimiter;

public class RateLimiterException extends Exception {
    public RateLimiterException(final String message) {
        super(message);
    }
}
