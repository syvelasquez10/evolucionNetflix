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
import java.io.Serializable;
import android.media.AudioManager;
import android.widget.Toast;
import android.app.DialogFragment;
import android.view.MenuItem;
import com.netflix.mediaclient.event.nrdp.media.Error;
import com.netflix.mediaclient.util.log.ConsolidatedLoggingUtils;
import com.netflix.mediaclient.servicemgr.UserActionLogging$CommandName;
import com.netflix.mediaclient.util.log.UserActionLogUtils;
import android.app.Activity;
import android.os.Bundle;
import android.content.res.Configuration;
import com.netflix.mediaclient.ui.verifyplay.PinVerifier;
import com.netflix.mediaclient.service.net.LogMobileType;
import com.netflix.mediaclient.servicemgr.IClientLogging$ModalView;
import com.netflix.mediaclient.service.logging.client.model.DataContext;
import com.netflix.mediaclient.servicemgr.ManagerStatusListener;
import com.netflix.mediaclient.ui.verifyplay.PlayVerifier;
import com.netflix.mediaclient.ui.verifyplay.PlayVerifierVault;
import com.netflix.mediaclient.ui.verifyplay.PlayVerifierVault$PlayInvokedFrom;
import android.annotation.SuppressLint;
import android.view.TextureView;
import android.content.IntentFilter;
import com.netflix.mediaclient.util.AndroidUtils;
import android.support.v7.widget.Toolbar;
import com.netflix.mediaclient.javabridge.ui.IMedia$SubtitleProfile;
import com.netflix.mediaclient.service.configuration.SubtitleConfiguration;
import com.netflix.mediaclient.media.PlayoutMetadata;
import com.netflix.mediaclient.ui.player.subtitles.SubtitleManager;
import com.netflix.mediaclient.ui.player.subtitles.SubtitleManagerFactory;
import android.os.Debug;
import com.netflix.mediaclient.util.AndroidManifestUtils;
import com.netflix.mediaclient.util.PreferenceUtils;
import android.os.SystemClock;
import android.util.Pair;
import com.netflix.mediaclient.ui.mdx.MdxTargetSelection;
import com.netflix.mediaclient.ui.experience.BrowseExperience;
import com.netflix.mediaclient.servicemgr.ManagerCallback;
import com.netflix.mediaclient.servicemgr.interface_.Playable;
import com.netflix.mediaclient.util.ThreadUtils;
import com.netflix.mediaclient.android.widget.AlertDialogFactory;
import com.netflix.mediaclient.android.widget.AlertDialogFactory$AlertDialogDescriptor;
import com.netflix.mediaclient.event.nrdp.media.NccpError;
import com.netflix.mediaclient.event.nrdp.media.NccpActionId;
import com.netflix.mediaclient.media.PlayerType;
import com.netflix.mediaclient.util.ConnectivityUtils;
import android.view.View;
import android.view.KeyEvent;
import android.content.Context;
import com.netflix.mediaclient.service.pushnotification.MessageData;
import android.os.Parcelable;
import android.content.Intent;
import com.netflix.mediaclient.ui.common.PlayContext;
import com.netflix.mediaclient.servicemgr.interface_.VideoType;
import com.netflix.mediaclient.service.configuration.PlayerTypeFactory;
import com.netflix.mediaclient.servicemgr.ServiceManager;
import com.netflix.mediaclient.android.widget.TappableSurfaceView$TapListener;
import com.netflix.mediaclient.android.widget.TappableSurfaceView$SurfaceMeasureListener;
import com.netflix.mediaclient.media.JPlayer.SecondSurface;
import com.netflix.mediaclient.service.player.subtitles.SafeSubtitleManager;
import android.view.Menu;
import com.netflix.mediaclient.servicemgr.IPlayer;
import android.content.BroadcastReceiver;
import android.os.Handler;
import com.netflix.mediaclient.ui.details.AbsEpisodeView$EpisodeRowListener;
import com.netflix.mediaclient.android.fragment.NetflixDialogFrag$DialogCanceledListener;
import com.netflix.mediaclient.service.ServiceAgent$ConfigurationAgentInterface;
import com.netflix.mediaclient.servicemgr.Asset;
import com.netflix.mediaclient.media.Language;
import android.view.View$OnClickListener;
import android.widget.SeekBar$OnSeekBarChangeListener;
import android.annotation.TargetApi;
import com.netflix.mediaclient.ui.common.PlayContextProvider;
import com.netflix.mediaclient.servicemgr.IPlayer$PlayerListener;
import com.netflix.mediaclient.media.JPlayer.JPlayer$JplayerListener;
import com.netflix.mediaclient.android.widget.ErrorWrapper$Callback;
import com.netflix.mediaclient.android.fragment.NetflixDialogFrag$DialogCanceledListenerProvider;
import android.media.AudioManager$OnAudioFocusChangeListener;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import android.view.Surface;
import com.netflix.mediaclient.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder$Callback;

class PlayerActivity$10 implements SurfaceHolder$Callback
{
    final /* synthetic */ PlayerActivity this$0;
    
    PlayerActivity$10(final PlayerActivity this$0) {
        this.this$0 = this$0;
    }
    
    public void surfaceChanged(final SurfaceHolder surfaceHolder, final int n, final int n2, final int n3) {
        if (Log.isLoggable()) {
            Log.d("PlayerActivity", "Surface changed, format: " + n + ", width: " + n2 + ", height: " + n3);
            if (surfaceHolder != null && surfaceHolder.getSurface() != null) {
                Log.d("PlayerActivity", "Holder: " + surfaceHolder);
                Log.d("PlayerActivity", "Native surface: " + surfaceHolder.getSurface());
            }
        }
    }
    
    public void surfaceCreated(final SurfaceHolder surfaceHolder) {
        synchronized (this) {
            Log.d("PlayerActivity", "Surface created");
            if (surfaceHolder != null && surfaceHolder.getSurface() != null && this.this$0.mScreen != null) {
                this.this$0.mIsSurfaceReady = true;
                this.this$0.mScreen.getSurfaceView().setVisibility(0);
                if (Log.isLoggable()) {
                    Log.d("PlayerActivity", "Native surface: " + surfaceHolder.getSurface());
                }
                this.this$0.completeInitIfReady();
                if (this.this$0.mPlayer != null) {
                    this.this$0.mPlayer.setSurface(surfaceHolder.getSurface());
                }
                if (this.this$0.mPlayerBackgrounded) {
                    this.this$0.removeDialogFragmentIfShown();
                    this.this$0.doUnpause();
                }
            }
            else {
                this.this$0.mIsSurfaceReady = false;
                if (this.this$0.mAsset == null) {
                    Log.e("PlayerActivity", "surfaceCreated again, playout already set to null");
                }
                Log.d("PlayerActivity", "SurfaceCreated again, no playback");
            }
        }
    }
    
    public void surfaceDestroyed(final SurfaceHolder surfaceHolder) {
        this.this$0.mIsSurfaceReady = false;
        if (this.this$0.mPlayer != null && this.this$0.canPlayerBeBackgrounded()) {
            Log.d("PlayerActivity", "Surface destroyed,, background player");
            this.this$0.mPlayer.setSurface(null);
            this.this$0.mPlayerBackgrounded = true;
            this.this$0.mPlayerSuspendNotification.showNotification(this.this$0.mAsset);
            return;
        }
        if (!this.this$0.mScreen.canExitPlaybackEndOfPlay()) {
            Log.d("PlayerActivity", "In posplay when surface is destroyed, do not exit");
            return;
        }
        Log.d("PlayerActivity", "Surface destroyed, exit if we are not already in it");
        this.this$0.cleanupAndExit();
    }
}
