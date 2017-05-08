// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service;

import android.net.NetworkInfo;
import com.netflix.mediaclient.javabridge.invoke.android.SetNetworkInterfaces;
import com.netflix.mediaclient.util.ConnectivityUtils;
import com.netflix.mediaclient.javabridge.invoke.android.SetLanguage;
import com.netflix.mediaclient.util.StringUtils;
import java.util.Locale;
import com.netflix.mediaclient.android.app.Status;
import com.netflix.mediaclient.android.app.CommonStatus;
import com.netflix.mediaclient.javabridge.invoke.Invoke;
import com.netflix.mediaclient.javabridge.invoke.mdx.InterfaceChanged;
import com.netflix.mediaclient.util.l10n.UserLocale;
import com.netflix.mediaclient.repository.SecurityRepository;
import com.netflix.mediaclient.util.FileUtils;
import com.netflix.mediaclient.javabridge.ui.EventListener;
import com.netflix.mediaclient.javabridge.ui.android.NrdpWrapper;
import com.netflix.mediaclient.javabridge.Bridge;
import com.netflix.mediaclient.javabridge.NrdProxyFactory;
import com.netflix.mediaclient.javabridge.ui.Nrdp;
import com.netflix.mediaclient.javabridge.NrdProxy;
import android.os.Bundle;
import com.netflix.mediaclient.Log;
import android.content.Intent;
import android.content.Context;
import android.content.BroadcastReceiver;

class NrdController$NrdJSCmdReceiver extends BroadcastReceiver
{
    public static final String JS_BRIDGE_INTENT = "com.netflix.mediaclient.intent.action.JS_BRIDGE_CMD";
    final /* synthetic */ NrdController this$0;
    
    private NrdController$NrdJSCmdReceiver(final NrdController this$0) {
        this.this$0 = this$0;
    }
    
    public void onReceive(final Context context, final Intent intent) {
        if (Log.isLoggable()) {
            Log.d("nf_nrdcontroller", "Received an action: " + intent.getAction());
        }
        if (!"com.netflix.mediaclient.intent.action.JS_BRIDGE_CMD".equals(intent.getAction())) {
            return;
        }
        try {
            final Bundle extras = intent.getExtras();
            if (extras != null) {
                final String string = extras.getString("object");
                final String string2 = extras.getString("method");
                final String string3 = extras.getString("params");
                if (string != null && string2 != null && string3 != null) {
                    this.this$0.nrd.invokeMethod(string, string2, string3);
                }
                if (Log.isLoggable()) {
                    Log.d("nf_nrdcontroller", "JS CMD: object: " + string + " method:  " + string2 + " param: " + string3);
                }
            }
        }
        catch (Exception ex) {
            Log.e("nf_nrdcontroller", "Unintented Exception thrown ", ex);
        }
    }
}
