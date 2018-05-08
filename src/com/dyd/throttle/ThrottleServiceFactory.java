package com.dyd.throttle;

import java.util.HashMap;
import java.util.Map;
import java.util.Timer;

public class ThrottleServiceFactory {
    public static final String FIXED_WINDOW_THROTTLE_SERVICE = "FixedWindowThrottleService";
    public static final String TOKEN_BUCKET_THROTTLE_SERVICE = "TokenBucketThrottleService";

    private static final int THRESHOLD = 10;

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
        services.put(TOKEN_BUCKET_THROTTLE_SERVICE, new TokenBucketThrottleService(timer, THRESHOLD));
    }

    public ThrottleService getThrottleService(final String name) {
        if (services.containsKey(name)) {
            return services.get(name);
        }
        throw new IllegalArgumentException("Unknown service " + name);
    }
}
