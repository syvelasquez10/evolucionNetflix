// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.voip;

import android.view.ViewTreeObserver$OnGlobalLayoutListener;
import android.content.Context;
import android.graphics.PorterDuff$Mode;
import android.graphics.drawable.Drawable;
import com.netflix.mediaclient.servicemgr.IVoip$Call;
import com.netflix.mediaclient.Log;
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

class DialerScreen$4 implements Runnable
{
    final /* synthetic */ DialerScreen this$0;
    
    DialerScreen$4(final DialerScreen this$0) {
        this.this$0 = this$0;
    }
    
    @Override
    public void run() {
        if (this.this$0.mSpeakerOn) {
            this.this$0.mSpeakerIcon.setImageResource(2130837946);
            this.this$0.mSpeakerIcon.setSelected(true);
            return;
        }
        this.this$0.mSpeakerIcon.setImageResource(2130837945);
        this.this$0.mSpeakerIcon.setSelected(false);
    }
}
