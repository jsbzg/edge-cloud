package com.ykermall.gateway.classloader;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
/**
 * @author : Aaron
 * @date : 2020/12/28
 */
public class MyClassLoaderParentFirst extends ClassLoader {

    private Map<String, String> classPathMap = new HashMap<>();

    public MyClassLoaderParentFirst() {
        classPathMap.put("com.ykermall.gateway.classloader.TestA", "D:\\github\\edge-cloud\\gateway\\target\\classes\\com\\ykermall\\gateway\\classloader\\TestA.class");
        classPathMap.put("com.ykermall.gateway.classloader.TestB", "D:\\github\\edge-cloud\\gateway\\target\\classes\\com\\ykermall\\gateway\\classloader\\TestA.class");
    }

    // 重写了 findClass 方法
    @Override
    public Class<?> findClass(String name) throws ClassNotFoundException {
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

    private byte[] getClassData(File file) {
        try (InputStream ins = new FileInputStream(file);
             ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
            byte[] buffer = new byte[4096];
            int bytesNumRead = 0;
            while ((bytesNumRead = ins.read(buffer)) != -1) {
                baos.write(buffer, 0, bytesNumRead);
            }
            return baos.toByteArray();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new byte[]{};
    }
}
