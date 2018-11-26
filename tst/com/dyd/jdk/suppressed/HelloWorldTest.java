package com.dyd.jdk.suppressed;

import org.junit.jupiter.api.Test;

public class HelloWorldTest {
    @Test
    public void test() throws Exception {
        HelloWorld helloWorld = new HelloWorld();
        try {
            helloWorld.hello();
        } catch (Exception e) {
            e.printStackTrace();

            System.out.println("Suppressed: ");
            for (Throwable t : e.getSuppressed()) {
                t.printStackTrace();
            }
        }
    }
}

