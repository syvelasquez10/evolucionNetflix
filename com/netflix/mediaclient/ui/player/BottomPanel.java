// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.player;

import android.view.ViewTreeObserver$OnGlobalLayoutListener;
import android.widget.SeekBar;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.util.gfx.AnimationUtils;
import com.netflix.mediaclient.android.widget.NetflixSeekBar;
import com.netflix.mediaclient.util.ViewUtils;
import android.view.View$OnTouchListener;
import android.animation.TimeInterpolator;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.animation.Animator$AnimatorListener;
import android.animation.ValueAnimator$AnimatorUpdateListener;
import android.animation.ValueAnimator;
import android.view.ViewGroup$MarginLayoutParams;
import android.content.Context;
import com.netflix.mediaclient.util.AndroidUtils;
import android.widget.SeekBar$OnSeekBarChangeListener;
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
    private SeekBar$OnSeekBarChangeListener videoPositionListener;
    protected ImageButton zoom;
    
    public BottomPanel(final PlayerFragment playerFragment, final PlayScreen$Listeners playScreen$Listeners) {
        super(playerFragment);
        this.mZoomEnabled = true;
        this.formatter = new TimeFormatterHelper();
        this.init(playScreen$Listeners);
    }
    
    public static int calculateTimelineMargin(final TimelineSeekBar timelineSeekBar, final int n) {
        final int[] array = new int[2];
        timelineSeekBar.getLocationOnScreen(array);
        return (int)(array[0] + timelineSeekBar.getPaddingLeft() + timelineSeekBar.computeXOffsetFromProgress(n));
    }
    
    private void init(final PlayScreen$Listeners playScreen$Listeners) {
        final View view = this.playerFragment.getView();
        this.durationLabel = (TextView)view.findViewById(2131755708);
        this.bottomPanel = view.findViewById(2131755704);
        this.bottomGradient = view.findViewById(2131755699);
        this.timeline = (TimelineSeekBar)view.findViewById(2131755707);
        this.videoPositionListener = playScreen$Listeners.videoPositionListener;
        this.timeline.setThumbOffset(AndroidUtils.dipToPixels((Context)this.playerFragment.getNetflixActivity(), this.playerFragment.getUiResources().timelineThumbOffsetInDip));
        (this.media = (ImageButton)view.findViewById(2131755705)).setOnClickListener(playScreen$Listeners.playPauseListener);
        (this.skipBack = (ImageButton)view.findViewById(2131755706)).setOnClickListener(playScreen$Listeners.skipBackListener);
        (this.zoom = (ImageButton)view.findViewById(2131755709)).setOnClickListener(playScreen$Listeners.zoomListener);
        this.extraSeekbarHandler = view.findViewById(2131755716);
        this.currentTime = CurrentTime.newInstance(this.playerFragment);
    }
    
    public static void playExtraHandlerAnimation(int calculateTimelineMargin, final Runnable runnable, final CurrentTime currentTime, final View view, final TimelineSeekBar timelineSeekBar) {
        final int leftMargin = ((ViewGroup$MarginLayoutParams)view.getLayoutParams()).leftMargin;
        calculateTimelineMargin = calculateTimelineMargin(timelineSeekBar, calculateTimelineMargin);
        final int n = view.getWidth() / 2;
        final ViewGroup$MarginLayoutParams viewGroup$MarginLayoutParams = (ViewGroup$MarginLayoutParams)view.getLayoutParams();
        final ValueAnimator ofInt = ValueAnimator.ofInt(new int[] { leftMargin, calculateTimelineMargin - n });
        ofInt.addUpdateListener((ValueAnimator$AnimatorUpdateListener)new BottomPanel$2(viewGroup$MarginLayoutParams, view));
        ofInt.addListener((Animator$AnimatorListener)new BottomPanel$3(currentTime, view, runnable));
        ofInt.setInterpolator((TimeInterpolator)new AccelerateDecelerateInterpolator());
        ofInt.setDuration(200L);
        ofInt.start();
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
            n = 2131689730;
        }
        else {
            n = 2131689569;
        }
        this.durationLabel.setTextColor(this.playerFragment.getResources().getColor(n));
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
        final int calculateTimelineMargin = calculateTimelineMargin(this.timeline, this.currentTimeProgress);
        ((ViewGroup$MarginLayoutParams)this.extraSeekbarHandler.getLayoutParams()).leftMargin = calculateTimelineMargin - this.extraSeekbarHandler.getWidth() / 2;
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
    
    public void playExtraHandlerAnimation(final int n, final Runnable runnable) {
        playExtraHandlerAnimation(n, runnable, this.currentTime, this.extraSeekbarHandler, this.timeline);
    }
    
    public void setMediaImage(final boolean b) {
        final ImageButton media = this.media;
        int imageResource;
        if (b) {
            imageResource = this.playerFragment.getUiResources().play;
        }
        else {
            imageResource = this.playerFragment.getUiResources().pause;
        }
        media.setImageResource(imageResource);
    }
    
    public void setPlayPauseVisibility(final int visibility) {
        this.media.setVisibility(visibility);
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
            if (this.bottomPanel.getAlpha() > 0.0f) {
                this.currentTime.updateCurrentTime();
                return n;
            }
            n2 = n;
            if (Log.isLoggable()) {
                Log.d("screen", "setProgress: bottomPanel is not visible, aborting");
                return n;
            }
        }
        return n2;
    }
    
    public void setSeekbarTrackingEnabled(final boolean b) {
        final TimelineSeekBar timeline = this.timeline;
        SeekBar$OnSeekBarChangeListener videoPositionListener;
        if (b) {
            videoPositionListener = this.videoPositionListener;
        }
        else {
            videoPositionListener = null;
        }
        timeline.setOnSeekBarChangeListener(videoPositionListener);
    }
    
    public void setZoomEnabled(final boolean mZoomEnabled) {
        this.mZoomEnabled = mZoomEnabled;
    }
    
    public void setZoomImage(final boolean b) {
        final ImageButton zoom = this.zoom;
        int imageResource;
        if (b) {
            imageResource = this.playerFragment.getUiResources().zoomIn;
        }
        else {
            imageResource = this.playerFragment.getUiResources().zoomOut;
        }
        zoom.setImageResource(imageResource);
    }
    
    public void setZoomVisibility(final int visibility) {
        this.zoom.setVisibility(visibility);
        this.mZoomEnabled = (visibility == 0);
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
