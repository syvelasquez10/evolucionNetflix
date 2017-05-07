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
import android.view.Surface;
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
import android.view.SurfaceHolder$Callback;
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
import android.os.SystemClock;
import com.netflix.mediaclient.Log;

class PlayerActivity$9 implements Runnable
{
    final /* synthetic */ PlayerActivity this$0;
    
    PlayerActivity$9(final PlayerActivity this$0) {
        this.this$0 = this$0;
    }
    
    @Override
    public void run() {
        if (this.this$0.destroyed() || this.this$0.mState.draggingInProgress || this.this$0.mState.draggingAudioInProgress) {
            Log.d("PlayerActivity", "METADATA exit");
            return;
        }
        synchronized (this.this$0) {
            if (this.this$0.mScreen != null && !this.this$0.mState.draggingInProgress && !this.this$0.mState.draggingAudioInProgress) {
                if (this.this$0.mState.getLastActionTime() > 0L && SystemClock.elapsedRealtime() - this.this$0.mState.getLastActionTime() > 5000L && this.this$0.mScreen.getState() != PlayerUiState.PostPlay) {
                    Log.d("PlayerActivity", "Time to remove panel");
                    this.this$0.clearPanel();
                }
                this.this$0.setProgress();
                this.this$0.updateMetadata();
            }
            this.this$0.repostOnEverySecondRunnable(1000);
        }
    }
}
