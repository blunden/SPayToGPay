SPayToGPay
============

A helper app to remap the Samsung Pay button shortcut on the
Galaxy Watch 4/5/6 to Google Pay on watches in **regions shipping
without Samsung Pay preinstalled**.

Watches that ship without Samsung Pay end up with a long-press
shortcut that they can't remap. This app makes the shortcut
launch Google Pay instead.

**NOTE:** Requires a watch reboot to take effect.

**NOTE:** On Wear OS 4.0 (One UI 5), the user needs to grant the
app the SYSTEM_ALERT_WINDOW permission in order for it to be able
to launch the Google Pay app from the background.

`adb shell pm grant com.samsung.android.samsungpay.gear android.permission.SYSTEM_ALERT_WINDOW`

[XDA Developers](https://forum.xda-developers.com/t/spaytogpay-remap-samsung-pay-long-press-shortcut-to-google-pay-on-watches-in-regions-shipping-without-samsung-pay.4459229/)