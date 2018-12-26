package com.bwei.week01;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class MainActivity2 extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        try {
            //加载UserBean类
            Class name = Class.forName("com.bwei.week01.UserBean");

            Method[] declaredMethods = name.getDeclaredMethods();

            for (Method method:declaredMethods){
                System.out.println("11111"+method);
            }

            System.out.println("********");

            Field[] declaredFields = name.getDeclaredFields();
            for(Field me:declaredFields){
                System.out.println("22222"+me);
            }

            //得到构造方法
            Constructor con = name.getDeclaredConstructor(new Class[]{});
            //实例化
            Object o = con.newInstance(new Object[]{});

            //获取UserBean类中str属性对应的Field对象
            Field str = name.getDeclaredField("str");

           // str.setAccessible(true);

            Object str1 = str.get(o);

            System.out.println("修改之前name的值："+(String)str1);

            str.set(o, "李四");

            //获取getName方法对应的Method对象
            Method getNameMethod = name.getDeclaredMethod("getStr", new Class[]{});

            //设置避开java访问控制检测
           // getNameMethod.setAccessible(true);

            //调用方法，返回值
            Object  o1 = getNameMethod.invoke(o, new Object[]{});

            System.out.println("修改之后name的值："+(String)o1);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }


}
