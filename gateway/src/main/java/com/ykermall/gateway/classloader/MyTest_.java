package com.ykermall.gateway.classloader;

import java.lang.reflect.Method;
/**
 * @author : Aaron
 * @date : 2020/12/28
 */
public class MyTest_ {

    public static void main(String[] args) throws Exception {
        MyClassLoaderParentFirst myClassLoaderParentFirst = new MyClassLoaderParentFirst();
        Class testAClass = myClassLoaderParentFirst.findClass("com.ykermall.gateway.classloader.TestA");
        Method mainMethod = testAClass.getDeclaredMethod("main", String[].class);
        mainMethod.invoke(null, new Object[]{args});
    }
}
