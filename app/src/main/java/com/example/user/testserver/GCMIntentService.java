package com.example.user.testserver;

/**
 * Created by user on 2016-07-21.
 */
import android.app.IntentService;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.NotificationCompat;

import com.google.android.gms.gcm.GoogleCloudMessaging;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Calendar;

public class GCMIntentService extends IntentService {
    public static final int NOTIFICATION_ID = 1;
    private NotificationManager mNotificationManager;

    // web server 에서 받을 extra key (web server 와 동일해야 함)
    static final String TITLE_EXTRA_KEY = "TITLE";
    static final String MSG_EXTRA_KEY = "MSG";
    static final String TYPE_EXTRA_CODE = "TYPE_CODE";
    // web server 에서 받을 extras key

    public GCMIntentService() {
        super("");
    }

    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     *
     * @param name Used to name the worker thread, important only for debugging.
     */
    public GCMIntentService(String name) {
        super(name);
        System.out.println("************************************************* GCMIntentService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {

        DatabaseHelper db = new DatabaseHelper(getApplicationContext());
        Bundle extras = intent.getExtras();
        GoogleCloudMessaging gcm = GoogleCloudMessaging.getInstance(this);
        // The getMessageType() intent parameter must be the intent you received
        // in your BroadcastReceiver.
        String messageType = gcm.getMessageType(intent);

        System.out.println("************************************************* messageType : " + messageType);

        if (!extras.isEmpty()) {
            if (GoogleCloudMessaging.MESSAGE_TYPE_MESSAGE.equals(messageType)) {
                // This loop represents the service doing some work.
                // 메시지를 받은 후 작업 시작
                System.out.println("************************************************* Working........................... ");

                // Post notification of received message.
                System.out.println("************************************************* 상태바 알림 호출");

                if(extras.getString(TITLE_EXTRA_KEY).equals("server1")) {
                    if(db.checkServer("server1"))
                        sendNotification(extras);
                }
                if(extras.getString(TITLE_EXTRA_KEY).equals("server2")) {
                    if(db.checkServer("server2"))
                        sendNotification(extras);
                }
                if(extras.getString(TITLE_EXTRA_KEY).equals("server3")) {
                    if(db.checkServer("server3"))
                        sendNotification(extras);
                }

                System.out.println("************************************************* Received toString : " + extras.toString());
            }
        }
        // Release the wake lock provided by the WakefulBroadcastReceiver.
        GcmBroadcastReceiver.completeWakefulIntent(intent);

    }

    // 상태바에 공지
    private void sendNotification(Bundle extras) {
        // 혹시 모를 사용가능한 코드
        String typeCode = extras.getString(TYPE_EXTRA_CODE);

        mNotificationManager = (NotificationManager)
                this.getSystemService(Context.NOTIFICATION_SERVICE);

/*
        Intent popupIntent = new Intent(getApplicationContext(), GcmPopupActivity.class);

        PendingIntent pie= PendingIntent.getActivity(getApplicationContext(), 0, popupIntent, PendingIntent.FLAG_ONE_SHOT);
        try {
            pie.send();
        } catch (PendingIntent.CanceledException e) {
            //   Log.degug(e.getMessage());
        }
*/
        PendingIntent contentIntent = PendingIntent.getActivity(this, 0,
                new Intent(this, PushListActivity.class), 0);

        NotificationCompat.Builder mBuilder =
                null;
        try {
            mBuilder = (NotificationCompat.Builder) new NotificationCompat.Builder(this)
                    .setVisibility(NotificationCompat.VISIBILITY_PUBLIC)
                    .setSmallIcon(R.mipmap.shinhanlogo)
                    .setContentTitle(URLDecoder.decode(extras.getString(TITLE_EXTRA_KEY), "UTF-8"))
                    .setStyle(new NotificationCompat.BigTextStyle()
                            .bigText(URLDecoder.decode(extras.getString(MSG_EXTRA_KEY), "UTF-8")))
                    .setContentText(URLDecoder.decode(extras.getString(MSG_EXTRA_KEY), "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        mBuilder.setVibrate(new long[]{0,3000}); // 진동 효과 (퍼미션 필요)
        mBuilder.setAutoCancel(true); // 클릭하면 삭제


        Calendar cal = Calendar.getInstance();


        PushItem item = new PushItem();
        item.setTitle(extras.getString(TITLE_EXTRA_KEY));
        item.setText(extras.getString(MSG_EXTRA_KEY));
        item.setTime(cal.getTime().toString());

        CountItem countItem = new CountItem();
        countItem.setTitle(extras.getString(TITLE_EXTRA_KEY));
        countItem.setText(extras.getString(MSG_EXTRA_KEY));


        DatabaseHelper db = new DatabaseHelper(getApplicationContext());



        if(db.checkItem(extras.getString(MSG_EXTRA_KEY))) {
            int count= db.checkCount(extras.getString(MSG_EXTRA_KEY))+1;
            countItem.setCount(count);

            db.upgradeCount(countItem);
            System.out.println("기존에 존재");
        }
        else {
            System.out.println("처음만들때");
            int firstpush = 1;
            countItem.setCount(firstpush);
            db.createCount(countItem);
        }

        db.createItems(item);

        Intent popupIntent = new Intent(getApplicationContext(), GcmPopupActivity.class);

        popupIntent.putExtra("push", item);

        PendingIntent pie= PendingIntent.getActivity(getApplicationContext(), 0, popupIntent, PendingIntent.FLAG_ONE_SHOT);
        try {
            pie.send();
        } catch (PendingIntent.CanceledException e) {
            //   Log.degug(e.getMessage());
        }

        mBuilder.setContentIntent(contentIntent);
        mNotificationManager.notify(NOTIFICATION_ID, mBuilder.build());
    }

}

