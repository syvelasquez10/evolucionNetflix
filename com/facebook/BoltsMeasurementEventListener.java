// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook;

import java.util.Iterator;
import android.os.Bundle;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;
import android.content.Context;
import android.content.BroadcastReceiver;

public class BoltsMeasurementEventListener extends BroadcastReceiver
{
    private static final String BOLTS_MEASUREMENT_EVENT_PREFIX = "bf_";
    private static final String MEASUREMENT_EVENT_ARGS_KEY = "event_args";
    private static final String MEASUREMENT_EVENT_NAME_KEY = "event_name";
    private static final String MEASUREMENT_EVENT_NOTIFICATION_NAME = "com.parse.bolts.measurement_event";
    private static BoltsMeasurementEventListener _instance;
    private Context applicationContext;
    
    private BoltsMeasurementEventListener(final Context context) {
        this.applicationContext = context.getApplicationContext();
    }
    
    private void close() {
        LocalBroadcastManager.getInstance(this.applicationContext).unregisterReceiver(this);
    }
    
    static BoltsMeasurementEventListener getInstance(final Context context) {
        if (BoltsMeasurementEventListener._instance != null) {
            return BoltsMeasurementEventListener._instance;
        }
        (BoltsMeasurementEventListener._instance = new BoltsMeasurementEventListener(context)).open();
        return BoltsMeasurementEventListener._instance;
    }
    
    private void open() {
        LocalBroadcastManager.getInstance(this.applicationContext).registerReceiver(this, new IntentFilter("com.parse.bolts.measurement_event"));
    }
    
    protected void finalize() {
        try {
            this.close();
        }
        finally {
            super.finalize();
        }
    }
    
    public void onReceive(final Context context, final Intent intent) {
        final AppEventsLogger logger = AppEventsLogger.newLogger(context);
        final String string = "bf_" + intent.getStringExtra("event_name");
        final Bundle bundleExtra = intent.getBundleExtra("event_args");
        final Bundle bundle = new Bundle();
        for (final String s : bundleExtra.keySet()) {
            bundle.putString(s.replaceAll("[^0-9a-zA-Z _-]", "-").replaceAll("^[ -]*", "").replaceAll("[ -]*$", ""), (String)bundleExtra.get(s));
        }
        logger.logEvent(string, bundle);
    }
}
