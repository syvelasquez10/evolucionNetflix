// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.player;

import android.view.View;
import com.netflix.mediaclient.util.ViewUtils;
import com.netflix.mediaclient.Log;
import android.widget.SeekBar;
import android.view.ViewTreeObserver$OnGlobalLayoutListener;

class BottomPanel$3 implements ViewTreeObserver$OnGlobalLayoutListener
{
    final /* synthetic */ BottomPanel this$0;
    final /* synthetic */ SeekBar val$fTimeline;
    
    BottomPanel$3(final BottomPanel this$0, final SeekBar val$fTimeline) {
        this.this$0 = this$0;
        this.val$fTimeline = val$fTimeline;
    }
    
    public void onGlobalLayout() {
        Log.d("screen", "Timeline is visible now, its location is known, render current time now!!! <==");
        if (this.this$0.timelineWasPreviouslyRendered > 1) {
            ViewUtils.removeGlobalLayoutListener((View)this.val$fTimeline, (ViewTreeObserver$OnGlobalLayoutListener)this);
        }
        final BottomPanel this$0 = this.this$0;
        ++this$0.timelineWasPreviouslyRendered;
        this.this$0.currentTime.move(true, true);
    }
}
