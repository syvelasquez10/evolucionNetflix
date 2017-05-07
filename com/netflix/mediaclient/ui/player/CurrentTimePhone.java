// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.player;

import android.view.ViewGroup$LayoutParams;
import android.widget.RelativeLayout$LayoutParams;
import com.netflix.mediaclient.Log;
import java.nio.ByteBuffer;

public class CurrentTimePhone extends CurrentTime
{
    CurrentTimePhone(final PlayerFragment playerFragment) {
        super(playerFragment);
    }
    
    @Override
    public void start(final ByteBuffer byteBuffer) {
        synchronized (this) {
            this.playerFragment.getScreen().startBif(byteBuffer);
            Log.d("screen", "Movie current time from startCurrentTime");
        }
    }
    
    @Override
    public void stop(final boolean b) {
        synchronized (this) {
            if (Log.isLoggable()) {
                Log.d("screen", "Movie current time from stopCurrentTime, phone: " + b);
            }
            this.restorePlaybackIfSnapOnExit();
        }
    }
    
    @Override
    public void updateTimeMargins() {
        if (this.playerFragment == null || this.currentTime == null) {
            return;
        }
        final BottomPanel bottomPanel = this.playerFragment.getScreen().getBottomPanel();
        final RelativeLayout$LayoutParams layoutParams = (RelativeLayout$LayoutParams)this.currentTime.getLayoutParams();
        layoutParams.setMargins(bottomPanel.getTimeXAndUpdateHandler(this.currentTime), 0, 0, 0);
        this.currentTime.setLayoutParams((ViewGroup$LayoutParams)layoutParams);
        Log.d("screen", "moveCurrentTimeWithTimeline set layout parameter!");
    }
}
