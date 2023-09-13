package com.samsung.android.samsungpay.gear.presentation;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.provider.Settings;
import android.util.Log;
import android.widget.Toast;

import com.samsung.android.samsungpay.gear.R;

public class WalletLaunchReceiver extends BroadcastReceiver {
    public static final String TAG = "SPayToGPayWalletLaunchReceiver";

    @Override
    public void onReceive(Context context, Intent intent) {

        if (intent != null) {
            Log.d(TAG, "onReceive(): " + intent.getAction());

            if (intent.getAction().equals("com.samsung.android.wearable.sysui.action.CHECK_AND_LAUNCH_WALLET")) {
                Log.d(TAG, "Started from CHECK_AND_LAUNCH_WALLET broadcast. Attempting to start Google Pay.");

                if (!Settings.canDrawOverlays(context)) {
                    Toast.makeText(context, R.string.error_missing_permission, Toast.LENGTH_LONG).show();
                    Log.d(TAG, "The required SYSTEM_ALERT_WINDOW has not been granted");
                    return;
                }

                // Build intent to launch Google Pay
                Intent activityIntent;
                activityIntent = new Intent(Intent.ACTION_MAIN);
                activityIntent.setComponent(ComponentName.unflattenFromString("com.google.android.apps.walletnfcrel/com.google.commerce.tapandpay.wear.cardlist.WalletThemedWearCardListActivity"));
                activityIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                try {
                    context.startActivity(activityIntent);

                } catch (Exception e) {
                    Log.e(TAG, "Failed to start Google Pay activity");
                    e.printStackTrace();
                }
            }
        }
    }
}