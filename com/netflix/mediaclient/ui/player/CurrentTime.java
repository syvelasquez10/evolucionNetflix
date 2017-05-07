// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.player;

import com.netflix.mediaclient.util.TimeFormatterHelper;
import java.nio.ByteBuffer;
import com.netflix.mediaclient.Log;
import java.util.concurrent.atomic.AtomicBoolean;
import android.widget.TextView;
import android.view.View;
import android.widget.ImageView;

public abstract class CurrentTime extends PlayerSection
{
    static final int CURRENT_TIME_BOTTOM_MARGIN_REGULAR = 18;
    static final int CURRENT_TIME_BOTTOM_MARGIN_REGULAR_PHONE = 10;
    static final int CURRENT_TIME_BOTTOM_MARGIN_TABLET_PRESSED = 40;
    protected static final String TAG = "screen";
    protected ImageView bifs;
    protected View currentTime;
    protected boolean currentTimeAnimationInProgress;
    protected View currentTimeExp;
    protected TextView currentTimeLabel;
    protected AtomicBoolean mBifDownloaded;
    
    CurrentTime(final PlayerActivity playerActivity) {
        super(playerActivity);
        this.mBifDownloaded = new AtomicBoolean(false);
        this.currentTime = playerActivity.findViewById(2131165561);
        this.currentTimeExp = playerActivity.findViewById(2131165569);
        this.currentTimeLabel = (TextView)playerActivity.findViewById(2131165562);
        this.bifs = (ImageView)playerActivity.findViewById(2131165535);
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
    
    public TextView getCurrentTimeLabel() {
        return this.currentTimeLabel;
    }
    
    @Override
    public void hide() {
        if (this.currentTime != null) {
            Log.d("SP", "Hide");
            this.currentTime.clearAnimation();
            this.currentTime.setVisibility(8);
        }
    }
    
    public boolean isBifDownloaded() {
        return this.mBifDownloaded.get();
    }
    
    public abstract void move(final boolean p0, final boolean p1);
    
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
        if (this.currentTime != null) {
            this.currentTime.setVisibility(0);
        }
    }
    
    public abstract void start(final ByteBuffer p0);
    
    public abstract void stop(final boolean p0);
    
    protected void updateCurrentTime() {
        final BottomPanel bottomPanel = this.context.getScreen().getBottomPanel();
        final TimeFormatterHelper formatter = bottomPanel.getFormatter();
        String s;
        if (this.context.getState().getTimelineExitOnSnap()) {
            Log.d("screen", "Exit on snap, use snap position");
            s = formatter.getStringForMs(this.context.getState().getTimelineStartSeekPosition());
            Log.d("screen", "Exit on snap, use snap position " + s);
        }
        else {
            s = formatter.getStringForMs(bottomPanel.getTimeline().getProgress());
            Log.d("screen", "No snap position");
        }
        this.updateText("screen", this.currentTimeLabel, "currentTimeLabel", s);
    }
}
