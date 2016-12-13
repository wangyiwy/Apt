package com.wangyi.viewbinder;

import android.app.Activity;

/**
 * Created on 2016/12/13.
 *
 * @author WangYi
 * @since 1.0.0
 */

public class ViewBinder {

    public static void Bind(Activity activity) {
        String className = activity.getClass().getName();
        try {
            Class<?> binderClass = Class.forName(className + "$$Binder");
            Binder<Activity> binder = (Binder<Activity>) binderClass.newInstance();
            binder.bind(activity);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
