// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.android.widget;

import android.os.Looper;
import android.view.View;
import android.os.Handler;
import com.netflix.mediaclient.util.gfx.AnimationUtils;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.util.ViewUtils;
import com.netflix.mediaclient.util.ThreadUtils;

class LoadingAndErrorWrapper$1 implements Runnable
{
    final /* synthetic */ LoadingAndErrorWrapper this$0;
    
    LoadingAndErrorWrapper$1(final LoadingAndErrorWrapper this$0) {
        this.this$0 = this$0;
    }
    
    @Override
    public void run() {
        ThreadUtils.assertOnMain();
        if (ViewUtils.activityIsDead(this.this$0.loading)) {
            return;
        }
        Log.v("LoadingAndErrorWrapper", "Showing loading view without animation (via runnable)");
        AnimationUtils.showView(this.this$0.loading, false);
    }
}
