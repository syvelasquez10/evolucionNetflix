// 
// Decompiled by Procyon v0.5.30
// 

package com.vailsys.whistleengine;

import android.os.Build$VERSION;
import android.os.IBinder;
import android.content.Intent;
import java.util.Iterator;
import java.util.Map;
import android.os.Handler;
import android.content.Context;
import android.app.Service;

class WhistleEngine$1 implements Runnable
{
    final /* synthetic */ WhistleEngine this$0;
    final /* synthetic */ String val$displayName;
    final /* synthetic */ int val$line;
    final /* synthetic */ String val$url;
    
    WhistleEngine$1(final WhistleEngine this$0, final String val$displayName, final int val$line, final String val$url) {
        this.this$0 = this$0;
        this.val$displayName = val$displayName;
        this.val$line = val$line;
        this.val$url = val$url;
    }
    
    @Override
    public void run() {
        String val$displayName;
        if (this.val$displayName.length() == 0) {
            val$displayName = null;
        }
        else {
            val$displayName = this.val$displayName;
        }
        if (this.this$0.eventListener != null) {
            this.this$0.eventListener.incomingCall(this.val$line, this.val$url, val$displayName);
        }
    }
}
