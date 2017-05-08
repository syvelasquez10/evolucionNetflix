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
    
    CurrentTime(final PlayerFragment playerFragment) {
        super(playerFragment);
        this.mBifDownloaded = new AtomicBoolean(false);
        this.currentTime = playerFragment.getView().findViewById(2131624510);
        this.currentTimeLabel = (TextView)playerFragment.getView().findViewById(2131624511);
    }
    
    static CurrentTime newInstance(final PlayerFragment playerFragment) {
        if (playerFragment.getNetflixActivity().isTablet()) {
            return new CurrentTimeTablet(playerFragment);
        }
        return new CurrentTimePhone(playerFragment);
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
        if (this.playerFragment.getState().getTimelineExitOnSnap()) {
            this.playerFragment.restorePlaybackAfterSnap();
            this.playerFragment.getState().setDraggingInProgress(false);
            if (!this.playerFragment.getNetflixActivity().isTablet()) {
                this.playerFragment.getScreen().stopBif();
            }
        }
        this.playerFragment.getState().setTimelineExitOnSnap(false);
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
        this.updateText("screen", this.currentTimeLabel, "currentTimeLabel", this.playerFragment.getScreen().getTimeStringForMs());
        this.updateTimeMargins();
    }
    
    public abstract void updateTimeMargins();
}
