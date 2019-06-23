package com.example.dell.come_on.dao;

import android.app.Application;

public class Myapp extends Application {

    private static DaoSession session;

    @Override
    public void onCreate() {
        super.onCreate();
        getadd();
    }

    private void getadd() {
        final DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this, "vv.db");
        final DaoMaster master = new DaoMaster(helper.getWritableDatabase());
        session = master.newSession();
    }

    public static DaoSession getSession() {
        return session;
    }
}
