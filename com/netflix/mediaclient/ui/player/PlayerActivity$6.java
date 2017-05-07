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
import android.widget.Toast;
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
import android.view.TextureView;
import android.content.IntentFilter;
import com.netflix.mediaclient.util.AndroidUtils;
import com.netflix.mediaclient.javabridge.ui.IMedia$SubtitleProfile;
import com.netflix.mediaclient.service.configuration.SubtitleConfiguration;
import com.netflix.mediaclient.media.PlayoutMetadata;
import com.netflix.mediaclient.util.AndroidManifestUtils;
import android.os.Debug;
import com.netflix.mediaclient.util.PreferenceUtils;
import android.widget.ImageView;
import android.os.SystemClock;
import android.util.Pair;
import com.netflix.mediaclient.ui.mdx.MdxTargetSelection;
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
import android.media.AudioManager;
import com.netflix.mediaclient.servicemgr.ServiceManager;
import com.netflix.mediaclient.android.widget.TappableSurfaceView$TapListener;
import com.netflix.mediaclient.android.widget.TappableSurfaceView$SurfaceMeasureListener;
import android.view.SurfaceHolder$Callback;
import com.netflix.mediaclient.media.JPlayer.SecondSurface;
import com.netflix.mediaclient.ui.common.Social$SocialProviderCallback;
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
import android.widget.SeekBar;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.android.widget.NetflixSeekBar;
import android.widget.SeekBar$OnSeekBarChangeListener;

class PlayerActivity$6 implements SeekBar$OnSeekBarChangeListener
{
    final /* synthetic */ PlayerActivity this$0;
    
    PlayerActivity$6(final PlayerActivity this$0) {
        this.this$0 = this$0;
    }
    
    private boolean skipSeek(final NetflixSeekBar netflixSeekBar) {
        boolean b = true;
        if (netflixSeekBar == null) {
            return false;
        }
        if (netflixSeekBar.inSnapPosition()) {
            Log.d("PlayerActivity", "Back to start position after snap, do NOT seek!");
            this.this$0.mState.timelineExitOnSnap = true;
        }
        else if (this.this$0.mState.timelineInSnapZone) {
            Log.d("PlayerActivity", "We are in snap zone, reset progress bar to it and update labels");
            this.this$0.mState.timelineExitOnSnap = true;
        }
        else {
            b = false;
        }
        Log.d("PlayerActivity", "tate.timelineExitOnSnap " + this.this$0.mState.timelineExitOnSnap);
        return b;
    }
    
    public void onProgressChanged(final SeekBar seekBar, final int n, final boolean b) {
        Log.d("PlayerActivity", "Progress: " + n + ", fromUser " + b);
        if (b && this.this$0.mState.draggingInProgress && this.this$0.mScreen != null && this.this$0.mPlayer != null) {
            final boolean access$800 = this.this$0.isSeekInSnapZone(n, seekBar, this.this$0.mPlayer.getDuration());
            if (access$800) {
                if (this.this$0.mState.timelineInSnapZone) {
                    if (seekBar instanceof NetflixSeekBar) {
                        final NetflixSeekBar netflixSeekBar = (NetflixSeekBar)seekBar;
                        if (Log.isLoggable("PlayerActivity", 3)) {
                            Log.d("PlayerActivity", "Snap to position was in progress " + netflixSeekBar.isSnapInProgress());
                        }
                        if (netflixSeekBar.isSnapInProgress()) {
                            Log.d("PlayerActivity", "We were already in snap zone. Do nothing! Just return!");
                            return;
                        }
                        Log.d("PlayerActivity", "We were already in snap zone. Update labels!");
                        this.this$0.mScreen.setProgress(n, -1, false);
                        this.this$0.mState.updatePosition(n);
                    }
                    else {
                        Log.e("PlayerActivity", "Not NETFLIX seekbar!");
                    }
                }
                else {
                    Log.d("PlayerActivity", "In snap zone. Snap!");
                    this.this$0.mScreen.snapToPosition(this.this$0.mState.timelineStartSeekPosition, -1);
                    this.this$0.mState.updatePosition(this.this$0.mState.timelineStartSeekPosition);
                }
            }
            else {
                Log.d("PlayerActivity", "Not in snap zone.");
                this.this$0.mScreen.setProgress(n, -1, false);
                this.this$0.mState.updatePosition(n);
            }
            this.this$0.mScreen.showBif(this.this$0.mPlayer.getBifFrame(seekBar.getProgress()));
            Log.d("PlayerActivity", "onProgressChange showing bif");
            this.this$0.mScreen.moveCurrentTimeWithTimeline(!this.this$0.mIsTablet, true);
            this.this$0.mState.timelineInSnapZone = access$800;
            return;
        }
        if (!b && this.this$0.mState.draggingInProgress && this.this$0.mScreen != null) {
            Log.d("PlayerActivity", "Not from user and state.draggingInProgress is true ");
            this.this$0.mScreen.setProgress(n, -1, false);
            this.this$0.mState.updatePosition(n);
        }
    }
    
    public void onStartTrackingTouch(final SeekBar seekBar) {
        synchronized (this) {
            this.this$0.mState.draggingInProgress = true;
            int timelineStartSeekPosition;
            if (seekBar instanceof NetflixSeekBar) {
                timelineStartSeekPosition = ((NetflixSeekBar)seekBar).setDentVisible(true);
            }
            else {
                Log.e("PlayerActivity", "Not a Netflix seekbar!");
                timelineStartSeekPosition = seekBar.getProgress();
            }
            this.this$0.mState.resetTimeline();
            this.this$0.mState.timelineStartSeekPosition = timelineStartSeekPosition;
            this.this$0.mState.updatePosition(timelineStartSeekPosition);
            this.this$0.mState.timelineInSnapZone = true;
            Log.d("PlayerActivity", "Pause playback");
            this.this$0.doPause(true);
            Log.d("PlayerActivity", "Start seek, get awake clock");
            this.this$0.keepScreenOn();
            this.this$0.stopScreenUpdateTask();
            this.this$0.mScreen.setLastTimeState(true);
            this.this$0.mScreen.startCurrentTime(this.this$0.mPlayer.getBifFrame(timelineStartSeekPosition));
            if (this.this$0.mScreen != null) {
                this.this$0.mScreen.changeActionState(false, true);
                this.this$0.mScreen.setTopPanelVisibility(false);
            }
        }
    }
    
    public void onStopTrackingTouch(final SeekBar seekBar) {
    Label_0154_Outer:
        while (true) {
            while (true) {
            Label_0214:
                while (true) {
                    NetflixSeekBar netflixSeekBar;
                    synchronized (this) {
                        Log.d("PlayerActivity", "onStopTrackingTouch called");
                        netflixSeekBar = null;
                        if (seekBar instanceof NetflixSeekBar) {
                            netflixSeekBar = (NetflixSeekBar)seekBar;
                        }
                        else {
                            Log.e("PlayerActivity", "Not a Netflix seekbar!");
                        }
                        boolean skipSeek = this.skipSeek(netflixSeekBar);
                        if (!skipSeek) {
                            final int access$1100 = this.this$0.toBifAjustedProgress(seekBar);
                            Log.d("PlayerActivity", "Seek!");
                            this.this$0.doSeek(access$1100);
                            this.this$0.mScreen.setLastTimeState(false);
                            if (this.this$0.mScreen != null) {
                                this.this$0.mScreen.setTopPanelVisibility(true);
                            }
                            if (netflixSeekBar != null) {
                                netflixSeekBar.setDentVisible(false);
                            }
                            if (Log.isLoggable("PlayerActivity", 3)) {
                                Log.d("PlayerActivity", "Stop current time " + skipSeek);
                            }
                            final PlayScreen access$1101 = this.this$0.mScreen;
                            if (!skipSeek) {
                                skipSeek = true;
                                access$1101.stopCurrentTime(skipSeek);
                                this.this$0.mState.resetTimeline();
                                return;
                            }
                            break Label_0214;
                        }
                    }
                    Log.d("PlayerActivity", "Do not seek!");
                    netflixSeekBar.setProgress(this.this$0.mState.timelineStartSeekPosition);
                    continue Label_0154_Outer;
                }
                boolean skipSeek = false;
                continue;
            }
        }
    }
}
