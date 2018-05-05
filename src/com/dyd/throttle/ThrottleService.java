package com.dyd.throttle;

import java.util.HashMap;
import java.util.Map;

public class ThrottleService {
    private static final int THREASHOLD = 3;

    private static final ThrottleService singleton = new ThrottleService();

    public static ThrottleService getInstance() {
        return singleton;
    }

    private Map<String, Integer> apiCounters = new HashMap<>();

    private ThrottleService() {
    }

    public void throttle(final Class client, final String api) throws ThrottleException {
        final String key = client.getName() + "#" + api;

        if (!apiCounters.containsKey(key)) {
            apiCounters.put(key, 1);
            return;
        }

        if (apiCounters.get(key) >= THREASHOLD) {
            throw new ThrottleException(String.format("The call to API %s reaches the max number %d", key, THREASHOLD));
        }

        apiCounters.put(key, (apiCounters.get(key)+1));
    }
}
