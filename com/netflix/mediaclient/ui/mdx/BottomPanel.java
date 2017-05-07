// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.mdx;

import com.netflix.mediaclient.util.ViewUtils;
import android.widget.SeekBar;
import android.view.ViewTreeObserver$OnGlobalLayoutListener;
import android.view.View$OnTouchListener;
import android.widget.SeekBar$OnSeekBarChangeListener;
import android.content.Context;
import com.netflix.mediaclient.util.AndroidUtils;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.android.widget.NetflixSeekBar;
import android.widget.ImageButton;
import com.netflix.mediaclient.util.TimeFormatterHelper;
import android.widget.TextView;
import android.view.View;

public final class BottomPanel extends PlaycardSection
{
    private static final String TAG = "playcard";
    protected View bottomPanel;
    protected CurrentTime currentTime;
    protected TextView durationLabel;
    protected TimeFormatterHelper formatter;
    protected LastTime lastTime;
    protected ImageButton skipBack;
    protected ImageButton stopButton;
    protected NetflixSeekBar timeline;
    protected int timelineWasPreviouslyRendered;
    
    public BottomPanel(final MdxPlaycardActivity mdxPlaycardActivity, final PlaycardScreen.Listeners listeners) {
        super(mdxPlaycardActivity);
        this.formatter = new TimeFormatterHelper();
        this.init(listeners);
    }
    
    private int getThumbMiddleX() {
        final NetflixSeekBar timeline = this.timeline;
        if (this.context.getState().getTimelineExitOnSnap()) {
            if (timeline.getDentMiddleX() >= 0) {
                Log.d("playcard", "Exit on snap, use x of middle of dent");
                return timeline.getDentMiddleX();
            }
            Log.w("playcard", "Exit on snap, but dent x is uknown! Use regular thumb position.");
        }
        else {
            Log.d("playcard", "Regular x from thumb");
        }
        final Drawable netflixThumb = timeline.getNetflixThumb();
        if (netflixThumb == null) {
            Log.e("playcard", "Thumb is null!");
            return 0;
        }
        return netflixThumb.getBounds().centerX();
    }
    
    private void init(final PlaycardScreen.Listeners listeners) {
        this.durationLabel = (TextView)this.context.findViewById(2131230989);
        this.bottomPanel = this.context.findViewById(2131230981);
        if (this.bottomPanel == null) {
            Log.e("playcard", "========>bottom null!");
        }
        this.timeline = (NetflixSeekBar)this.context.findViewById(2131230988);
        if (this.timeline != null) {
            this.timeline.setOnSeekBarChangeListener(listeners.videoPositionListener);
            this.timeline.setDentVisible(false);
            this.timeline.setScrubberDent(BitmapFactory.decodeResource(this.context.getResources(), this.context.getUiResources().timelineDent));
            this.timeline.setThumbOffset(AndroidUtils.dipToPixels((Context)this.context, this.context.getUiResources().timelineThumbOffsetInDip));
            this.timeline.setProgressBarPadding(AndroidUtils.dipToPixels((Context)this.context, this.context.getUiResources().timelineHeightPaddingInDip));
        }
        this.stopButton = (ImageButton)this.context.findViewById(2131230991);
        if (this.stopButton != null) {
            this.stopButton.setOnClickListener(listeners.stopListener);
        }
        this.skipBack = (ImageButton)this.context.findViewById(2131230987);
        if (this.skipBack != null) {
            this.skipBack.setOnClickListener(listeners.skipBackListener);
            this.skipBack.setBackgroundColor(this.transpColor);
        }
        this.currentTime = CurrentTime.newInstance(this.context);
        this.lastTime = new LastTime(this.context);
    }
    
    @Override
    public void changeActionState(final boolean enabled) {
        this.enableButtons(enabled);
        if (this.timeline != null) {
            this.timeline.setEnabled(enabled);
        }
    }
    
    @Override
    public void destroy() {
        if (this.timeline != null) {
            this.timeline.setOnSeekBarChangeListener((SeekBar$OnSeekBarChangeListener)null);
        }
        if (this.stopButton != null) {
            this.stopButton.setOnTouchListener((View$OnTouchListener)null);
        }
        if (this.skipBack != null) {
            this.skipBack.setOnTouchListener((View$OnTouchListener)null);
        }
    }
    
    public void enableButtons(final boolean b) {
        this.enableButton((View)this.stopButton, b);
        this.enableButton((View)this.skipBack, b);
    }
    
    String formatTime(final int n) {
        return this.formatter.getStringForMs(n * 1000);
    }
    
    public CurrentTime getCurrentTime() {
        return this.currentTime;
    }
    
    public TimeFormatterHelper getFormatter() {
        return this.formatter;
    }
    
    public LastTime getLastTime() {
        return this.lastTime;
    }
    
    public int getTimeX(final View view) {
        final NetflixSeekBar timeline = this.timeline;
        final int[] array = new int[2];
        timeline.getLocationOnScreen(array);
        final int thumbMiddleX = this.getThumbMiddleX();
        if (thumbMiddleX <= 0) {
            return 0;
        }
        return array[0] + thumbMiddleX + timeline.getPaddingLeft() / 2 - view.getWidth() / 2;
    }
    
    public NetflixSeekBar getTimeline() {
        return this.timeline;
    }
    
    @Override
    public void hide() {
        synchronized (this) {
            if (this.currentTime != null) {
                this.currentTime.hide();
            }
            if (this.lastTime != null) {
                this.lastTime.setLastTimeState(false);
            }
        }
    }
    
    public void initProgress(final int max) {
        if (this.timeline != null) {
            this.timeline.setMax(max);
        }
    }
    
    public int setProgress(final int progress, final int n, final boolean b, final boolean b2) {
        if (Log.isLoggable("playcard", 3)) {
            Log.d("playcard", "SetProgress: pos " + progress + ", duration " + n + ", updateSeekBar " + b + ", forceUpdate " + b2);
        }
        if (!b2) {
            Log.d("playcard", "Ignoring setProgress");
            return 0;
        }
        if (this.timeline != null && b) {
            this.timeline.setProgress(progress);
        }
        if (this.durationLabel != null && n > 0) {
            this.durationLabel.setText((CharSequence)this.formatTime(n - progress));
        }
        this.updateText("playcard", this.currentTime.getCurrentTimeLabel(), "currentTimeLabel", this.formatTime(progress));
        return progress;
    }
    
    @Override
    public void show() {
        synchronized (this) {
            if (this.timelineWasPreviouslyRendered > 0) {
                Log.d("playcard", "Timeline was visible before, its location is known, render current time now");
                this.currentTime.move(true, true);
            }
            else {
                Log.d("playcard", "Timeline was NOT visible before, its location is NOT known, delay until is rendered first time");
                final NetflixSeekBar timeline = this.timeline;
                timeline.getViewTreeObserver().addOnGlobalLayoutListener((ViewTreeObserver$OnGlobalLayoutListener)new ViewTreeObserver$OnGlobalLayoutListener() {
                    public void onGlobalLayout() {
                        Log.d("playcard", "Timeline is visible now, its location is known, render current time now!!! <==");
                        if (BottomPanel.this.timelineWasPreviouslyRendered > 1) {
                            ViewUtils.removeGlobalLayoutListener((View)timeline, (ViewTreeObserver$OnGlobalLayoutListener)this);
                        }
                        final BottomPanel this$0 = BottomPanel.this;
                        ++this$0.timelineWasPreviouslyRendered;
                        BottomPanel.this.currentTime.move(true, true);
                    }
                });
            }
        }
    }
    
    public void snapToPosition(final int n, final int n2) {
        if (Log.isLoggable("playcard", 3)) {
            Log.d("playcard", "snapToPosition: pos " + n);
        }
        if (this.timeline != null) {
            Log.d("playcard", "snapToPosition: snap now!");
            this.timeline.snapToLastTime();
        }
        else {
            Log.e("playcard", "snapToPosition: timeline null?!");
        }
        if (this.durationLabel != null && n2 > 0) {
            this.durationLabel.setText((CharSequence)this.formatTime(n2 - n));
        }
        this.updateText("playcard", this.currentTime.getCurrentTimeLabel(), "currentTimeLabel", this.formatTime(n));
    }
}
