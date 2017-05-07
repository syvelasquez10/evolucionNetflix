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
    private final SessionWatchDogInterface mCallback;
    private final Handler mHandler;
    private final Runnable mPeriodicRunnable;
    
    MdxSessionWatchDog(final SessionWatchDogInterface mCallback, final Handler mHandler) {
        this.mCallback = mCallback;
        this.mHandler = mHandler;
        this.mPeriodicRunnable = new Runnable() {
            @Override
            public void run() {
                if (System.currentTimeMillis() - Long.valueOf(MdxSessionWatchDog.this.mCallback.onGetTimeOfMostRecentIncomingMessage()) > 300000L) {
                    Log.d("nf_mdxSessionWatchDog", "MdxSessionWatchDog:  expired");
                    MdxSessionWatchDog.this.mCallback.onSessionWatchDogExpired();
                    return;
                }
                Log.d("nf_mdxSessionWatchDog", "MdxSessionWatchDog: check if session still alive");
                MdxSessionWatchDog.this.mHandler.postDelayed(MdxSessionWatchDog.this.mPeriodicRunnable, 300000L);
            }
        };
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
    
    public interface SessionWatchDogInterface
    {
        long onGetTimeOfMostRecentIncomingMessage();
        
        void onSessionWatchDogExpired();
    }
}
