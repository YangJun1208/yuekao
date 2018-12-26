package com.bwei.zhujie;

import android.app.Activity;
import android.view.View;

import java.lang.reflect.Field;

public class ButtenClick {
    public static void  bind(Object object){
        try {
            parse(object);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private static void parse(Object object) throws Exception {
        Class<?> aClass = object.getClass();
        View view = null;
        Field[] fields = aClass.getDeclaredFields();
        for (Field field : fields) {
            if (field.isAnnotationPresent(BindString.class)) {
                BindString annotation = field.getAnnotation(BindString.class);
                int id = annotation.value();
                if (id < 0) {
                    throw new Exception("error");
                } else {
                    field.setAccessible(true);
                    if (object instanceof View) {
                        view = ((View) object).findViewById(id);
                        view.setOnClickListener((View.OnClickListener)object);
                    } else if (object instanceof Activity) {
                        view = ((Activity) object).findViewById(id);
                        view.setOnClickListener((View.OnClickListener)object);
                    }
                    field.set(object, view);
                }
            }
        }
    }
}
