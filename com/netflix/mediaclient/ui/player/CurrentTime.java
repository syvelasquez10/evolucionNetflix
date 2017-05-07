// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.player;

import java.nio.ByteBuffer;
import com.netflix.mediaclient.util.gfx.AnimationUtils;
import com.netflix.mediaclient.Log;
import java.util.concurrent.atomic.AtomicBoolean;
import android.widget.TextView;
import android.view.View;

public abstract class CurrentTime extends PlayerSection
{
    static final int CURRENT_TIME_BOTTOM_MARGIN_REGULAR_DP = 0;
    static final int CURRENT_TIME_BOTTOM_MARGIN_REGULAR_PHONE_DP = 0;
    static final int CURRENT_TIME_BOTTOM_MARGIN_TABLET_PRESSED_DP = 40;
    protected static final String TAG = "screen";
    protected View currentTime;
    protected TextView currentTimeLabel;
    protected AtomicBoolean mBifDownloaded;
    
    CurrentTime(final PlayerActivity playerActivity) {
        super(playerActivity);
        this.mBifDownloaded = new AtomicBoolean(false);
        this.currentTime = playerActivity.findViewById(2131165586);
        this.currentTimeLabel = (TextView)playerActivity.findViewById(2131165587);
    }
    
    static CurrentTime newInstance(final PlayerActivity playerActivity) {
        if (playerActivity.isTablet()) {
            return new CurrentTimeTablet(playerActivity);
        }
        return new CurrentTimePhone(playerActivity);
    }
    
    @Override
    public void changeActionState(final boolean b) {
    }
    
    @Override
    public void destroy() {
    }
    
    @Override
    public void hide() {
        Log.i("screen", "Hide time");
        AnimationUtils.startViewAppearanceAnimation(this.currentTime, false);
    }
    
    public boolean isBifDownloaded() {
        return this.mBifDownloaded.get();
    }
    
    protected void restorePlaybackIfSnapOnExit() {
        if (this.context.getState().getTimelineExitOnSnap()) {
            this.context.restorePlaybackAfterSnap();
            this.context.getState().setDraggingInProgress(false);
            if (!this.context.isTablet()) {
                this.context.getScreen().stopBif();
            }
        }
        this.context.getState().setTimelineExitOnSnap(false);
    }
    
    public void setBifDownloaded(final boolean b) {
        this.mBifDownloaded.set(b);
    }
    
    @Override
    public void show() {
        Log.i("screen", "Show time");
        AnimationUtils.startViewAppearanceAnimation(this.currentTime, true);
    }
    
    public abstract void start(final ByteBuffer p0);
    
    public abstract void stop(final boolean p0);
    
    public void updateCurrentTime() {
        final BottomPanel bottomPanel = this.context.getScreen().getBottomPanel();
        this.updateText("screen", this.currentTimeLabel, "currentTimeLabel", bottomPanel.getFormatter().getStringForMs(bottomPanel.getCurrentProgress()));
        this.updateTimeMargins();
    }
    
    public abstract void updateTimeMargins();
}
