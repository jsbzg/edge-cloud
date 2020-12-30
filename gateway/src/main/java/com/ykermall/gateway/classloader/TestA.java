package com.ykermall.gateway.classloader;
/**
 * @author : Aaron
 * @date : 2020/12/28
 */
public class TestA {

    public static void main(String[] args) {
        TestA testA = new TestA();
        testA.hello();
    }

    public void hello() {
        System.out.println("TestA: " + this.getClass().getClassLoader());
        TestB testB = new TestB();
        testB.hello();
    }
}

