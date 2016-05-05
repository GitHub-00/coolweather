package com.coolweather.app.util;

/**
 * Created by W on 2016/4/20.
 */
public interface HttpCallBackListener {
    void onFinish(String response);
    void onError(Exception e);
}
