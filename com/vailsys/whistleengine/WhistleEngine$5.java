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

class WhistleEngine$5 implements Runnable
{
    final /* synthetic */ WhistleEngine this$0;
    final /* synthetic */ int val$line;
    final /* synthetic */ String val$reason;
    final /* synthetic */ int val$sipCode;
    
    WhistleEngine$5(final WhistleEngine this$0, final int val$line, final String val$reason, final int val$sipCode) {
        this.this$0 = this$0;
        this.val$line = val$line;
        this.val$reason = val$reason;
        this.val$sipCode = val$sipCode;
    }
    
    @Override
    public void run() {
        if (this.this$0.eventListener != null) {
            this.this$0.eventListener.callFailed(this.val$line, this.val$reason, this.val$sipCode);
        }
    }
}
