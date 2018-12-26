package com.bwei.week01;

import android.app.Application;

import com.facebook.drawee.backends.pipeline.Fresco;

public class MyAppliction extends Application {
    private static MyAppliction app;
    @Override
    public void onCreate() {
        super.onCreate();
        app = this;
        Fresco.initialize(this);
    }

    public static MyAppliction get() {
        return app;
    }
}
