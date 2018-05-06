package com.dyd.throttle;

import java.util.HashMap;
import java.util.Map;
import java.util.Timer;

public class ThrottleServiceFactory {
    public static final String FIXED_WINDOW_THROTTLE_SERVICE = "FixedWindowThrottleService";
    public static final String SLIDING_WINDOW_THROTTLE_SERVICE = "SlidingWindowThrottleService";

    private static final int THRESHOLD = 5;

    private static final ThrottleServiceFactory singleton = new ThrottleServiceFactory();

    public static ThrottleServiceFactory getInstance() {
        return singleton;
    }

    private Map<String, ThrottleService> services;
    private Timer timer;

    private ThrottleServiceFactory() {
        this.timer = new Timer();

        services = new HashMap<>();
        services.put(FIXED_WINDOW_THROTTLE_SERVICE, new FixedWindowThrottleService(timer, THRESHOLD));
    }

    public ThrottleService getThrottleService(final String name) {
        if (services.containsKey(name)) {
            return services.get(name);
        }
        throw new IllegalArgumentException("Unknown service " + name);
    }
}
