package com.dyd.throttle;

import java.time.Instant;
import java.util.*;

public class TokenBucketThrottleService implements ThrottleService {
    private final Queue<Instant> tokens;

    public TokenBucketThrottleService(final Timer timer, final int threshold) {
        timer.schedule(new TokenGenerationTask(), 0, 1000/threshold);

        tokens = new LinkedList<>();
    }

    @Override
    public synchronized void check() throws ThrottleException {
        if (tokens.size() == 0) {
            throw new ThrottleException("Throttled");
        }

        // Assign a token
        tokens.remove();
    }

    private synchronized void generateToken() {
        Instant tokenNow = Instant.now();
        removeExpiredToken(tokenNow);

        tokens.add(tokenNow);
    }

    private void removeExpiredToken(Instant now) {
        // Remove expired (older than 1 second) tokens.
        while (tokens.size() > 0) {
            Instant token = tokens.peek();
            if (now.getEpochSecond() == token.getEpochSecond()) {
                return;
            }
            tokens.remove();
        }
    }

    class TokenGenerationTask extends TimerTask {
        @Override
        public void run() {
            generateToken();
        }
    }
}
