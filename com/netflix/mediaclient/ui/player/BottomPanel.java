// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.player;

import android.view.ViewTreeObserver$OnGlobalLayoutListener;
import android.widget.SeekBar;
import com.netflix.mediaclient.Log;
import android.animation.TimeInterpolator;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.animation.Animator$AnimatorListener;
import android.animation.ValueAnimator$AnimatorUpdateListener;
import android.animation.ValueAnimator;
import com.netflix.mediaclient.util.gfx.AnimationUtils;
import com.netflix.mediaclient.android.widget.NetflixSeekBar;
import android.widget.RelativeLayout$LayoutParams;
import com.netflix.mediaclient.util.ViewUtils;
import android.view.View$OnTouchListener;
import android.widget.SeekBar$OnSeekBarChangeListener;
import android.content.Context;
import com.netflix.mediaclient.util.AndroidUtils;
import com.netflix.mediaclient.android.widget.TimelineSeekBar;
import android.widget.ImageButton;
import com.netflix.mediaclient.util.TimeFormatterHelper;
import android.widget.TextView;
import android.view.View;

public final class BottomPanel extends PlayerSection
{
    private static final int EXTRA_HANDLER_ANIMATION_MS = 200;
    private static final String TAG = "screen";
    private View bottomGradient;
    protected View bottomPanel;
    protected CurrentTime currentTime;
    private int currentTimeProgress;
    protected TextView durationLabel;
    private View extraSeekbarHandler;
    protected TimeFormatterHelper formatter;
    private boolean mZoomEnabled;
    protected String mdxSid;
    protected ImageButton media;
    protected ImageButton skipBack;
    protected TimelineSeekBar timeline;
    protected int timelineWasPreviouslyRendered;
    protected ImageButton zoom;
    
    public BottomPanel(final PlayerActivity playerActivity, final PlayScreen$Listeners playScreen$Listeners) {
        super(playerActivity);
        this.mZoomEnabled = true;
        this.formatter = new TimeFormatterHelper();
        this.init(playScreen$Listeners);
    }
    
    private void init(final PlayScreen$Listeners playScreen$Listeners) {
        this.durationLabel = (TextView)this.context.findViewById(2131165582);
        this.bottomPanel = this.context.findViewById(2131165578);
        this.bottomGradient = this.context.findViewById(2131165574);
        (this.timeline = (TimelineSeekBar)this.context.findViewById(2131165581)).setOnSeekBarChangeListener(playScreen$Listeners.videoPositionListener);
        this.timeline.setThumbOffset(AndroidUtils.dipToPixels((Context)this.context, this.context.getUiResources().timelineThumbOffsetInDip));
        (this.media = (ImageButton)this.context.findViewById(2131165579)).setOnClickListener(playScreen$Listeners.playPauseListener);
        (this.skipBack = (ImageButton)this.context.findViewById(2131165580)).setOnClickListener(playScreen$Listeners.skipBackListener);
        (this.zoom = (ImageButton)this.context.findViewById(2131165583)).setOnClickListener(playScreen$Listeners.zoomListener);
        this.extraSeekbarHandler = this.context.findViewById(2131165592);
        this.currentTime = CurrentTime.newInstance(this.context);
    }
    
    public int calculateTimelineMargin(final int n) {
        final int[] array = new int[2];
        this.timeline.getLocationOnScreen(array);
        return (int)(array[0] + this.timeline.getPaddingLeft() + this.timeline.computeXOffsetFromProgress(n));
    }
    
    @Override
    public void changeActionState(final boolean b) {
        this.changeActionState(b, true);
    }
    
    public void changeActionState(final boolean enabled, final boolean b) {
        this.enableButtons(enabled);
        if (b) {
            this.timeline.setEnabled(enabled);
        }
        int n;
        if (enabled) {
            n = 2131296360;
        }
        else {
            n = 2131296373;
        }
        this.durationLabel.setTextColor(this.context.getResources().getColor(n));
    }
    
    @Override
    public void destroy() {
        if (this.timeline != null) {
            this.timeline.setOnSeekBarChangeListener(null);
        }
        if (this.media != null) {
            this.media.setOnTouchListener((View$OnTouchListener)null);
        }
        if (this.skipBack != null) {
            this.skipBack.setOnTouchListener((View$OnTouchListener)null);
        }
        if (this.zoom != null) {
            this.zoom.setOnTouchListener((View$OnTouchListener)null);
        }
    }
    
    public void enableButtons(final boolean b) {
        this.enableButton((View)this.media, b);
        this.enableButton((View)this.skipBack, b);
        this.enableButton((View)this.zoom, b);
    }
    
    public void finishDragging() {
        ViewUtils.setVisibleOrInvisible(this.extraSeekbarHandler, false);
    }
    
    public int getCurrentProgress() {
        return this.currentTimeProgress;
    }
    
    public CurrentTime getCurrentTime() {
        return this.currentTime;
    }
    
    public TimeFormatterHelper getFormatter() {
        return this.formatter;
    }
    
    public int getTimeXAndUpdateHandler(final View view) {
        final int calculateTimelineMargin = this.calculateTimelineMargin(this.currentTimeProgress);
        ((RelativeLayout$LayoutParams)this.extraSeekbarHandler.getLayoutParams()).leftMargin = calculateTimelineMargin - this.extraSeekbarHandler.getWidth() / 2;
        view.measure(0, 0);
        return calculateTimelineMargin - view.getMeasuredWidth() / 2;
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
            AnimationUtils.startViewAppearanceAnimation(this.bottomPanel, false);
            AnimationUtils.startViewAppearanceAnimation(this.bottomGradient, false);
            this.finishDragging();
        }
    }
    
    public void initProgress(final int max) {
        if (this.timeline != null) {
            this.timeline.setMax(max);
        }
    }
    
    public void playExtraHandlerAnimation(int calculateTimelineMargin, final Runnable runnable) {
        final int leftMargin = ((RelativeLayout$LayoutParams)this.extraSeekbarHandler.getLayoutParams()).leftMargin;
        calculateTimelineMargin = this.calculateTimelineMargin(calculateTimelineMargin);
        final int n = this.extraSeekbarHandler.getWidth() / 2;
        final RelativeLayout$LayoutParams relativeLayout$LayoutParams = (RelativeLayout$LayoutParams)this.extraSeekbarHandler.getLayoutParams();
        final ValueAnimator ofInt = ValueAnimator.ofInt(new int[] { leftMargin, calculateTimelineMargin - n });
        ofInt.addUpdateListener((ValueAnimator$AnimatorUpdateListener)new BottomPanel$2(this, relativeLayout$LayoutParams));
        ofInt.addListener((Animator$AnimatorListener)new BottomPanel$3(this, runnable));
        ofInt.setInterpolator((TimeInterpolator)new AccelerateDecelerateInterpolator());
        ofInt.setDuration(200L);
        ofInt.start();
    }
    
    public void setMediaImage(final boolean b) {
        final ImageButton media = this.media;
        int imageResource;
        if (b) {
            imageResource = this.context.getUiResources().play;
        }
        else {
            imageResource = this.context.getUiResources().pause;
        }
        media.setImageResource(imageResource);
    }
    
    public int setProgress(final int n, int n2, final boolean b, final boolean b2) {
        if (Log.isLoggable()) {
            Log.d("screen", "SetProgress: pos " + n + ", duration " + n2 + ", updateSeekBar " + b + ", forceUpdate " + b2);
        }
        if (!b2 && (this.media == null || !this.media.isEnabled())) {
            Log.d("screen", "Ignoring setProgress");
            n2 = 0;
        }
        else {
            if (this.timeline != null && b) {
                this.timeline.setProgress(n);
            }
            if (this.durationLabel != null && n2 > 0) {
                this.durationLabel.setText((CharSequence)this.formatter.getStringForMs(n2 - n));
            }
            this.currentTimeProgress = n;
            n2 = n;
            if (this.bottomPanel.getAlpha() > 0.0f) {
                this.currentTime.updateCurrentTime();
                return n;
            }
        }
        return n2;
    }
    
    public void setZoomEnabled(final boolean mZoomEnabled) {
        this.mZoomEnabled = mZoomEnabled;
    }
    
    public void setZoomImage(final boolean b) {
        final ImageButton zoom = this.zoom;
        int imageResource;
        if (b) {
            imageResource = this.context.getUiResources().zoomIn;
        }
        else {
            imageResource = this.context.getUiResources().zoomOut;
        }
        zoom.setImageResource(imageResource);
    }
    
    @Override
    public void show() {
        synchronized (this) {
            if (this.currentTime != null) {
                this.currentTime.show();
            }
            AnimationUtils.startViewAppearanceAnimation(this.bottomPanel, true);
            AnimationUtils.startViewAppearanceAnimation(this.bottomGradient, true);
            ViewUtils.setVisibleOrGone((View)this.zoom, this.mZoomEnabled);
            this.currentTime.updateCurrentTime();
            if (this.timelineWasPreviouslyRendered <= 0) {
                Log.d("screen", "Timeline was NOT visible before, its location is NOT known, delay until is rendered first time");
                final TimelineSeekBar timeline = this.timeline;
                timeline.getViewTreeObserver().addOnGlobalLayoutListener((ViewTreeObserver$OnGlobalLayoutListener)new BottomPanel$1(this, timeline));
            }
        }
    }
    
    public void startDragging() {
        ViewUtils.setVisibleOrInvisible(this.extraSeekbarHandler, true);
    }
}
