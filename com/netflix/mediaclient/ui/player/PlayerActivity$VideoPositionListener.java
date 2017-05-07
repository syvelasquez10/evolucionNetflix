// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.player;

import com.netflix.mediaclient.media.AudioSubtitleDefaultOrderInfo;
import com.netflix.mediaclient.media.AudioSource;
import com.netflix.mediaclient.media.Subtitle;
import com.netflix.mediaclient.util.LanguageChoice;
import com.netflix.mediaclient.util.log.UIViewLogUtils;
import com.netflix.mediaclient.servicemgr.UIViewLogging$UIViewCommandName;
import com.netflix.mediaclient.service.player.subtitles.SubtitleScreen;
import com.netflix.mediaclient.service.logging.client.model.DeepErrorElement;
import java.util.List;
import com.netflix.mediaclient.service.logging.client.model.ActionOnUIError;
import com.netflix.mediaclient.service.logging.client.model.RootCause;
import com.netflix.mediaclient.service.logging.client.model.UIError;
import com.netflix.mediaclient.servicemgr.IClientLogging$CompletionReason;
import android.media.AudioManager;
import android.widget.Toast;
import android.view.MenuItem;
import com.netflix.mediaclient.event.nrdp.media.Error;
import com.netflix.mediaclient.util.log.ConsolidatedLoggingUtils;
import com.netflix.mediaclient.servicemgr.UserActionLogging$CommandName;
import com.netflix.mediaclient.util.log.UserActionLogUtils;
import android.app.Activity;
import android.os.Bundle;
import android.content.res.Configuration;
import com.netflix.mediaclient.net.LogMobileType;
import com.netflix.mediaclient.servicemgr.IClientLogging$ModalView;
import android.view.Surface;
import com.netflix.mediaclient.service.logging.client.model.DataContext;
import com.netflix.mediaclient.servicemgr.ManagerStatusListener;
import com.netflix.mediaclient.ui.pin.PinDialogVault;
import com.netflix.mediaclient.ui.pin.PinDialogVault$PinInvokedFrom;
import com.netflix.mediaclient.ui.pin.PinVerifier;
import android.annotation.SuppressLint;
import android.view.TextureView;
import android.content.IntentFilter;
import com.netflix.mediaclient.util.AndroidUtils;
import android.support.v7.widget.Toolbar;
import com.netflix.mediaclient.javabridge.ui.IMedia$SubtitleProfile;
import com.netflix.mediaclient.service.configuration.SubtitleConfiguration;
import com.netflix.mediaclient.media.PlayoutMetadata;
import com.netflix.mediaclient.util.AndroidManifestUtils;
import android.os.Debug;
import com.netflix.mediaclient.util.PreferenceUtils;
import android.os.SystemClock;
import android.util.Pair;
import com.netflix.mediaclient.ui.mdx.MdxTargetSelection;
import com.netflix.mediaclient.ui.kubrick.KubrickUtils;
import com.netflix.mediaclient.servicemgr.ManagerCallback;
import com.netflix.mediaclient.servicemgr.model.Playable;
import com.netflix.mediaclient.util.ThreadUtils;
import com.netflix.mediaclient.android.widget.AlertDialogFactory;
import com.netflix.mediaclient.android.widget.AlertDialogFactory$AlertDialogDescriptor;
import com.netflix.mediaclient.event.nrdp.media.NccpError;
import com.netflix.mediaclient.event.nrdp.media.NccpActionId;
import com.netflix.mediaclient.media.PlayerType;
import com.netflix.mediaclient.util.ConnectivityUtils;
import android.view.View;
import android.view.KeyEvent;
import java.io.Serializable;
import android.content.Context;
import com.netflix.mediaclient.ui.kids.player.KidsPlayerActivity;
import com.netflix.mediaclient.service.pushnotification.MessageData;
import android.os.Parcelable;
import android.content.Intent;
import com.netflix.mediaclient.ui.common.PlayContext;
import com.netflix.mediaclient.servicemgr.model.VideoType;
import com.netflix.mediaclient.service.configuration.PlayerTypeFactory;
import com.netflix.mediaclient.servicemgr.ServiceManager;
import com.netflix.mediaclient.android.widget.TappableSurfaceView$TapListener;
import com.netflix.mediaclient.android.widget.TappableSurfaceView$SurfaceMeasureListener;
import android.view.SurfaceHolder$Callback;
import com.netflix.mediaclient.media.JPlayer.SecondSurface;
import com.netflix.mediaclient.ui.common.Social$SocialProviderCallback;
import android.view.Menu;
import com.netflix.mediaclient.servicemgr.IPlayer;
import android.content.BroadcastReceiver;
import android.os.Handler;
import com.netflix.mediaclient.ui.details.EpisodeRowView$EpisodeRowListener;
import com.netflix.mediaclient.android.fragment.NetflixDialogFrag$DialogCanceledListener;
import com.netflix.mediaclient.service.ServiceAgent$ConfigurationAgentInterface;
import com.netflix.mediaclient.ui.Asset;
import com.netflix.mediaclient.media.Language;
import android.view.View$OnClickListener;
import android.annotation.TargetApi;
import com.netflix.mediaclient.servicemgr.IPlayer$PlayerListener;
import com.netflix.mediaclient.media.JPlayer.JPlayer$JplayerListener;
import com.netflix.mediaclient.android.fragment.NetflixDialogFrag$DialogCanceledListenerProvider;
import android.media.AudioManager$OnAudioFocusChangeListener;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import android.view.MotionEvent;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.android.widget.TimelineSeekBar;
import android.widget.SeekBar;
import android.widget.SeekBar$OnSeekBarChangeListener;

public class PlayerActivity$VideoPositionListener implements SeekBar$OnSeekBarChangeListener
{
    private boolean mIsInCancelZone;
    final /* synthetic */ PlayerActivity this$0;
    
    public PlayerActivity$VideoPositionListener(final PlayerActivity this$0) {
        this.this$0 = this$0;
    }
    
    private boolean inCancelProgressZone(final SeekBar seekBar, final float n) {
        final float dimension = this.this$0.getResources().getDimension(2131362031);
        int height;
        if (this.this$0.isTablet()) {
            height = (int)(2.0f * dimension);
        }
        else {
            height = seekBar.getHeight();
        }
        return n < -dimension || n > height;
    }
    
    private boolean skipSeek(final TimelineSeekBar timelineSeekBar) {
        boolean b = true;
        if (timelineSeekBar == null) {
            return false;
        }
        if (timelineSeekBar.getProgress() == this.this$0.mScreen.getBottomPanel().getCurrentProgress()) {
            Log.d("PlayerActivity", "Back to start position after snap, do NOT seek!");
            this.this$0.mState.timelineExitOnSnap = true;
        }
        else {
            b = false;
        }
        return b;
    }
    
    public boolean handleTouchEvent(final SeekBar seekBar, final MotionEvent motionEvent, final int n) {
        boolean b = true;
        switch (motionEvent.getAction()) {
            default: {
                b = false;
                break;
            }
            case 1: {
                this.this$0.mScreen.getBottomPanel().finishDragging();
                if (this.inCancelProgressZone(seekBar, motionEvent.getY())) {
                    this.onProgressChanged(seekBar, seekBar.getProgress(), true);
                    this.this$0.mState.timelineExitOnSnap = true;
                }
                this.onStopTrackingTouch(seekBar);
                return true;
            }
            case 0: {
                this.mIsInCancelZone = false;
                this.this$0.mScreen.getBottomPanel().startDragging();
                this.onStartTrackingTouch(seekBar);
                this.onProgressChanged(seekBar, n, true);
                return true;
            }
            case 2: {
                if (!this.inCancelProgressZone(seekBar, motionEvent.getY())) {
                    this.onProgressChanged(seekBar, n, true);
                    this.mIsInCancelZone = false;
                    return true;
                }
                if (!this.mIsInCancelZone) {
                    this.onProgressChangeCanceled(seekBar, seekBar.getProgress());
                    return this.mIsInCancelZone = true;
                }
                break;
            }
        }
        return b;
    }
    
    void onProgressChangeCanceled(final SeekBar seekBar, final int n) {
        this.this$0.mScreen.getBottomPanel().playExtraHandlerAnimation(n, new PlayerActivity$VideoPositionListener$1(this, seekBar, n));
    }
    
    public void onProgressChanged(final SeekBar seekBar, final int n, final boolean b) {
        if (Log.isLoggable("PlayerActivity", 3)) {
            Log.d("PlayerActivity", "onProgressChanged: " + n + ", fromUser " + b + "; mState.draggingInProgress: " + this.this$0.mState.draggingInProgress);
        }
        if (b && this.this$0.mState.draggingInProgress && this.this$0.mScreen != null && this.this$0.mPlayer != null) {
            this.this$0.mScreen.setProgress(n, -1, false);
            this.this$0.mState.updatePosition(n);
            this.this$0.mScreen.showBif(this.this$0.mPlayer.getBifFrame(n));
        }
        else if (!b && this.this$0.mState.draggingInProgress && this.this$0.mScreen != null) {
            this.this$0.mScreen.setProgress(n, -1, false);
            this.this$0.mState.updatePosition(n);
        }
    }
    
    public void onStartTrackingTouch(final SeekBar seekBar) {
        synchronized (this) {
            this.this$0.mState.draggingInProgress = true;
            ((TimelineSeekBar)seekBar).hideThumb(true);
            this.this$0.mState.resetTimeline();
            this.this$0.doPause(false, true);
            this.this$0.mSubtitleManager.setSubtitleVisibility(false);
            Log.d("PlayerActivity", "Start seek, get awake clock");
            this.this$0.keepScreenOn();
            this.this$0.stopScreenUpdateTask();
            this.this$0.mScreen.startCurrentTime(this.this$0.mPlayer.getBifFrame(seekBar.getProgress()));
            if (this.this$0.mScreen != null) {
                this.this$0.mScreen.changeActionState(false, false);
                this.this$0.mScreen.setTopPanelVisibility(false);
            }
        }
    }
    
    public void onStopTrackingTouch(final SeekBar seekBar) {
        boolean b;
        TimelineSeekBar timelineSeekBar;
        boolean b2;
        PlayScreen access$700;
        int access$701;
        boolean b3;
        Block_8_Outer:Label_0054_Outer:
        while (true) {
            b = true;
            while (true) {
                Label_0253: {
                    Label_0248: {
                        while (true) {
                            synchronized (this) {
                                this.this$0.mState.draggingInProgress = false;
                                Log.d("PlayerActivity", "onStopTrackingTouch called");
                                if (seekBar instanceof TimelineSeekBar) {
                                    timelineSeekBar = (TimelineSeekBar)seekBar;
                                }
                                else {
                                    Log.e("PlayerActivity", "Not a Netflix seekbar!");
                                    timelineSeekBar = null;
                                }
                                if (!this.mIsInCancelZone && !this.skipSeek(timelineSeekBar)) {
                                    break Label_0253;
                                }
                                break Label_0248;
                                // iftrue(Label_0243:, b2)
                                // iftrue(Label_0219:, b2)
                                // iftrue(Label_0121:, PlayerActivity.access$700(this.this$0) == null)
                                while (true) {
                                    Label_0121: {
                                    Block_9:
                                        while (true) {
                                            this.this$0.mScreen.setTopPanelVisibility(true);
                                            break Label_0121;
                                            Log.d("PlayerActivity", "Stop current time " + b2);
                                            Label_0173: {
                                                access$700 = this.this$0.mScreen;
                                            }
                                            break Block_9;
                                            Label_0219:
                                            Log.d("PlayerActivity", "Do not seek!");
                                            ((TimelineSeekBar)access$700).setProgress(((TimelineSeekBar)access$700).getProgress());
                                            Label_0100: {
                                                break Label_0100;
                                                while (true) {
                                                    access$701 = this.this$0.toBifAjustedProgress(this.this$0.mScreen.getBottomPanel().getCurrentProgress());
                                                    ((TimelineSeekBar)access$700).setProgress(access$701);
                                                    Log.d("PlayerActivity", "Seek!");
                                                    this.this$0.doSeek(access$701);
                                                    break Label_0100;
                                                    continue Label_0054_Outer;
                                                }
                                            }
                                            continue Block_8_Outer;
                                        }
                                        b3 = b;
                                        access$700.stopCurrentTime(b3);
                                        this.this$0.mState.resetTimeline();
                                        return;
                                    }
                                    this.this$0.mSubtitleManager.setSubtitleVisibility(true);
                                    ((TimelineSeekBar)access$700).hideThumb(false);
                                    continue Label_0054_Outer;
                                }
                            }
                            // iftrue(Label_0173:, !Log.isLoggable("PlayerActivity", 3))
                            Label_0243: {
                                b3 = false;
                            }
                            continue;
                        }
                    }
                    b2 = true;
                    continue;
                }
                b2 = false;
                continue;
            }
        }
    }
}
