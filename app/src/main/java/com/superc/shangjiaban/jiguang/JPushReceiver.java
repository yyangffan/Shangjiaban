package com.superc.shangjiaban.jiguang;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.superc.shangjiaban.R;
import com.superc.shangjiaban.ui.MainActivity;
import com.superc.shangjiaban.utils.ShareUtil;

import org.json.JSONObject;

import cn.jpush.android.api.JPushInterface;

import static android.content.Context.NOTIFICATION_SERVICE;

/**
 * 接收到极光推送后的操作设置
 */

public class JPushReceiver extends BroadcastReceiver {
    private static final String TAG = "JPushReceiver";

    private NotificationManager nm;
    MediaPlayer mMediaPlayer;

    @Override
    public void onReceive(Context context, Intent intent) {
        if (null == nm) {
            nm = (NotificationManager) context.getSystemService(NOTIFICATION_SERVICE);
        }
        Bundle bundle = intent.getExtras();

        if (JPushInterface.ACTION_REGISTRATION_ID.equals(intent.getAction())) {
            Log.d(TAG, "JPush用户注册成功");

        } else if (JPushInterface.ACTION_MESSAGE_RECEIVED.equals(intent.getAction())) {
            Log.d(TAG, "接受到推送下来的自定义消息");

        } else if (JPushInterface.ACTION_NOTIFICATION_RECEIVED.equals(intent.getAction())) {
            Log.d(TAG, "接受到推送下来的通知");

            receivingNotification(context, bundle);

        } else if (JPushInterface.ACTION_NOTIFICATION_OPENED.equals(intent.getAction())) {
            Log.d(TAG, "用户点击打开了通知");

            openNotification(context, bundle);

        } else {
            Log.d(TAG, "Unhandled intent - " + intent.getAction());
        }
    }

    private void receivingNotification(Context context, Bundle bundle) {
        String title = bundle.getString(JPushInterface.EXTRA_NOTIFICATION_TITLE);
        Log.d(TAG, " title : " + title);
        String message = bundle.getString(JPushInterface.EXTRA_ALERT);
        Log.d(TAG, "message : " + message);
        String extras = bundle.getString(JPushInterface.EXTRA_EXTRA);
        Log.d(TAG, "extras : " + extras);
        String type = "";
        try {
            JSONObject extrasJson = new JSONObject(extras);
            type = extrasJson.optString("type");
            Log.e("接受下来的通知类型", type);
        } catch (Exception e) {
            Log.w(TAG, "Unexpected: extras is not a valid json", e);
            return;
        }
        switch (type) {
            case "10"://有新订单
                mMediaPlayer = MediaPlayer.create(context, R.raw.tongzhi);
                mMediaPlayer.start();
                ShareUtil.getInstance(context).put("isJp", true);
//                processCustomMessage(context,bundle);
//                ShareUtil.getInstance(AppAppLication.getmContext()).put("isJp", true);
                break;
        }

    }

    private void openNotification(Context context, Bundle bundle) {
        String extras = bundle.getString(JPushInterface.EXTRA_EXTRA);
        String type = "";
        try {
            JSONObject extrasJson = new JSONObject(extras);
            type = extrasJson.optString("type");
            Log.e("接受下来的通知类型", type);
        } catch (Exception e) {
            Log.w(TAG, "Unexpected: extras is not a valid json", e);
            return;
        }
        switch (type) {
            case "10"://跳转到订单的全部订单
                Intent mIntent = new Intent(context, MainActivity.class);
                mIntent.putExtra("tab", 4);
                mIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(mIntent);
                break;
        }
    }

    /**
     * 实现自定义推送声音
     * @param context
     * @param bundle
     */
    private void processCustomMessage(Context context, Bundle bundle) {
        NotificationCompat.Builder notification = new NotificationCompat.Builder(context);

        String title = bundle.getString(JPushInterface.EXTRA_TITLE);
        String msg = bundle.getString(JPushInterface.EXTRA_MESSAGE);
        String extras = bundle.getString(JPushInterface.EXTRA_EXTRA);
        Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), R.mipmap.ic_launcher);

        Intent mIntent = new Intent(context,MainActivity.class);
        mIntent.putExtra("tab", 4);
        mIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, mIntent, 0);

        notification.setContentIntent(pendingIntent)
                .setAutoCancel(true)
                .setContentText(msg)
                .setContentTitle("您有新的订单,请查看")
                .setSmallIcon(R.mipmap.ic_launcher)
                .setLargeIcon(bitmap)
                .setNumber(3);

        Log.e(TAG, "processCustomMessage: extras----->" + extras);
//        if (!TextUtils.isEmpty(extras)) {
//            try {
//                JSONObject extraJson = new JSONObject(extras);
//                if (null != extraJson && extraJson.length() > 0) {
//                    String sound = extraJson.getString("sound");
//                    if("1".equals(sound)){
                        notification.setSound(Uri.parse("android.resource://" + context.getPackageName() + "/" +R.raw.tongzhi));
//                    } else {
//                        notification.setSound(Uri.parse("android.resource://" + context.getPackageName() + "/" +R.raw.tongzhi));
//                    }
//                }
//            } catch (JSONException e) {
//                e.printStackTrace();
//            }
//        }

        NotificationManager notificationManager = (NotificationManager) context.getSystemService(NOTIFICATION_SERVICE);
        notificationManager.notify(3, notification.build());  //id随意，正好使用定义的常量做id，0除外，0为默认的Notification
    }

}
