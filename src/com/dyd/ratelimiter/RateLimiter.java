package com.dyd.ratelimiter;

public interface RateLimiter {
    void check() throws RateLimiterException;
}
