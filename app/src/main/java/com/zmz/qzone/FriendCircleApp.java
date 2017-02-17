package com.zmz.qzone;

import android.app.Application;
import android.content.Context;

/**
 * Created by 大灯泡 on 2016/10/26.
 * <p>
 * app
 */

public class FriendCircleApp extends Application {
    private static Context CONTEXT;

    @Override
    public void onCreate() {
        super.onCreate();
        FriendCircleApp.CONTEXT = getApplicationContext();
    }

    public static Context getAppContext() {
        return CONTEXT;
    }

    /*private void initBmob() {
        BmobConfig config = new BmobConfig.Builder(this)
                //设置appkey
                .setApplicationId(Define.BMOB_APPID)
                //请求超时时间（单位为秒）：默认15s
                .setConnectTimeout(15)
                //文件分片上传时每片的大小（单位字节），默认512*1024
                .setUploadBlockSize(1024 * 1024)
                //文件的过期时间(单位为秒)：默认1800s
                .setFileExpiration(1800)
                .build();
        Bmob.initialize(config);
    }*/

}
