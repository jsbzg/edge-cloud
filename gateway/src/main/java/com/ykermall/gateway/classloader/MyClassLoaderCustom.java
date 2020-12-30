package com.ykermall.gateway.classloader;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * @author : Aaron
 * @date : 2020/12/28
 */
public class MyClassLoaderCustom extends ClassLoader {

    private ClassLoader jdkClassLoader;

    private Map<String, String> classPathMap = new HashMap<>();

    public MyClassLoaderCustom(ClassLoader jdkClassLoader) {
        this.jdkClassLoader = jdkClassLoader;
        classPathMap.put("com.ykermall.gateway.classloader.TestA", "D:\\github\\edge-cloud\\gateway\\target\\classes\\com\\ykermall\\gateway\\classloader\\TestA.class");
        classPathMap.put("com.ykermall.gateway.classloader.TestB", "D:\\github\\edge-cloud\\gateway\\target\\classes\\com\\ykermall\\gateway\\classloader\\TestB.class");
    }

    @Override
    protected Class<?> loadClass(String name, boolean resolve) throws ClassNotFoundException {
        Class result = null;
        try {
            //这里要使用 JDK 的类加载器加载 java.lang 包里面的类
            result = jdkClassLoader.loadClass(name);
        } catch (Exception e) {
            //忽略
        }
        if (result != null) {
            return result;
        }
        String classPath = classPathMap.get(name);
        File file = new File(classPath);
        if (!file.exists()) {
            throw new ClassNotFoundException();
        }

        byte[] classBytes = getClassData(file);
        if (classBytes == null || classBytes.length == 0) {
            throw new ClassNotFoundException();
        }
        return defineClass(classBytes, 0, classBytes.length);
    }

    private byte[] getClassData(File file) { //省略
        return null;
    }

}
