// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.mdx;

import com.netflix.mediaclient.Log;
import java.nio.ByteBuffer;
import java.util.concurrent.atomic.AtomicBoolean;
import android.widget.TextView;
import android.view.View;
import android.widget.ImageView;

public abstract class CurrentTime extends PlaycardSection
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
    
    CurrentTime(final MdxPlaycardActivity mdxPlaycardActivity) {
        super(mdxPlaycardActivity);
        this.mBifDownloaded = new AtomicBoolean(false);
        this.currentTime = mdxPlaycardActivity.findViewById(2131230998);
        this.currentTimeExp = mdxPlaycardActivity.findViewById(2131231006);
        this.currentTimeLabel = (TextView)mdxPlaycardActivity.findViewById(2131230999);
        this.bifs = (ImageView)mdxPlaycardActivity.findViewById(2131230967);
    }
    
    static CurrentTime newInstance(final MdxPlaycardActivity mdxPlaycardActivity) {
        if (mdxPlaycardActivity.isTablet()) {
            return new CurrentTimeTablet(mdxPlaycardActivity);
        }
        return new CurrentTimePhone(mdxPlaycardActivity);
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
        String s;
        if (this.context.getState().getTimelineExitOnSnap()) {
            Log.d("screen", "Exit on snap, use snap position");
            s = bottomPanel.formatTime(this.context.getState().getTimelineStartSeekPosition());
            Log.d("screen", "Exit on snap, use snap position " + s);
        }
        else {
            s = bottomPanel.formatTime(bottomPanel.getTimeline().getProgress());
            Log.d("screen", "No snap position");
        }
        this.updateText("screen", this.currentTimeLabel, "currentTimeLabel", s);
    }
}
