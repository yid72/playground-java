package com.dyd.ratelimiter;

import java.time.Instant;

public class FixedWindowRateLimiter implements RateLimiter {
    private final int threshold;
    private long second;
    private int count = 0;

    public FixedWindowRateLimiter(final int threshold) {
        this.threshold = threshold;
        this.second = Instant.now().getEpochSecond();
    }

    @Override
    public synchronized void check() throws RateLimiterException {
        long curSecond = Instant.now().getEpochSecond();
        if (second < curSecond) {
            count = 0;
        }

        if (count >= threshold) {
            throw new RateLimiterException(
                    String.format(
                            "Throttled. Limit = %d", threshold
                    )
            );
        }

        count ++;
        second = curSecond;
    }
}
