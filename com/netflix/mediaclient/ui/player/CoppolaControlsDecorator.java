// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.player;

import android.content.res.Configuration;
import android.os.Build$VERSION;
import android.provider.Settings$System;
import android.os.Handler;
import android.view.View$OnClickListener;
import android.view.ViewGroup$MarginLayoutParams;
import com.netflix.mediaclient.util.DeviceUtils;
import android.app.Activity;
import android.media.AudioManager;
import com.netflix.mediaclient.util.gfx.AnimationUtils;
import android.content.Context;
import com.netflix.mediaclient.util.CoppolaUtils;
import com.netflix.mediaclient.util.ViewUtils;
import android.view.ViewGroup;
import android.database.ContentObserver;
import com.netflix.mediaclient.android.widget.TimelineSeekBar;
import android.view.OrientationEventListener;
import android.widget.ImageButton;
import android.view.View;

public class CoppolaControlsDecorator extends PlayScreenDecorator
{
    private View bottomGradient;
    private int currentDeviceVolume;
    private int currentOrientation;
    private View extraSeekbarHandler;
    private ImageButton muteButton;
    private ImageButton orientationButton;
    private OrientationEventListener orientationEventListener;
    private ImageButton pausePlayButton;
    private TimelineSeekBar timeline;
    private View topGradient;
    private int userVolumeValue;
    private ContentObserver volumeObserver;
    
    public CoppolaControlsDecorator(final PlayScreen playScreen) {
        super(playScreen);
        this.updateUI((ViewGroup)this.getController().getView());
    }
    
    public CoppolaControlsDecorator(final PlayScreenDecorator playScreenDecorator) {
        super(playScreenDecorator);
        this.updateUI((ViewGroup)this.getController().getView());
    }
    
    private void changePlayerOverlayVisibility(final boolean b) {
        boolean b2 = b;
        if (this.currentOrientation == 1) {
            b2 = b;
            if (!this.getController().getPlayer().isPlaying()) {
                if (ViewUtils.isVisible((View)this.pausePlayButton) && ViewUtils.isVisible((View)this.timeline)) {
                    return;
                }
                b2 = true;
            }
        }
        if (this.currentOrientation == 2) {
            this.topGradient.setVisibility(8);
            this.bottomGradient.setVisibility(8);
            this.muteButton.setVisibility(8);
            this.timeline.setVisibility(4);
            if (!CoppolaUtils.isAudioOn((Context)this.getController().getActivity()) && this.currentVolumeIsMuted()) {
                this.setDeviceStreamVolume(this.userVolumeValue);
            }
        }
        else {
            AnimationUtils.startViewAppearanceAnimation((View)this.muteButton, b2);
            AnimationUtils.startViewAppearanceAnimation((View)this.timeline, b2);
            AnimationUtils.startViewAppearanceAnimation(this.topGradient, b2);
            AnimationUtils.startViewAppearanceAnimation(this.bottomGradient, b2);
        }
        AnimationUtils.startViewAppearanceAnimation((View)this.pausePlayButton, b2);
        AnimationUtils.startViewAppearanceAnimation((View)this.orientationButton, b2);
        if (b2) {
            this.updateDefaultPlayerControls();
        }
    }
    
    private boolean currentVolumeIsMuted() {
        return this.currentDeviceVolume < 1;
    }
    
    private void setDeviceStreamVolume(final int n) {
        final Activity activity = this.getController().getActivity();
        if (activity != null) {
            ((AudioManager)activity.getSystemService("audio")).setStreamVolume(3, n, 0);
        }
    }
    
    private void subscribeForSensorUpdates() {
        if (this.orientationEventListener == null) {
            this.orientationEventListener = new CoppolaControlsDecorator$4(this, (Context)this.getController().getActivity());
        }
        this.orientationEventListener.enable();
    }
    
    private void updateButtonsMargins() {
        final int n = 0;
        final ImageButton orientationButton = this.orientationButton;
        int imageResource;
        if (this.currentOrientation == 1) {
            imageResource = this.getController().getUiResources().zoomIn;
        }
        else {
            imageResource = this.getController().getUiResources().zoomOut;
        }
        orientationButton.setImageResource(imageResource);
        int navigationBarHeight = n;
        if (this.currentOrientation == 2) {
            navigationBarHeight = n;
            if (!DeviceUtils.hasHardwareNavigationKeys()) {
                navigationBarHeight = ViewUtils.getNavigationBarHeight((Context)this.getController().getActivity(), false);
            }
        }
        ((ViewGroup$MarginLayoutParams)this.orientationButton.getLayoutParams()).rightMargin = navigationBarHeight;
        ((ViewGroup$MarginLayoutParams)this.pausePlayButton.getLayoutParams()).rightMargin = navigationBarHeight / 2;
        this.updateDefaultPlayerControls();
    }
    
    private void updateDefaultPlayerControls() {
        this.playerScreen.setPlayPauseVisibility(8);
        this.playerScreen.setZoomVisibility(4);
    }
    
    private void updateMuteDrawable() {
        this.updateMuteDrawable(this.currentVolumeIsMuted());
    }
    
    private void updateMuteDrawable(final boolean b) {
        if (this.muteButton != null) {
            if (!b) {
                this.muteButton.setImageResource(2130837774);
                return;
            }
            this.muteButton.setImageResource(2130837718);
        }
    }
    
    private void updateUI(final ViewGroup viewGroup) {
        this.getController().getActivity().getLayoutInflater().inflate(2130903201, viewGroup);
        this.topGradient = viewGroup.findViewById(2131624483);
        this.bottomGradient = viewGroup.findViewById(2131624484);
        (this.pausePlayButton = (ImageButton)viewGroup.findViewById(2131624485)).setOnClickListener((View$OnClickListener)new CoppolaControlsDecorator$1(this));
        this.currentOrientation = this.getController().getActivity().getResources().getConfiguration().orientation;
        (this.orientationButton = (ImageButton)viewGroup.findViewById(2131624487)).setOnClickListener((View$OnClickListener)new CoppolaControlsDecorator$2(this));
        this.updateUserVolume();
        if (!CoppolaUtils.isAudioOn((Context)this.getController().getActivity()) || CoppolaUtils.didUserMutePlayback(this.getController().getActivity())) {
            this.setDeviceStreamVolume(0);
        }
        (this.muteButton = (ImageButton)viewGroup.findViewById(2131624488)).setOnClickListener((View$OnClickListener)new CoppolaControlsDecorator$3(this));
        this.updateMuteDrawable();
        this.volumeObserver = new CoppolaControlsDecorator$VolumeContentObserver(this, (Context)this.getController().getActivity(), new Handler());
        this.getController().getActivity().getApplicationContext().getContentResolver().registerContentObserver(Settings$System.CONTENT_URI, true, this.volumeObserver);
        (this.timeline = (TimelineSeekBar)viewGroup.findViewById(2131624486)).setOnSeekBarChangeListener(this.getPlayScreen().getListeners().videoPositionListener);
        this.extraSeekbarHandler = viewGroup.findViewById(2131624489);
        this.updateButtonsMargins();
    }
    
    private void updateUserVolume() {
        final Activity activity = this.getController().getActivity();
        if (activity != null) {
            final AudioManager audioManager = (AudioManager)activity.getSystemService("audio");
            if (Build$VERSION.SDK_INT >= 23 && audioManager.isStreamMute(3)) {
                audioManager.setStreamMute(3, false);
                this.currentDeviceVolume = audioManager.getStreamVolume(3);
                audioManager.setStreamMute(3, true);
            }
            else {
                this.currentDeviceVolume = audioManager.getStreamVolume(3);
            }
            if (this.currentDeviceVolume > 0) {
                this.userVolumeValue = this.currentDeviceVolume;
            }
        }
    }
    
    @Override
    public void onConfigurationChanged(final Configuration configuration) {
        super.onConfigurationChanged(configuration);
        this.currentOrientation = configuration.orientation;
        this.changePlayerOverlayVisibility(false);
        this.updateButtonsMargins();
        this.updateUserVolume();
        this.updateMuteDrawable();
    }
    
    @Override
    public void onDestroy() {
        super.onDestroy();
        if (this.orientationEventListener != null) {
            this.orientationEventListener.disable();
        }
        this.getController().getActivity().getApplicationContext().getContentResolver().unregisterContentObserver(this.volumeObserver);
        CoppolaUtils.setUserMutePlayback(this.getController().getActivity(), this.currentVolumeIsMuted());
        this.setDeviceStreamVolume(this.userVolumeValue);
    }
    
    @Override
    public void playExtraHandlerAnimation(final int n, final Runnable runnable) {
        super.playExtraHandlerAnimation(n, runnable);
        BottomPanel.playExtraHandlerAnimation(n, runnable, null, this.extraSeekbarHandler, this.timeline);
    }
    
    @Override
    public void playerOverlayVisibility(final boolean b) {
        super.playerOverlayVisibility(b);
        this.changePlayerOverlayVisibility(b);
    }
    
    @Override
    public void setDraggingState(final boolean draggingState) {
        boolean b = false;
        super.setDraggingState(draggingState);
        if (this.getController().isInPortrait()) {
            final View extraSeekbarHandler = this.extraSeekbarHandler;
            int visibility;
            if (draggingState) {
                visibility = 0;
            }
            else {
                visibility = 4;
            }
            extraSeekbarHandler.setVisibility(visibility);
        }
        final ImageButton pausePlayButton = this.pausePlayButton;
        if (!draggingState) {
            b = true;
        }
        AnimationUtils.startViewAppearanceAnimation((View)pausePlayButton, b);
    }
    
    @Override
    public void setSeekbarEnabled(final boolean b) {
        super.setSeekbarEnabled(b);
        this.timeline.setEnabled(b);
    }
    
    @Override
    public void setTimelineMaxProgress(final int n) {
        super.setTimelineMaxProgress(n);
        this.timeline.setMax(n);
    }
    
    @Override
    public void setTimelineProgress(int calculateTimelineMargin, final boolean b) {
        super.setTimelineProgress(calculateTimelineMargin, b);
        if (this.getController().isInPortrait()) {
            if (!b) {
                calculateTimelineMargin = BottomPanel.calculateTimelineMargin(this.timeline, calculateTimelineMargin);
                ((ViewGroup$MarginLayoutParams)this.extraSeekbarHandler.getLayoutParams()).leftMargin = calculateTimelineMargin - this.extraSeekbarHandler.getWidth() / 2;
                this.extraSeekbarHandler.getParent().requestLayout();
                return;
            }
            this.timeline.setProgress(calculateTimelineMargin);
        }
    }
    
    @Override
    public void updatePlaybackStatus(final boolean b) {
        if (b) {
            this.pausePlayButton.setImageResource(2130837725);
            return;
        }
        this.pausePlayButton.setImageResource(2130837722);
    }
}
