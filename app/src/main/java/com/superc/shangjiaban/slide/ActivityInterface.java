package com.superc.shangjiaban.slide;

import android.app.Application;

/**
 * 这个包里面的是进行左侧返回使用的
 * @since 2016/10/28
 */
public interface ActivityInterface {
    /**
     * Set the callback for activity lifecycle
     *
     * @param callbacks callbacks
     */
    void setActivityLifecycleCallbacks(Application.ActivityLifecycleCallbacks callbacks);
}
