// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.mdx;

import com.netflix.mediaclient.Log;
import android.os.Handler;

public class MdxSessionWatchDog
{
    private static final long SESSION_EXPIRE_TIME_MS = 300000L;
    private static final String TAG = "nf_mdxSessionWatchDog";
    private final MdxSessionWatchDog$SessionWatchDogInterface mCallback;
    private final Handler mHandler;
    private final Runnable mPeriodicRunnable;
    
    MdxSessionWatchDog(final MdxSessionWatchDog$SessionWatchDogInterface mCallback, final Handler mHandler) {
        this.mCallback = mCallback;
        this.mHandler = mHandler;
        this.mPeriodicRunnable = new MdxSessionWatchDog$1(this);
    }
    
    void start() {
        Log.d("nf_mdxSessionWatchDog", "MdxSessionWatchDog: start");
        this.mHandler.removeCallbacks(this.mPeriodicRunnable);
        this.mHandler.postDelayed(this.mPeriodicRunnable, 300000L);
    }
    
    void stop() {
        Log.d("nf_mdxSessionWatchDog", "MdxSessionWatchDog: stop");
        this.mHandler.removeCallbacks(this.mPeriodicRunnable);
    }
}
