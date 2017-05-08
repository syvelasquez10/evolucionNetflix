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

class DialerScreen$5 implements Runnable
{
    final /* synthetic */ DialerScreen this$0;
    final /* synthetic */ boolean val$dialPadVisible;
    
    DialerScreen$5(final DialerScreen this$0, final boolean val$dialPadVisible) {
        this.this$0 = this$0;
        this.val$dialPadVisible = val$dialPadVisible;
    }
    
    @Override
    public void run() {
        if (this.val$dialPadVisible) {
            this.this$0.mDialpadIcon.setImageResource(2130837736);
            this.this$0.mDialpadIcon.setSelected(false);
            return;
        }
        this.this$0.mDialpadIcon.setImageResource(2130837737);
        this.this$0.mDialpadIcon.setSelected(true);
    }
}
