package com.samsung.android.samsungpay.gear.ui;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.ComponentInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SpayMainActivity extends Activity {
    private static final String TAG = "SPayToGPay";
    private static final String COMPONENT_GOOGLE_PAY = "com.google.android.apps.walletnfcrel/com.google.commerce.tapandpay.android.wearable.cardlist.WearPayActivity";
    private static final String COMPONENT_GOOGLE_WALLET = "com.google.android.apps.walletnfcrel/com.google.commerce.tapandpay.wear.cardlist.WalletThemedWearCardListActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Log.d(TAG, "Started SpayMainActivity. Attempting to start Google Pay/Wallet.");

        ComponentName googlePayComponent = ComponentName.unflattenFromString(COMPONENT_GOOGLE_PAY);
        ComponentName googleWalletComponent = ComponentName.unflattenFromString(COMPONENT_GOOGLE_WALLET);

        // Build intent to launch Google Pay/Wallet
        Intent activityIntent;
        activityIntent = new Intent(Intent.ACTION_MAIN);

        if (isComponentEnabled(googleWalletComponent)) {
            activityIntent.setComponent(googleWalletComponent);
        } else if (isComponentEnabled(googlePayComponent)) {
            activityIntent.setComponent(googlePayComponent);
        }

        try {
            startActivity(activityIntent);

        } catch (Exception e) {
            Log.e(TAG, "Failed to start Google Pay/Wallet activity");
            e.printStackTrace();
        }
        finish();
    }

    @SuppressLint("SwitchIntDef")
    private boolean isComponentEnabled(ComponentName componentName) {
        PackageManager pm = getPackageManager();
        int componentEnabledSetting = pm.getComponentEnabledSetting(componentName);

        switch (componentEnabledSetting) {
            case PackageManager.COMPONENT_ENABLED_STATE_DISABLED:
                return false;
            case PackageManager.COMPONENT_ENABLED_STATE_ENABLED:
                return true;
            case PackageManager.COMPONENT_ENABLED_STATE_DEFAULT:
            default:
                // Need to get the application info to get the component's default state
                try {
                    PackageInfo packageInfo = pm.getPackageInfo(componentName.getPackageName(),
                            PackageManager.GET_ACTIVITIES | PackageManager.MATCH_DISABLED_COMPONENTS);

                    List<ComponentInfo> components = new ArrayList<>();
                    if (packageInfo.activities != null) {
                        Collections.addAll(components, packageInfo.activities);
                    }

                    for (ComponentInfo componentInfo : components) {
                        if (componentInfo.name.equals(componentName.getClassName())) {
                            return componentInfo.isEnabled();
                        }
                    }

                    // The component is not declared in the AndroidManifest
                    return false;
                } catch (PackageManager.NameNotFoundException e) {
                    // The package isn't installed on the device
                    return false;
                }
        }
    }
}