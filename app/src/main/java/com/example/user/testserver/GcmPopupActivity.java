package com.example.user.testserver;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.app.KeyguardManager;
import android.content.Context;
import android.os.PowerManager;
import android.util.Log;

/**
 * Created by user on 2016-07-22.
 */
public class GcmPopupActivity extends Activity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED
                | WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD
                | WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON);


        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.popup_activity);


    }
}

class PushWakeLock {

    private static PowerManager.WakeLock sCpuWakeLock;
    private static KeyguardManager.KeyguardLock mKeyguardLock;
    private static boolean isScreenLock;

    static void acquireCpuWakeLock(Context context) {
        Log.e("PushWakeLock", "Acquiring cpu wake lock");
        if (sCpuWakeLock != null) {
            return;
        }

        PowerManager pm = (PowerManager) context.getSystemService(Context.POWER_SERVICE);

        sCpuWakeLock = pm.newWakeLock(
                PowerManager.SCREEN_BRIGHT_WAKE_LOCK |
                        PowerManager.ACQUIRE_CAUSES_WAKEUP |
                        PowerManager.ON_AFTER_RELEASE, "I'm your father");
        sCpuWakeLock.acquire();

//        KeyguardManager km = (KeyguardManager)context.getSystemService(context.KEYGUARD_SERVICE);
//        mKeyguardLock = km.newKeyguardLock("key guard");
//        if (km.inKeyguardRestrictedInputMode()) {
//        mKeyguardLock.disableKeyguard();
//          isScreenLock = true;
//        } else {
//          isScreenLock = false;
//        }
    }

    static void releaseCpuLock() {
        Log.e("PushWakeLock", "Releasing cpu wake lock");
//        if (isScreenLock) {
//          mKeyguardLock.reenableKeyguard();
//          isScreenLock = false;
//      }

        if (sCpuWakeLock != null) {
            sCpuWakeLock.release();
            sCpuWakeLock = null;
        }
    }
}