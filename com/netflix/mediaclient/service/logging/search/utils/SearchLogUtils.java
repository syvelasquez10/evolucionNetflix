// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.logging.search.utils;

import com.netflix.mediaclient.service.logging.client.model.DeviceUniqueId;
import android.support.v4.content.LocalBroadcastManager;
import android.content.Intent;
import com.netflix.mediaclient.servicemgr.ISearchLogging$InputMode;
import com.netflix.mediaclient.servicemgr.IClientLogging$ModalView;
import android.content.Context;

public class SearchLogUtils
{
    public static void reportSearchEditChange(final long n, final Context context, final IClientLogging$ModalView clientLogging$ModalView, final String s, final ISearchLogging$InputMode searchLogging$InputMode) {
        final Intent intent = new Intent("com.netflix.mediaclient.intent.action.LOG_SUS_FOCUS_SEARCH_EDIT");
        intent.addCategory("com.netflix.mediaclient.intent.category.LOGGING");
        intent.putExtra("id", n);
        intent.putExtra("query", s);
        if (searchLogging$InputMode != null) {
            intent.putExtra("input_mode", searchLogging$InputMode.toString());
        }
        LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
    }
    
    public static void reportSearchFocusSessionEnded(final long n, final Context context, final long n2) {
        final Intent intent = new Intent("com.netflix.mediaclient.intent.action.LOG_SUS_FOCUS_SEARCH_SESSION_END");
        intent.addCategory("com.netflix.mediaclient.intent.category.LOGGING");
        intent.putExtra("id", n);
        intent.putExtra("session_id", n2);
        LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
    }
    
    public static long reportSearchFocusSessionStarted(final long n, final Context context, final IClientLogging$ModalView clientLogging$ModalView, final String s) {
        final DeviceUniqueId deviceUniqueId = new DeviceUniqueId();
        final Intent intent = new Intent("com.netflix.mediaclient.intent.action.LOG_SUS_FOCUS_SEARCH_SESSION_START");
        intent.addCategory("com.netflix.mediaclient.intent.category.LOGGING");
        intent.putExtra("view", clientLogging$ModalView.name());
        intent.putExtra("id", n);
        intent.putExtra("session_id", deviceUniqueId.getValue());
        if (s != null) {
            intent.putExtra("term", s);
        }
        LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
        return deviceUniqueId.getValue();
    }
    
    public static void reportSearchImpression(final long n, final Context context, final IClientLogging$ModalView clientLogging$ModalView, final String s, final String[] array, final int n2, final int n3, final IClientLogging$ModalView clientLogging$ModalView2) {
        final Intent intent = new Intent("com.netflix.mediaclient.intent.action.LOG_SUS_FOCUS_SEARCH_IMPRESSION");
        intent.addCategory("com.netflix.mediaclient.intent.category.LOGGING");
        intent.putExtra("id", n);
        intent.putExtra("reference", s);
        intent.putExtra("from", n2);
        intent.putExtra("to", n3);
        intent.putExtra("childIds", array);
        intent.putExtra("view", clientLogging$ModalView.name());
        intent.putExtra("view", clientLogging$ModalView2.name());
        LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
    }
    
    public static void reportSearchSessionEnded(final long n, final Context context, final long n2) {
        final Intent intent = new Intent("com.netflix.mediaclient.intent.action.LOG_SUS_SEARCH_SESSION_END");
        intent.addCategory("com.netflix.mediaclient.intent.category.LOGGING");
        intent.putExtra("id", n);
        intent.putExtra("session_id", n2);
        LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
    }
    
    public static long reportSearchSessionStarted(final long n, final Context context, final IClientLogging$ModalView clientLogging$ModalView, final String s) {
        final DeviceUniqueId deviceUniqueId = new DeviceUniqueId();
        final Intent intent = new Intent("com.netflix.mediaclient.intent.action.LOG_SUS_SEARCH_SESSION_START");
        intent.addCategory("com.netflix.mediaclient.intent.category.LOGGING");
        intent.putExtra("view", clientLogging$ModalView.name());
        intent.putExtra("id", n);
        intent.putExtra("session_id", deviceUniqueId.getValue());
        if (s != null) {
            intent.putExtra("term", s);
        }
        LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
        return deviceUniqueId.getValue();
    }
}
