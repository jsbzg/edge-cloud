package com.ykermall.gateway.classloader;

/**
 * @author : Aaron
 * @date : 2020/12/28
 */
public class TestB {

    public void hello() {
        System.out.println("TestB: " + this.getClass().getClassLoader());
    }
}
