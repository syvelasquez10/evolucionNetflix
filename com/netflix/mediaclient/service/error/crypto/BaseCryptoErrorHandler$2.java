// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.error.crypto;

import com.netflix.mediaclient.util.AndroidUtils;
import com.netflix.mediaclient.Log;
import android.content.Context;

class BaseCryptoErrorHandler$2 implements Runnable
{
    final /* synthetic */ BaseCryptoErrorHandler this$0;
    final /* synthetic */ Context val$context;
    final /* synthetic */ Runnable val$killApp;
    
    BaseCryptoErrorHandler$2(final BaseCryptoErrorHandler this$0, final Runnable val$killApp, final Context val$context) {
        this.this$0 = this$0;
        this.val$killApp = val$killApp;
        this.val$context = val$context;
    }
    
    @Override
    public void run() {
        if (CryptoErrorManager.INSTANCE.setActionToExecuteOnExitIfContentRemovalIsInProgress(this.val$killApp)) {
            Log.d(BaseCryptoErrorHandler.TAG, "Offline content removal is IN PROGRESS, do NOT kill app yet");
            return;
        }
        Log.d(BaseCryptoErrorHandler.TAG, "Offline content removal is not in progress, kill app!");
        AndroidUtils.forceStop(this.val$context);
    }
}
