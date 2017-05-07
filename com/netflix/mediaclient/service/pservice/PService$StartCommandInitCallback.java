// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.pservice;

import android.os.IBinder;
import com.netflix.mediaclient.util.AndroidUtils;
import java.util.Iterator;
import com.netflix.mediaclient.util.ThreadUtils;
import android.content.Context;
import com.netflix.mediaclient.util.StringUtils;
import com.netflix.mediaclient.Log;
import java.util.ArrayList;
import android.app.Service;
import android.content.Intent;

final class PService$StartCommandInitCallback implements PService$InitCallback
{
    private final int flags;
    private final Intent intent;
    private final int startId;
    final /* synthetic */ PService this$0;
    
    public PService$StartCommandInitCallback(final PService this$0, final Intent intent, final int flags, final int startId) {
        this.this$0 = this$0;
        this.intent = intent;
        this.flags = flags;
        this.startId = startId;
    }
    
    @Override
    public void onInitComplete() {
        this.this$0.doStartCommand(this.intent, this.flags, this.startId);
    }
}
