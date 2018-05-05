package com.dyd.throttle;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ThrottleService {
    private static final int THREASHOLD = 5;

    private static final ThrottleService singleton = new ThrottleService();

    public static ThrottleService getInstance() {
        return singleton;
    }

    private Map<String, Integer> apiCounters = new HashMap<>();
    private Map<String, Lock> apiLockers = new HashMap<>();

    private ThrottleService() {
    }

    public void throttle(final Class client, final String api) throws ThrottleException {
        final String key = client.getName() + "#" + api;

        if (!validateKey(key)) {
            return;
        }

        final Lock lock = apiLockers.get(key);
        try {
            lock.lock();

            if (apiCounters.get(key) >= THREASHOLD) {
                throw new ThrottleException(
                        String.format(
                                "The call to API %s reaches the max number %d", key, THREASHOLD
                        )
                );
            }

            apiCounters.put(key, (apiCounters.get(key)+1));
        } finally {
            lock.unlock();
        }
    }

    private synchronized boolean validateKey(final String key) {
        if (!apiCounters.containsKey(key)) {
            apiCounters.put(key, 1);
            apiLockers.put(key, new ReentrantLock());
            return false;
        }
        return true;
    }
}
