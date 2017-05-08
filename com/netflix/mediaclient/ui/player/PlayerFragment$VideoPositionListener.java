// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.player;

import com.netflix.mediaclient.util.LanguageChoice;
import com.netflix.mediaclient.media.AudioSubtitleDefaultOrderInfo;
import com.netflix.mediaclient.media.AudioSource;
import com.netflix.mediaclient.media.Subtitle;
import com.netflix.mediaclient.ui.home.HomeActivity;
import com.netflix.mediaclient.util.log.UIViewLogUtils;
import com.netflix.mediaclient.servicemgr.UIViewLogging$UIViewCommandName;
import com.netflix.mediaclient.service.player.subtitles.SubtitleScreen;
import com.netflix.mediaclient.service.logging.client.model.DeepErrorElement;
import java.util.List;
import java.io.Serializable;
import android.media.AudioManager;
import com.netflix.mediaclient.media.Watermark;
import com.netflix.mediaclient.servicemgr.IPlayer$PlaybackError;
import android.view.MenuItem;
import com.netflix.mediaclient.util.NflxProtocolUtils;
import android.view.Surface;
import android.widget.FrameLayout;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.content.res.Configuration;
import com.netflix.mediaclient.ui.verifyplay.PinVerifier;
import com.netflix.mediaclient.ui.coppola.details.CoppolaDetailsActivity;
import android.content.Intent;
import com.netflix.mediaclient.servicemgr.UserActionLogging$CommandName;
import com.netflix.mediaclient.util.MdxUtils;
import com.netflix.mediaclient.util.DeviceUtils;
import com.netflix.mediaclient.ui.bandwidthsetting.BandwidthUtility;
import com.netflix.mediaclient.service.net.LogMobileType;
import android.view.Window;
import com.netflix.mediaclient.util.l10n.LocalizationUtils;
import com.netflix.mediaclient.ui.mdx.MdxTargetSelection;
import android.util.Pair;
import com.netflix.mediaclient.ui.verifyplay.PinAndAgeVerifier;
import com.netflix.mediaclient.ui.verifyplay.PlayVerifierVault;
import com.netflix.mediaclient.ui.verifyplay.PlayVerifierVault$RequestedBy;
import android.annotation.SuppressLint;
import android.view.TextureView;
import android.content.IntentFilter;
import android.support.v7.widget.Toolbar;
import com.netflix.mediaclient.servicemgr.ISubtitleDef$SubtitleProfile;
import com.netflix.mediaclient.service.configuration.SubtitleConfiguration;
import com.netflix.mediaclient.media.PlayoutMetadata;
import com.netflix.mediaclient.service.configuration.drm.DrmManagerRegistry;
import com.netflix.mediaclient.ui.player.subtitles.SubtitleManager;
import com.netflix.mediaclient.ui.player.subtitles.SubtitleManagerFactory;
import android.os.Debug;
import com.netflix.mediaclient.util.AndroidManifestUtils;
import com.netflix.mediaclient.util.PreferenceUtils;
import android.app.Activity;
import android.os.SystemClock;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import android.app.DialogFragment;
import com.netflix.mediaclient.ui.details.EpisodesFrag;
import com.netflix.mediaclient.ui.kubrick.details.BarkerShowDetailsFrag;
import com.netflix.mediaclient.ui.kubrick.details.BarkerHelper;
import com.netflix.mediaclient.util.AndroidUtils;
import android.view.ViewGroup$LayoutParams;
import android.widget.LinearLayout$LayoutParams;
import com.netflix.mediaclient.ui.experience.BrowseExperience;
import com.netflix.mediaclient.servicemgr.ManagerCallback;
import com.netflix.mediaclient.servicemgr.interface_.VideoType;
import com.netflix.mediaclient.util.StringUtils;
import com.netflix.mediaclient.android.app.NetflixImmutableStatus;
import com.netflix.mediaclient.servicemgr.interface_.offline.realm.RealmVideoDetails;
import com.netflix.mediaclient.android.app.CommonStatus;
import com.netflix.mediaclient.servicemgr.interface_.offline.realm.RealmUtils;
import com.netflix.mediaclient.ui.common.PlayContextImp;
import com.netflix.mediaclient.service.logging.client.model.UIError;
import com.netflix.mediaclient.util.log.UserActionLogUtils;
import com.netflix.mediaclient.util.StatusUtils;
import com.netflix.mediaclient.util.log.ConsolidatedLoggingUtils;
import com.netflix.mediaclient.servicemgr.IClientLogging$ModalView;
import com.netflix.mediaclient.util.log.OfflineLogUtils;
import com.netflix.mediaclient.servicemgr.IClientLogging$CompletionReason;
import java.util.Iterator;
import com.netflix.mediaclient.util.ThreadUtils;
import com.netflix.mediaclient.ui.common.PlaybackLauncher;
import com.netflix.mediaclient.media.PlayerType;
import com.netflix.mediaclient.servicemgr.interface_.Playable;
import com.netflix.mediaclient.servicemgr.interface_.PlaybackBookmark;
import com.netflix.mediaclient.util.TimeUtils;
import com.netflix.mediaclient.media.BookmarkStore;
import android.widget.Toast;
import com.netflix.mediaclient.event.nrdp.media.NccpActionId;
import android.view.View;
import android.view.KeyEvent;
import com.netflix.mediaclient.ui.common.PlayLocationType;
import com.netflix.mediaclient.android.widget.AlertDialogFactory;
import com.netflix.mediaclient.util.ConnectivityUtils;
import com.netflix.mediaclient.android.widget.UpdateDialog$Builder;
import com.netflix.mediaclient.servicemgr.interface_.offline.WatchState;
import org.json.JSONException;
import org.json.JSONObject;
import android.os.Parcelable;
import com.netflix.mediaclient.service.webclient.model.leafs.ABTestConfig$Cell;
import com.netflix.mediaclient.service.configuration.PersistentConfig;
import android.content.Context;
import com.netflix.mediaclient.util.Coppola1Utils;
import com.netflix.mediaclient.service.configuration.PlayerTypeFactory;
import com.netflix.mediaclient.servicemgr.interface_.offline.OfflinePlayableViewData;
import com.netflix.mediaclient.servicemgr.interface_.offline.DownloadState;
import com.netflix.mediaclient.event.nrdp.media.Error;
import com.netflix.mediaclient.servicemgr.ServiceManager;
import com.netflix.mediaclient.android.widget.AlertDialogFactory$AlertDialogDescriptor;
import com.netflix.mediaclient.service.error.ErrorDescriptor;
import com.netflix.mediaclient.service.logging.client.model.ActionOnUIError;
import com.netflix.mediaclient.service.logging.client.model.RootCause;
import com.netflix.mediaclient.event.nrdp.media.MediaEvent;
import com.netflix.mediaclient.ui.player.error.PlayerErrorDialogDescriptorFactory;
import com.netflix.mediaclient.event.nrdp.media.NccpError;
import com.netflix.mediaclient.android.app.Status;
import com.netflix.mediaclient.servicemgr.interface_.details.VideoDetails;
import com.netflix.mediaclient.ui.common.PlayContext;
import com.netflix.mediaclient.android.widget.TappableSurfaceView$TapListener;
import com.netflix.mediaclient.android.widget.TappableSurfaceView$SurfaceMeasureListener;
import android.view.SurfaceHolder$Callback;
import java.util.ArrayList;
import com.netflix.mediaclient.media.JPlayer.SecondSurface;
import com.netflix.mediaclient.service.player.subtitles.SafeSubtitleManager;
import android.view.ViewGroup;
import android.view.Menu;
import com.netflix.mediaclient.servicemgr.IPlayer;
import com.netflix.mediaclient.servicemgr.IPlayer$PlaybackType;
import android.content.BroadcastReceiver;
import android.os.Handler;
import android.os.Bundle;
import com.netflix.mediaclient.android.fragment.NetflixDialogFrag;
import com.netflix.mediaclient.ui.details.AbsEpisodeView$EpisodeRowListener;
import com.netflix.mediaclient.android.fragment.NetflixDialogFrag$DialogCanceledListener;
import com.netflix.mediaclient.service.ServiceAgent$ConfigurationAgentInterface;
import com.netflix.mediaclient.servicemgr.Asset;
import com.netflix.mediaclient.media.Language;
import android.view.View$OnClickListener;
import com.netflix.mediaclient.ui.details.DetailsActivity$Reloader;
import com.netflix.mediaclient.ui.common.PlayContextProvider;
import com.netflix.mediaclient.servicemgr.IPlayer$PlayerListener;
import com.netflix.mediaclient.media.JPlayer.JPlayer$JplayerListener;
import com.netflix.mediaclient.android.widget.ErrorWrapper$Callback;
import com.netflix.mediaclient.android.fragment.NetflixDialogFrag$DialogCanceledListenerProvider;
import android.media.AudioManager$OnAudioFocusChangeListener;
import com.netflix.mediaclient.android.fragment.NetflixFrag;
import com.netflix.mediaclient.service.logging.error.ErrorLoggingManager;
import android.view.MotionEvent;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.android.widget.TimelineSeekBar;
import android.widget.SeekBar;
import android.widget.SeekBar$OnSeekBarChangeListener;

public class PlayerFragment$VideoPositionListener implements SeekBar$OnSeekBarChangeListener
{
    private boolean mIsInCancelZone;
    final /* synthetic */ PlayerFragment this$0;
    
    public PlayerFragment$VideoPositionListener(final PlayerFragment this$0) {
        this.this$0 = this$0;
    }
    
    private boolean inCancelProgressZone(final SeekBar seekBar, final float n) {
        final float dimension = this.this$0.getResources().getDimension(2131362287);
        int height;
        if (this.this$0.mIsTablet) {
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
        if (timelineSeekBar.getProgress() == this.this$0.mScreen.getCurrentTimelineProgress()) {
            Log.d("PlayerFragment", "Back to start position after snap, do NOT seek!");
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
                if (this.inCancelProgressZone(seekBar, motionEvent.getY())) {
                    this.onProgressChanged(seekBar, seekBar.getProgress(), true);
                    this.this$0.mState.timelineExitOnSnap = true;
                }
                this.onStopTrackingTouch(seekBar);
                return true;
            }
            case 0: {
                this.mIsInCancelZone = false;
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
        this.this$0.mScreen.playExtraHandlerAnimation(n, new PlayerFragment$VideoPositionListener$1(this, seekBar, n));
    }
    
    public void onProgressChanged(final SeekBar seekBar, final int n, final boolean b) {
        if (Log.isLoggable()) {
            Log.d("PlayerFragment", "onProgressChanged: " + n + ", fromUser " + b + "; mState.draggingInProgress: " + this.this$0.mState.draggingInProgress);
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
            Log.d("PlayerFragment", "Start seek, get awake clock");
            this.this$0.keepScreenOn();
            this.this$0.stopScreenUpdateTask();
            this.this$0.mScreen.startCurrentTime(this.this$0.mPlayer.getBifFrame(seekBar.getProgress()));
            this.this$0.mScreen.startDragging();
        }
    }
    
    public void onStopTrackingTouch(final SeekBar seekBar) {
    Label_0169_Outer:
        while (true) {
            final boolean b = true;
        Label_0054:
            while (true) {
            Label_0169:
                while (true) {
                Label_0097:
                    while (true) {
                        Label_0208: {
                            synchronized (this) {
                                this.this$0.mState.draggingInProgress = false;
                                Log.d("PlayerFragment", "onStopTrackingTouch called");
                                if (!(seekBar instanceof TimelineSeekBar)) {
                                    Log.e("PlayerFragment", "PlayerFragment got not a Netflix seekbar!");
                                    ErrorLoggingManager.logHandledException("PlayerFragment got not a Netflix seekbar!");
                                    return;
                                }
                                final TimelineSeekBar timelineSeekBar = (TimelineSeekBar)seekBar;
                                if (!this.mIsInCancelZone && !this.skipSeek(timelineSeekBar)) {
                                    break Label_0208;
                                }
                                break Label_0169;
                                // iftrue(Label_0213:, b2)
                                // iftrue(Label_0232:, b2)
                                while (true) {
                                    boolean b2;
                                    PlayScreen access$1301;
                                    while (true) {
                                        b2 = b;
                                        break Label_0169;
                                        final int access$1300 = this.this$0.toBifAjustedProgress(this.this$0.mScreen.getCurrentTimelineProgress());
                                        ((TimelineSeekBar)access$1301).setProgress(access$1300);
                                        Log.d("PlayerFragment", "Seek!");
                                        this.this$0.doSeek(access$1300);
                                        break Label_0097;
                                        Log.d("PlayerFragment", "Stop current time " + b2);
                                        Label_0154: {
                                            access$1301 = this.this$0.mScreen;
                                        }
                                        continue Label_0169_Outer;
                                    }
                                    access$1301.stopCurrentTime(b2);
                                    this.this$0.mState.resetTimeline();
                                    return;
                                    this.this$0.mScreen.finishDragging();
                                    this.this$0.mSubtitleManager.setSubtitleVisibility(true);
                                    ((TimelineSeekBar)access$1301).hideThumb(false);
                                    continue;
                                }
                            }
                            // iftrue(Label_0154:, !Log.isLoggable())
                        }
                        boolean b2 = false;
                        continue Label_0054;
                        Label_0213: {
                            Log.d("PlayerFragment", "Do not seek!");
                        }
                        PlayScreen access$1301 = null;
                        ((TimelineSeekBar)access$1301).setProgress(((TimelineSeekBar)access$1301).getProgress());
                        continue Label_0097;
                    }
                    Label_0232: {
                        final boolean b2 = false;
                    }
                    continue Label_0169;
                }
                boolean b2 = true;
                continue Label_0054;
            }
        }
    }
}
