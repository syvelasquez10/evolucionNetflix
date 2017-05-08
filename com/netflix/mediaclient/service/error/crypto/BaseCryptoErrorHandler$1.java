// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.error.crypto;

import com.netflix.mediaclient.util.AndroidUtils;
import android.content.Context;

class BaseCryptoErrorHandler$1 implements Runnable
{
    final /* synthetic */ BaseCryptoErrorHandler this$0;
    final /* synthetic */ Context val$context;
    
    BaseCryptoErrorHandler$1(final BaseCryptoErrorHandler this$0, final Context val$context) {
        this.this$0 = this$0;
        this.val$context = val$context;
    }
    
    @Override
    public void run() {
        AndroidUtils.forceStop(this.val$context);
    }
}
