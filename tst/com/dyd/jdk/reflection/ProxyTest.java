package com.dyd.jdk.reflection;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Proxy;

public class ProxyTest {
    @Test
    public void test() {
        HelloInvocationHandler handler = new HelloInvocationHandler();

        Hello proxy = (Hello) Proxy.newProxyInstance(
                ProxyTest.class.getClassLoader(),
                new Class[] {Hello.class},
                handler
        );

        proxy.sayHello();
    }
}
