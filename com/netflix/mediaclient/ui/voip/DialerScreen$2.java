// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.voip;

import android.view.ViewGroup$LayoutParams;
import android.widget.LinearLayout$LayoutParams;
import android.content.Context;
import com.netflix.mediaclient.util.AndroidUtils;
import android.app.Activity;
import com.netflix.mediaclient.util.DeviceUtils;
import com.netflix.mediaclient.servicemgr.IVoip$Call;
import android.media.ToneGenerator;
import android.view.View$OnTouchListener;
import android.widget.ImageView;
import android.os.Handler;
import com.netflix.mediaclient.util.TimeFormatterHelper;
import android.support.design.widget.FloatingActionButton;
import android.widget.TableLayout;
import android.widget.TextView;
import android.view.View;
import android.media.AudioManager;
import com.netflix.mediaclient.Log;

class DialerScreen$2 implements Runnable
{
    final /* synthetic */ DialerScreen this$0;
    
    DialerScreen$2(final DialerScreen this$0) {
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
