package com.example.user.testserver;

/**
 * Created by user on 2016-07-21.
 */
import android.content.Intent;

import com.google.android.gms.iid.InstanceIDListenerService;
public class MyInstanceIDListenerService extends InstanceIDListenerService {

    private static final String TAG = "MyInstanceIDLS";

    @Override
    public void onTokenRefresh() {
        Intent intent = new Intent(this, RegistrationIntentService.class);
        startService(intent);
    }
}
