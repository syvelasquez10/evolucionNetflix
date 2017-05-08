// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.exoplayer;

import com.google.android.exoplayer.upstream.NetworkLock;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import android.os.Handler;
import com.google.android.exoplayer.upstream.Allocator;

class DefaultLoadControl$1 implements Runnable
{
    final /* synthetic */ DefaultLoadControl this$0;
    final /* synthetic */ boolean val$loading;
    
    DefaultLoadControl$1(final DefaultLoadControl this$0, final boolean val$loading) {
        this.this$0 = this$0;
        this.val$loading = val$loading;
    }
    
    @Override
    public void run() {
        this.this$0.eventListener.onLoadingChanged(this.val$loading);
    }
}
