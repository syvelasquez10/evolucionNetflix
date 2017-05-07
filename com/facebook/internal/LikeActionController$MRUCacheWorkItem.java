// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.internal;

import java.io.Closeable;
import java.io.IOException;
import android.content.BroadcastReceiver;
import android.content.IntentFilter;
import com.facebook.Settings;
import com.facebook.SessionState;
import android.os.Looper;
import com.facebook.widget.FacebookDialog$PendingCall;
import com.facebook.widget.FacebookDialog;
import com.facebook.widget.FacebookDialog$Callback;
import com.facebook.RequestBatch$Callback;
import com.facebook.RequestBatch;
import org.json.JSONException;
import android.util.Log;
import org.json.JSONObject;
import android.support.v4.content.LocalBroadcastManager;
import com.facebook.FacebookRequestError;
import android.app.Activity;
import android.content.Intent;
import com.facebook.Session;
import java.util.UUID;
import android.os.Bundle;
import android.content.Context;
import com.facebook.AppEventsLogger;
import android.os.Handler;
import java.util.concurrent.ConcurrentHashMap;
import java.util.ArrayList;

class LikeActionController$MRUCacheWorkItem implements Runnable
{
    private static ArrayList<String> mruCachedItems;
    private String cacheItem;
    private boolean shouldTrim;
    
    static {
        LikeActionController$MRUCacheWorkItem.mruCachedItems = new ArrayList<String>();
    }
    
    LikeActionController$MRUCacheWorkItem(final String cacheItem, final boolean shouldTrim) {
        this.cacheItem = cacheItem;
        this.shouldTrim = shouldTrim;
    }
    
    @Override
    public void run() {
        if (this.cacheItem != null) {
            LikeActionController$MRUCacheWorkItem.mruCachedItems.remove(this.cacheItem);
            LikeActionController$MRUCacheWorkItem.mruCachedItems.add(0, this.cacheItem);
        }
        if (this.shouldTrim && LikeActionController$MRUCacheWorkItem.mruCachedItems.size() >= 128) {
            while (64 < LikeActionController$MRUCacheWorkItem.mruCachedItems.size()) {
                LikeActionController.cache.remove(LikeActionController$MRUCacheWorkItem.mruCachedItems.remove(LikeActionController$MRUCacheWorkItem.mruCachedItems.size() - 1));
            }
        }
    }
}
