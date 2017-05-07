// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.mdx;

import java.nio.ByteBuffer;
import android.view.View;
import android.view.ViewGroup$LayoutParams;
import android.content.Context;
import com.netflix.mediaclient.util.AndroidUtils;
import android.widget.RelativeLayout$LayoutParams;
import com.netflix.mediaclient.Log;

public class CurrentTimePhone extends CurrentTime
{
    CurrentTimePhone(final MdxPlaycardActivity mdxPlaycardActivity) {
        super(mdxPlaycardActivity);
    }
    
    @Override
    public void move(final boolean b, final boolean b2) {
        synchronized (this) {
            if (this.currentTimeAnimationInProgress) {
                Log.d("screen", "Current time animation in progress. Ignore!");
            }
            else {
                final MdxPlaycardActivity context = this.context;
                if (context != null && this.currentTime != null) {
                    if (Log.isLoggable("screen", 3)) {
                        Log.d("screen", "moveCurrentTimeWithTimeline start by margin: " + 10);
                    }
                    final BottomPanel bottomPanel = this.context.getScreen().getBottomPanel();
                    final RelativeLayout$LayoutParams layoutParams = (RelativeLayout$LayoutParams)this.currentTime.getLayoutParams();
                    layoutParams.setMargins(bottomPanel.getTimeX(this.currentTime), 0, 0, AndroidUtils.dipToPixels((Context)context, 10));
                    if (this.currentTime.getVisibility() != 0) {
                        this.currentTime.setVisibility(0);
                    }
                    if (b2) {
                        this.updateCurrentTime();
                    }
                    Log.d("screen", "moveCurrentTimeWithTimeline set layout parameter!");
                    this.currentTime.setLayoutParams((ViewGroup$LayoutParams)layoutParams);
                    this.currentTime.clearAnimation();
                    this.context.getScreen().getBottomPanel().getLastTime().validateTimeOverlap((View)this.currentTimeLabel);
                }
            }
        }
    }
    
    @Override
    public void start(final ByteBuffer byteBuffer) {
        synchronized (this) {
            this.context.getScreen().startBif(byteBuffer);
            Log.d("screen", "Movie current time from startCurrentTime");
            this.move(false, true);
        }
    }
    
    @Override
    public void stop(final boolean b) {
        synchronized (this) {
            if (Log.isLoggable("screen", 3)) {
                Log.d("screen", "Movie current time from stopCurrentTime, phone: " + b);
            }
            this.move(false, b);
            this.restorePlaybackIfSnapOnExit();
        }
    }
}
