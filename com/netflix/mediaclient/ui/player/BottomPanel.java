// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.player;

import com.netflix.mediaclient.util.ViewUtils;
import android.widget.SeekBar;
import android.view.ViewTreeObserver$OnGlobalLayoutListener;
import android.app.Dialog;
import android.view.View$OnTouchListener;
import android.widget.SeekBar$OnSeekBarChangeListener;
import android.view.View$OnClickListener;
import com.netflix.mediaclient.util.AndroidUtils;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import com.netflix.mediaclient.servicemgr.IPlayer;
import com.netflix.mediaclient.ui.Asset;
import com.netflix.mediaclient.ui.mdx.MdxTarget;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import com.netflix.mediaclient.ui.common.PlaybackLauncher;
import com.netflix.mediaclient.util.MdxUtils;
import com.netflix.mediaclient.Log;
import android.widget.AdapterView;
import android.widget.AdapterView$OnItemClickListener;
import android.content.Context;
import android.app.Activity;
import com.netflix.mediaclient.ui.mdx.MdxTargetSelectionDialog;
import android.app.AlertDialog;
import com.netflix.mediaclient.android.widget.NetflixSeekBar;
import com.netflix.mediaclient.android.widget.IconFontTextView;
import com.netflix.mediaclient.ui.mdx.MdxTargetSelection;
import android.widget.ImageButton;
import com.netflix.mediaclient.util.TimeFormatterHelper;
import android.widget.TextView;
import android.view.View;

public final class BottomPanel extends PlayerSection
{
    private static final String TAG = "screen";
    protected View bottomPanel;
    protected CurrentTime currentTime;
    protected TextView durationLabel;
    protected TimeFormatterHelper formatter;
    protected LastTime lastTime;
    private boolean mZoomEnabled;
    protected String mdxSid;
    protected ImageButton mdxTarget;
    protected MdxTargetSelection mdxTargetSelector;
    protected ImageButton media;
    protected IconFontTextView skipBack;
    protected NetflixSeekBar timeline;
    protected int timelineWasPreviouslyRendered;
    protected ImageButton zoom;
    protected View zoomDivider;
    
    public BottomPanel(final PlayerActivity playerActivity, final PlayScreen.Listeners listeners) {
        super(playerActivity);
        this.mZoomEnabled = true;
        this.formatter = new TimeFormatterHelper();
        this.init(listeners);
    }
    
    private AlertDialog createMdxTargetSelectionDialog(final PlayerActivity playerActivity) {
        final IPlayer player = playerActivity.getPlayer();
        final boolean b = player != null && player.isPlaying();
        final int localDevicePosition = this.mdxTargetSelector.getLocalDevicePosition();
        this.mdxTargetSelector.setTarget(localDevicePosition);
        final MdxTargetSelectionDialog.Builder builder = new MdxTargetSelectionDialog.Builder(playerActivity);
        builder.setCancelable(false);
        builder.setTitle(2131493087);
        builder.setAdapterData(this.mdxTargetSelector.getTargets((Context)playerActivity));
        builder.setSelection(localDevicePosition, String.format(playerActivity.getString(2131493178), playerActivity.getCurrentTitle()));
        builder.setOnItemClickListener((AdapterView$OnItemClickListener)new AdapterView$OnItemClickListener() {
            public void onItemClick(final AdapterView<?> adapterView, final View view, final int target, final long n) {
                Log.d("screen", "Mdx target clicked: item with id " + n + ", on position " + target);
                playerActivity.removeVisibleDialog();
                if (BottomPanel.this.mdxTargetSelector != null) {
                    BottomPanel.this.mdxTargetSelector.setTarget(target);
                    final MdxTarget selectedTarget = BottomPanel.this.mdxTargetSelector.getSelectedTarget();
                    if (selectedTarget == null) {
                        Log.e("screen", "Target is NULL, this should NOT happen!");
                        if (b) {
                            playerActivity.doUnpause();
                        }
                    }
                    else if (selectedTarget.isLocal()) {
                        Log.d("screen", "Target is local, same as cancel. Do nothing");
                        if (b) {
                            playerActivity.doUnpause();
                        }
                    }
                    else {
                        if (Log.isLoggable("screen", 3)) {
                            Log.d("screen", "Remote target is selected " + selectedTarget);
                        }
                        if (MdxUtils.isMdxTargetAvailable(playerActivity.getServiceManager(), selectedTarget.getUUID())) {
                            Log.d("screen", "Remote target is available, start MDX playback, use local bookmark!");
                            playerActivity.getServiceManager().getMdx().setCurrentTarget(selectedTarget.getUUID());
                            final Asset currentAsset = playerActivity.getCurrentAsset();
                            currentAsset.setPlaybackBookmark(playerActivity.getPlayer().getCurrentPositionMs() / 1000);
                            if (PlaybackLauncher.startPlayback(playerActivity, currentAsset)) {
                                playerActivity.finish();
                            }
                        }
                        else {
                            Log.w("screen", "Remote target is NOT available anymore, continue local plaback");
                            if (b) {
                                playerActivity.doUnpause();
                            }
                        }
                    }
                }
            }
        });
        return builder.create();
    }
    
    private int getThumbMiddleX() {
        final NetflixSeekBar timeline = this.timeline;
        if (this.context.getState().getTimelineExitOnSnap()) {
            if (timeline.getDentMiddleX() >= 0) {
                Log.d("screen", "Exit on snap, use x of middle of dent");
                return timeline.getDentMiddleX();
            }
            Log.w("screen", "Exit on snap, but dent x is uknown! Use regular thumb position.");
        }
        else {
            Log.d("screen", "Regular x from thumb");
        }
        final Drawable netflixThumb = timeline.getNetflixThumb();
        if (netflixThumb == null) {
            Log.e("screen", "Thumb is null!");
            return 0;
        }
        return netflixThumb.getBounds().centerX();
    }
    
    private void init(final PlayScreen.Listeners listeners) {
        this.durationLabel = (TextView)this.context.findViewById(2131099855);
        this.bottomPanel = this.context.findViewById(2131099847);
        if (this.bottomPanel == null) {
            Log.e("screen", "========>bottom null!");
        }
        this.timeline = (NetflixSeekBar)this.context.findViewById(2131099854);
        if (this.timeline != null) {
            this.timeline.setOnSeekBarChangeListener(listeners.videoPositionListener);
            this.timeline.setDentVisible(false);
            this.timeline.setScrubberDent(BitmapFactory.decodeResource(this.context.getResources(), this.context.getUiResources().timelineDent));
            this.timeline.setThumbOffset(AndroidUtils.dipToPixels((Context)this.context, this.context.getUiResources().timelineThumbOffsetInDip));
            this.timeline.setProgressBarPadding(AndroidUtils.dipToPixels((Context)this.context, this.context.getUiResources().timelineHeightPaddingInDip));
        }
        this.media = (ImageButton)this.context.findViewById(2131099891);
        if (this.media != null) {
            this.media.setOnClickListener(listeners.playPauseListener);
            this.media.setBackgroundColor(this.transpColor);
        }
        this.skipBack = (IconFontTextView)this.context.findViewById(2131099853);
        if (this.skipBack != null) {
            this.skipBack.setOnClickListener(listeners.skipBackListener);
            this.skipBack.setBackgroundColor(this.transpColor);
        }
        final View$OnClickListener onClickListener = (View$OnClickListener)new View$OnClickListener() {
            public void onClick(final View view) {
                final PlayerActivity context = BottomPanel.this.context;
                if (context != null) {
                    context.extendTimeoutTimer();
                }
                BottomPanel.this.displayMdxTargets();
            }
        };
        this.mdxTarget = (ImageButton)this.context.findViewById(2131099839);
        if (this.mdxTarget != null) {
            this.mdxTarget.setOnClickListener((View$OnClickListener)onClickListener);
            this.mdxTarget.setBackgroundColor(this.transpColor);
        }
        this.zoomDivider = this.context.findViewById(2131099892);
        this.zoom = (ImageButton)this.context.findViewById(2131099893);
        if (this.zoom != null) {
            this.zoom.setOnClickListener(listeners.zoomListener);
            this.zoom.setBackgroundColor(this.transpColor);
        }
        this.currentTime = CurrentTime.newInstance(this.context);
        this.lastTime = new LastTime(this.context);
    }
    
    private boolean isMdxTargetSelectionVisible() {
        return this.isMdxTargetSelectionVisible(this.mdxTargetSelector);
    }
    
    private boolean isMdxTargetSelectionVisible(final MdxTargetSelection mdxTargetSelection) {
        return mdxTargetSelection != null && mdxTargetSelection.getMdxTargets() != null && mdxTargetSelection.getMdxTargets().length > 1;
    }
    
    private boolean isVisible() {
        boolean b = false;
        if (this.bottomPanel != null) {
            b = b;
            if (this.bottomPanel.getVisibility() == 0) {
                b = true;
            }
        }
        return b;
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
        if (this.media != null) {
            this.media.setOnTouchListener((View$OnTouchListener)null);
        }
        if (this.skipBack != null) {
            this.skipBack.setOnTouchListener((View$OnTouchListener)null);
        }
        if (this.mdxTarget != null) {
            this.mdxTarget.setOnTouchListener((View$OnTouchListener)null);
        }
        if (this.zoom != null) {
            this.zoom.setOnTouchListener((View$OnTouchListener)null);
        }
    }
    
    protected void displayMdxTargets() {
        if (this.mdxTargetSelector == null || this.mdxTargetSelector.getMdxTargets() == null || this.mdxTargetSelector.getMdxTargets().length < 2) {
            Log.d("screen", "Non local targets are not available!");
        }
        else {
            final PlayerActivity context = this.context;
            if (context != null) {
                Log.d("screen", "MDX target is reachable, display dialog");
                context.displayDialog((Dialog)this.createMdxTargetSelectionDialog(context));
            }
        }
    }
    
    public void enableButtons(final boolean b) {
        this.enableButton((View)this.media, b);
        this.enableButton((View)this.skipBack, b);
        this.enableButton((View)this.mdxTarget, b);
        this.enableButton((View)this.zoom, b);
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
    
    public MdxTargetSelection getMdxTargetSelector() {
        return this.mdxTargetSelector;
    }
    
    public ImageButton getMedia() {
        return this.media;
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
    
    public ImageButton getZoom() {
        return this.zoom;
    }
    
    @Override
    public void hide() {
        synchronized (this) {
            if (this.media != null) {
                this.media.setVisibility(8);
            }
            if (this.skipBack != null) {
                this.skipBack.setVisibility(8);
            }
            if (this.zoom != null) {
                this.zoom.setVisibility(8);
            }
            if (this.zoomDivider != null) {
                this.zoomDivider.setVisibility(8);
            }
            if (this.mdxTarget != null && this.isMdxTargetSelectionVisible()) {
                this.mdxTarget.setVisibility(8);
            }
            if (this.currentTime != null) {
                this.currentTime.hide();
            }
            if (this.bottomPanel != null) {
                this.bottomPanel.setVisibility(8);
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
    
    public void setMdxTargetSelector(final MdxTargetSelection mdxTargetSelector) {
        while (true) {
            Label_0234: {
                final boolean mdxTargetSelectionVisible2;
                final NetflixActivity netflixActivity;
                Label_0194: {
                    Label_0170: {
                        synchronized (this) {
                            final MdxTargetSelection mdxTargetSelector2 = this.mdxTargetSelector;
                            this.mdxTargetSelector = mdxTargetSelector;
                            final boolean mdxTargetSelectionVisible = this.isMdxTargetSelectionVisible(mdxTargetSelector2);
                            mdxTargetSelectionVisible2 = this.isMdxTargetSelectionVisible();
                            final boolean visible = this.isVisible();
                            if (Log.isLoggable("screen", 3)) {
                                Log.d("screen", "Bottom panel is visible: " + visible);
                                Log.d("screen", "MDX target is currently visible: " + mdxTargetSelectionVisible);
                                Log.d("screen", "MDX target whould be visible: " + mdxTargetSelectionVisible2);
                            }
                            if (visible) {
                                if (this.context == null) {
                                    Log.w("screen", "Player activity was destroyed, do nothing");
                                }
                                else {
                                    if (!mdxTargetSelectionVisible) {
                                        break Label_0194;
                                    }
                                    if (!mdxTargetSelectionVisible2) {
                                        break Label_0170;
                                    }
                                    Log.d("screen", "Panel is visible. MDX target was visible and it should be visible. Do nothing.");
                                }
                                return;
                            }
                            break Label_0234;
                        }
                    }
                    Log.d("screen", "Panel is visible. MDX target was visible and it should NOT be visible. Remove it!");
                    netflixActivity.runInUiThread(new Runnable() {
                        @Override
                        public void run() {
                            synchronized (BottomPanel.this) {
                                if (BottomPanel.this.mdxTarget != null) {
                                    BottomPanel.this.mdxTarget.setVisibility(8);
                                }
                            }
                        }
                    });
                    return;
                }
                if (mdxTargetSelectionVisible2) {
                    Log.d("screen", "Panel is visible. MDX target was NOT visible and it should be visible. Add it!");
                    netflixActivity.runInUiThread(new Runnable() {
                        @Override
                        public void run() {
                            synchronized (BottomPanel.this) {
                                if (BottomPanel.this.mdxTarget != null) {
                                    BottomPanel.this.mdxTarget.setVisibility(0);
                                }
                            }
                        }
                    });
                    return;
                }
                Log.d("screen", "Panel is visible. MDX target was NOT visible and it should NOT be visible. Do nothing.");
                return;
            }
            Log.d("screen", "Panel is NOT visible. Do nothing.");
        }
    }
    
    public int setProgress(final int progress, final int n, final boolean b, final boolean b2) {
        if (Log.isLoggable("screen", 3)) {
            Log.d("screen", "SetProgress: pos " + progress + ", duration " + n + ", updateSeekBar " + b + ", forceUpdate " + b2);
        }
        if (!b2 && (this.media == null || !this.media.isEnabled())) {
            Log.d("screen", "Ignoring setProgress");
            return 0;
        }
        if (this.timeline != null && b) {
            this.timeline.setProgress(progress);
        }
        if (this.durationLabel != null && n > 0) {
            this.durationLabel.setText((CharSequence)this.formatter.getStringForMs(n - progress));
        }
        this.updateText("screen", this.currentTime.getCurrentTimeLabel(), "currentTimeLabel", this.formatter.getStringForMs(progress));
        return progress;
    }
    
    public void setZoomEnabled(final boolean mZoomEnabled) {
        this.mZoomEnabled = mZoomEnabled;
    }
    
    @Override
    public void show() {
        while (true) {
            synchronized (this) {
                if (this.media != null) {
                    this.media.setVisibility(0);
                }
                if (this.skipBack != null) {
                    this.skipBack.setVisibility(0);
                }
                if (this.mdxTarget != null && this.isMdxTargetSelectionVisible()) {
                    this.mdxTarget.setVisibility(0);
                }
                if (this.bottomPanel != null) {
                    this.bottomPanel.setVisibility(0);
                }
                if (this.zoom != null) {
                    if (this.mZoomEnabled) {
                        this.zoom.setVisibility(0);
                        if (this.zoomDivider != null) {
                            this.zoomDivider.setVisibility(0);
                        }
                    }
                    else {
                        this.zoom.setVisibility(8);
                        if (this.zoomDivider != null) {
                            this.zoomDivider.setVisibility(8);
                        }
                    }
                }
                if (this.timelineWasPreviouslyRendered > 0) {
                    Log.d("screen", "Timeline was visible before, its location is known, render current time now");
                    this.currentTime.move(true, true);
                    return;
                }
            }
            Log.d("screen", "Timeline was NOT visible before, its location is NOT known, delay until is rendered first time");
            final NetflixSeekBar timeline = this.timeline;
            timeline.getViewTreeObserver().addOnGlobalLayoutListener((ViewTreeObserver$OnGlobalLayoutListener)new ViewTreeObserver$OnGlobalLayoutListener() {
                public void onGlobalLayout() {
                    Log.d("screen", "Timeline is visible now, its location is known, render current time now!!! <==");
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
    
    public void snapToPosition(final int n, final int n2) {
        if (Log.isLoggable("screen", 3)) {
            Log.d("screen", "snapToPosition: pos " + n);
        }
        if (this.timeline != null) {
            Log.d("screen", "snapToPosition: snap now!");
            this.timeline.snapToLastTime();
        }
        else {
            Log.e("screen", "snapToPosition: timeline null?!");
        }
        if (this.durationLabel != null && n2 > 0) {
            this.durationLabel.setText((CharSequence)this.formatter.getStringForMs(n2 - n));
        }
        this.updateText("screen", this.currentTime.getCurrentTimeLabel(), "currentTimeLabel", this.formatter.getStringForMs(n));
    }
}
