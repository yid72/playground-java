package com.dyd.ratelimiter;

import java.util.Timer;
import java.util.TimerTask;

public class FixedWindowRateLimiter implements RateLimiter {
    private static final int PERIOD = 1000;

    private final int threshold;
    private int count = 0;

    public FixedWindowRateLimiter(final Timer timer, final int threshold) {
        timer.schedule(new CleanupTask(), 0, PERIOD);
        this.threshold = threshold;
    }

    @Override
    public synchronized void check() throws RateLimiterException {
        if (count >= threshold) {
            throw new RateLimiterException(
                    String.format(
                            "Throttled. Limit = %d", threshold
                    )
            );
        }

        count ++;
    }

    private synchronized void cleanup() {
        count = 0;
    }

    class CleanupTask extends TimerTask {
        @Override
        public void run() {
            cleanup();
        }
    }
}
