package com.superc.shangjiaban.others;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.media.MediaPlayer;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;

import com.superc.shangjiaban.R;
import com.superc.shangjiaban.base.AppAppLication;
import com.superc.shangjiaban.ui.MainActivity;
import com.superc.shangjiaban.utils.ShareUtil;
import com.yanzhenjie.nohttp.rest.RequestQueue;

import cn.jpush.android.api.JPushInterface;

/********************************************************************
 @version: 1.0.0
 @description: 进行订单接口查询判断订单是否增加进行语音提示
 @author: user
 @time: 2018/3/27 16:37
 @变更历史:
 ********************************************************************/
public class MyService extends Service {

    private int old_count, new_count, all_count = 0;
    private String uid = "";
    private RequestQueue mRequestQueue;
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
//            getNewDd();
//            all_count++;
            guanBi();
            mHandler.sendEmptyMessageDelayed(110, 2000);/*2秒进行一次接口查询*/
        }
    };
    MediaPlayer mMediaPlayer;

    public MyService() {
    }

    public Context getMine(){
        return this;
    }

    @Override
    public IBinder onBind(Intent intent) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
//        mRequestQueue = NoHttp.newRequestQueue();
        uid = (String) ShareUtil.getInstance(this).get("uid", "");
        mHandler.obtainMessage().sendToTarget();

        forgroundNote();
        return super.onStartCommand(intent, flags, startId);
    }
    /*关闭前台服务*/
    public void guanBi() {
        uid = (String) ShareUtil.getInstance(this).get("uid", "");
        if(uid.equals("")){
            stopForeground(true);
            return;
        }
        boolean pushStopped = JPushInterface.isPushStopped(AppAppLication.getmContext());
        if(pushStopped){
            JPushInterface.resumePush(AppAppLication.getmContext());
        }
    }

    /*查询已付款订单数量(用来做语音提示)*/
    public void getNewDd() {
//        uid = (String) ShareUtil.getInstance(this).get("uid", "");
//        if(uid.equals("")){
//            stopForeground(true);
//            return;
//        }
//        Map<String, String> map = new HashMap<>();
//        map.put("uid", uid);
//        final Request<JSONObject> request = NoHttp.createJsonObjectRequest(HASNEWDINGDAN, RequestMethod.POST);
//        request.add("uid",uid);
//        mRequestQueue.add(2, request, new OnResponseListener<JSONObject>() {
//            @Override
//            public void onStart(int what) {
//            }
//
//            @Override
//            public void onSucceed(int what, Response<JSONObject> response) {
//                JSONObject jsonObject = response.get();
//                try {
//                    String count = jsonObject.getString("count");
//                    if (count == null || count.equals("")) {
//                        count = "0";
//                    }
//                    if (old_count == 0) {
//                        old_count = Integer.parseInt(count);
//                    } else {
//                        new_count = Integer.parseInt(count);
//                        if (new_count != old_count) {
////                            int num = new_count - old_count;/*新增加的订单条数*/
//                            mMediaPlayer = MediaPlayer.create(MyService.this, R.raw.tongzhi);
//                            mMediaPlayer.start();
//                            old_count = new_count;
//                        }
//                    }
//                    Log.i(" MyService 订单数量", "old_count:" + old_count + "  new_count:" + new_count);
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//
//            }
//
//            @Override
//            public void onFailed(int what, Response<JSONObject> response) {
////                Toast.makeText(MyService.this, "网络异常", Toast.LENGTH_SHORT).show();
//            }
//
//            @Override
//            public void onFinish(int what) {
//            }
//        });
    }

    /*开启前台服务*/
    public void forgroundNote() {
        //         在API11之后构建Notification的方式
        Notification.Builder builder = new Notification.Builder(this.getApplicationContext()); //获取一个Notification构造器
        Intent nfIntent = new Intent(this, MainActivity.class);
        builder.setContentIntent(PendingIntent.getActivity(this, 0, nfIntent, 0)) // 设置PendingIntent
                .setLargeIcon(BitmapFactory.decodeResource(this.getResources(), R.mipmap.ic_launcher)) // 设置下拉列表中的图标(大图标)
                .setContentTitle(this.getResources().getString(R.string.app_name)) // 设置下拉列表里的标题
                .setSmallIcon(R.mipmap.ic_launcher) // 设置状态栏内的小图标
                .setContentText("请保持程序在后台运行") // 设置上下文内容
                .setWhen(System.currentTimeMillis()); // 设置该通知发生的时间
        Notification notification = builder.build(); // 获取构建好的Notification
        startForeground(0x111, notification);// 开始前台服务
    }

    @Override
    public void onDestroy() {
        stopForeground(true);// 停止前台服务--参数：表示是否移除之前的通知
        super.onDestroy();
    }
}
