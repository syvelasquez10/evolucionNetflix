// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.falkor.cache;

import com.netflix.mediaclient.android.app.ApplicationStateListener;
import com.netflix.mediaclient.NetflixApplication;
import org.json.JSONException;
import com.netflix.mediaclient.Log;
import org.json.JSONObject;
import java.util.ArrayList;
import android.os.Looper;
import io.realm.Realm;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicInteger;
import android.os.Handler;

class FalkorCacheMonitor$1 implements Runnable
{
    final /* synthetic */ FalkorCacheMonitor this$0;
    
    FalkorCacheMonitor$1(final FalkorCacheMonitor this$0) {
        this.this$0 = this$0;
    }
    
    @Override
    public void run() {
        this.this$0.notifyListeners(this.this$0.mDataUpdated);
        this.this$0.mDataUpdated.clear();
    }
}
