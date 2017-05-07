// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.mdx;

import android.os.Handler;
import com.netflix.mediaclient.Log;

class MdxSessionWatchDog$1 implements Runnable
{
    final /* synthetic */ MdxSessionWatchDog this$0;
    
    MdxSessionWatchDog$1(final MdxSessionWatchDog this$0) {
        this.this$0 = this$0;
    }
    
    @Override
    public void run() {
        if (System.currentTimeMillis() - Long.valueOf(this.this$0.mCallback.onGetTimeOfMostRecentIncomingMessage()) > 300000L) {
            Log.d("nf_mdxSessionWatchDog", "MdxSessionWatchDog:  expired");
            this.this$0.mCallback.onSessionWatchDogExpired();
            return;
        }
        Log.d("nf_mdxSessionWatchDog", "MdxSessionWatchDog: check if session still alive");
        this.this$0.mHandler.postDelayed(this.this$0.mPeriodicRunnable, 300000L);
    }
}
