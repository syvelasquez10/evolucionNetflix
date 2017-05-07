// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.voip;

import android.view.ViewTreeObserver$OnGlobalLayoutListener;
import android.content.Context;
import android.graphics.PorterDuff$Mode;
import android.graphics.drawable.Drawable;
import com.netflix.mediaclient.servicemgr.IVoip$Call;
import com.netflix.mediaclient.util.AndroidUtils;
import android.media.ToneGenerator;
import android.view.View$OnTouchListener;
import android.os.Handler;
import com.netflix.mediaclient.util.TimeFormatterHelper;
import android.support.design.widget.FloatingActionButton;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TextView;
import android.view.View;
import android.media.AudioManager;
import com.netflix.mediaclient.Log;

class DialerScreen$6 implements Runnable
{
    final /* synthetic */ DialerScreen this$0;
    
    DialerScreen$6(final DialerScreen this$0) {
        this.this$0 = this$0;
    }
    
    @Override
    public void run() {
        if (this.this$0.mOwner.destroyed() || this.this$0.mOwner.isFinishing()) {
            Log.d("VoipActivity", "timer update exit");
            return;
        }
        this.this$0.setProgress();
        this.this$0.repostOnEverySecondRunnable(1000);
    }
}