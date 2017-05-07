// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient;

import com.netflix.mediaclient.service.NetflixService;
import java.util.HashMap;
import java.net.URLDecoder;
import android.content.Intent;
import android.content.Context;
import android.content.BroadcastReceiver;

public class InstallReferrerReceiver extends BroadcastReceiver
{
    private static final String INSTALL_ACTION = "com.android.vending.INSTALL_REFERRER";
    private static final String PROPERTY_REFFERER = "referrer";
    public static final String REFFERER_KEY_TOKEN = "token";
    private static final String TAG = "nf_install";
    
    private void handleInstallation(final Context context, final Intent intent) {
        final String s = "";
        String s2;
        if (!intent.hasExtra("referrer")) {
            Log.e("nf_install", "Refferer property not found in intent, do default");
            s2 = s;
        }
        else {
            final String decode = URLDecoder.decode(intent.getStringExtra("referrer"));
            if (Log.isLoggable()) {
                Log.d("nf_install", "Refferer: " + decode);
            }
            final HashMap<Object, String> hashMap = new HashMap<Object, String>();
            final String[] split = decode.split("&");
            if (split != null && split.length > 0) {
                for (int length = split.length, i = 0; i < length; ++i) {
                    final String[] split2 = split[i].split("=");
                    if (split2 != null && split2.length >= 2) {
                        hashMap.put(split2[0], split2[1]);
                        if (Log.isLoggable()) {
                            Log.d("nf_install", "Key: " + split2[0] + ", value: " + split2[1]);
                        }
                    }
                }
            }
            if (!hashMap.containsKey("token")) {
                Log.e("nf_install", "Token not found, do default!");
                s2 = "";
            }
            else {
                s2 = (String)hashMap.get("token");
            }
        }
        if (Log.isLoggable()) {
            Log.d("nf_install", "Token: " + s2 + ", start service...");
        }
        final Intent intent2 = new Intent("com.netflix.mediaclient.intent.action.USER_AUTOLOGIN");
        intent2.setClass(context, (Class)NetflixService.class);
        intent2.addCategory("com.netflix.mediaclient.intent.category.USER");
        intent2.putExtra("token", s2);
        context.startService(intent2);
    }
    
    public void onReceive(final Context context, final Intent intent) {
        if ("com.android.vending.INSTALL_REFERRER".equals(intent.getAction())) {
            Log.d("nf_install", "Installation intent received");
            Log.d("nf_install", intent);
            this.handleInstallation(context, intent);
            return;
        }
        Log.e("nf_install", "Unexpected intent received");
        Log.d("nf_install", intent);
    }
}
