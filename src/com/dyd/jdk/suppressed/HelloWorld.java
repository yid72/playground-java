package com.dyd.jdk.suppressed;

public class HelloWorld {
    public void hello() throws Exception {
        MyBadResource resource = new MyBadResource();
        try {
            resource.execute();
        } finally {
            resource.close();
        }
    }
}
