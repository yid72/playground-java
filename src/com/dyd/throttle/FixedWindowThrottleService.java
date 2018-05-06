package com.dyd.throttle;

import java.util.Timer;
import java.util.TimerTask;

public class FixedWindowThrottleService implements ThrottleService {
    private static final int PERIOD = 1000;

    private final Timer timer;
    private final int threshold;
    private int count = 0;

    public FixedWindowThrottleService(final Timer timer, final int threshold) {
        this.timer = timer;
        timer.schedule(new CleanupTask(), 0, PERIOD);
        this.threshold = threshold;
    }

    @Override
    public synchronized void check() throws ThrottleException {
        if (count >= threshold) {
            throw new ThrottleException(
                    String.format(
                            "Throttled. Limit = %d", threshold
                    )
            );
        }

        count ++;
    }

    @Override
    public void finalize() {
        timer.cancel();
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
