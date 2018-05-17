package com.dyd.jdk.suppressed;

public class MyBadResource implements AutoCloseable {
    public void execute() {
        throw new RuntimeException("Execution failed");
    }

    @Override
    public void close() throws Exception {
        throw new RuntimeException("Close failed");
    }
}
