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
import com.netflix.mediaclient.Log;
import android.media.AudioManager;
import android.widget.SeekBar;
import android.widget.SeekBar$OnSeekBarChangeListener;

class PlayerActivity$6 implements SeekBar$OnSeekBarChangeListener
{
    final /* synthetic */ PlayerActivity this$0;
    
    PlayerActivity$6(final PlayerActivity this$0) {
        this.this$0 = this$0;
    }
    
    public void onProgressChanged(final SeekBar seekBar, final int n, final boolean b) {
        if (b && this.this$0.mState.draggingAudioInProgress && this.this$0.mScreen != null) {
            final AudioManager audioManager = (AudioManager)this.this$0.getSystemService("audio");
            if (audioManager == null) {
                Log.e("PlayerActivity", "Audio manager is not available, can not change volume");
                return;
            }
            audioManager.setStreamVolume(3, n, 0);
        }
    }
    
    public void onStartTrackingTouch(final SeekBar seekBar) {
        this.this$0.mState.draggingAudioInProgress = true;
        Log.d("PlayerActivity", "Start volume change, get awake clock");
        this.this$0.keepScreenOn();
        this.this$0.stopScreenUpdateTask();
    }
    
    public void onStopTrackingTouch(final SeekBar seekBar) {
        Log.d("PlayerActivity", "volume::onStopTrackingTouch called");
        this.this$0.mState.draggingAudioInProgress = false;
        this.this$0.mState.audioSeekToInProgress = false;
        this.this$0.startScreenUpdateTask();
    }
}
