package com.dyd.ratelimiter;

import java.time.Instant;
import java.time.temporal.ChronoField;
import java.util.ArrayList;
import java.util.List;

public class Buckets {
    private final List<Integer> buckets;

    public Buckets(final int size) {
        buckets = new ArrayList<>();

        for (int i = 0; i < 2*size; i++) {
            buckets.add(0);
        }
    }

    public void count(final Instant timeStamp) {
        final int millisecond = timeStamp.get(ChronoField.MILLI_OF_SECOND);

    }
}
