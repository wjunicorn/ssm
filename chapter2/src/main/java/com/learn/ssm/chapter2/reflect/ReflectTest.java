package com.learn.ssm.chapter2.reflect;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ReflectTest {
    public static void main(String[] args) {
        ReflectTest test = new ReflectTest();
        //test.getInstance();
        //test.getInstance2();
        //test.reflectMethod();
        test.reflect();
    }

    public ReflectServiceImpl getInstance() {
        ReflectServiceImpl object = null;
        try {
            object = (ReflectServiceImpl)Class.forName("com.learn.ssm.chapter2.reflect.ReflectServiceImpl").
                    newInstance();
        } catch (ClassNotFoundException | InstantiationException
                | IllegalAccessException ex) {
            ex.printStackTrace();
        }
        return object;
    }

    public ReflectServiceImpl2 getInstance2() {
        ReflectServiceImpl2 object = null;
        try {
            object = (ReflectServiceImpl2)Class.forName("com.learn.ssm.chapter2.reflect.ReflectServiceImpl2").
                    getConstructor(String.class).newInstance("张三");
        } catch (ClassNotFoundException | InstantiationException
                | IllegalAccessException | NoSuchMethodException
                | SecurityException | IllegalArgumentException
                | InvocationTargetException ex) {
            ex.printStackTrace();
        }
        return object;
    }

    public Object reflectMethod() {
        Object returnObj = null;
        ReflectServiceImpl target = new ReflectServiceImpl();
        try {
            Method method = ReflectServiceImpl.class.getMethod("sayHello", String.class);
            returnObj = method.invoke(target, "张三");
        } catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException
                | InvocationTargetException ex) {
            ex.printStackTrace();
        }
        return returnObj;
    }

    public Object reflect() {
        ReflectServiceImpl object = null;
        try {
            object = (ReflectServiceImpl) Class.forName("com.learn.ssm.chapter2.reflect.ReflectServiceImpl")
                    .newInstance();
            Method method = object.getClass().getMethod("sayHello", String.class);
            method.invoke(object, "张三");
        } catch (NoSuchMethodException | SecurityException | ClassNotFoundException | IllegalAccessException
                | IllegalArgumentException | InvocationTargetException | InstantiationException ex) {
            ex.printStackTrace();
        }
        return object;
    }
}
