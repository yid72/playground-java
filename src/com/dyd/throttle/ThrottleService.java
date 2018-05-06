package com.dyd.throttle;

public interface ThrottleService {
    void check() throws ThrottleException;
}
