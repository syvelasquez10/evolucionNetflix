// 
// Decompiled by Procyon v0.5.30
// 

package com.vailsys.whistleengine;

import android.os.Build$VERSION;
import android.os.IBinder;
import android.content.Intent;
import java.util.Iterator;
import java.util.Map;
import android.util.Log;
import android.app.Notification;
import android.os.Handler;
import android.content.Context;
import android.app.Service;

class WhistleEngine$13 implements Runnable
{
    final /* synthetic */ WhistleEngine this$0;
    final /* synthetic */ String val$codec;
    final /* synthetic */ int val$line;
    final /* synthetic */ int val$sampleRate;
    
    WhistleEngine$13(final WhistleEngine this$0, final int val$line, final String val$codec, final int val$sampleRate) {
        this.this$0 = this$0;
        this.val$line = val$line;
        this.val$codec = val$codec;
        this.val$sampleRate = val$sampleRate;
    }
    
    @Override
    public void run() {
        if (this.this$0.eventListener != null) {
            this.this$0.eventListener.selectedCodec(this.val$line, this.val$codec, this.val$sampleRate);
        }
    }
}
