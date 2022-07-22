package com.samsung.android.samsungpay.gear.ui;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

public class SpayMainActivity extends Activity {
    private static final String TAG = "SPayToGPay";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Log.d(TAG, "Started SpayMainActivity. Attempting to start Google Pay.");

        // Build intent to launch Google Pay
        Intent activityIntent;
        activityIntent = new Intent(Intent.ACTION_MAIN);
        activityIntent.setComponent(ComponentName.unflattenFromString("com.google.android.apps.walletnfcrel/com.google.commerce.tapandpay.wear.cardlist.WalletThemedWearCardListActivity"));

        try {
            startActivity(activityIntent);

        } catch (Exception e) {
            Log.e(TAG, "Failed to start Google Pay activity");
            e.printStackTrace();
        }
        finish();
    }
}