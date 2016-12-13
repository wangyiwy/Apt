package com.wangyi.viewbinder;

import android.app.Activity;

/**
 * Created on 2016/12/13.
 *
 * @author WangYi
 * @since 1.0.0
 */

public interface Binder<T extends Activity> {

    void bind(T activity);
}
