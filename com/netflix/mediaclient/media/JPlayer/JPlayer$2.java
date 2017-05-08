// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.media.JPlayer;

import android.util.Log;

class JPlayer$2 implements UncaughtExceptionHandler
{
    final /* synthetic */ JPlayer this$0;
    
    JPlayer$2(final JPlayer this$0) {
        this.this$0 = this$0;
    }
    
    @Override
    public void uncaughtException(final Thread thread, final Throwable t) {
        if (Log.isLoggable("NF_JPlayer", 3)) {
            Log.d("NF_JPlayer", "videoConfigureThread has exception " + t);
        }
    }
}
