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
import com.netflix.mediaclient.ui.kids.player.KidsPlayerActivity;
import com.netflix.mediaclient.service.pushnotification.MessageData;
import android.os.Parcelable;
import android.content.Intent;
import com.netflix.mediaclient.servicemgr.model.VideoType;
import com.netflix.mediaclient.service.configuration.PlayerTypeFactory;
import android.media.AudioManager;
import com.netflix.mediaclient.servicemgr.ServiceManager;
import android.widget.SeekBar;
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
import com.netflix.mediaclient.media.Language;
import android.view.View$OnClickListener;
import android.widget.SeekBar$OnSeekBarChangeListener;
import android.annotation.TargetApi;
import com.netflix.mediaclient.servicemgr.IPlayer$PlayerListener;
import com.netflix.mediaclient.media.JPlayer.JPlayer$JplayerListener;
import com.netflix.mediaclient.android.fragment.NetflixDialogFrag$DialogCanceledListenerProvider;
import android.media.AudioManager$OnAudioFocusChangeListener;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import com.netflix.mediaclient.servicemgr.model.details.ShowDetails;
import com.netflix.mediaclient.servicemgr.model.details.MovieDetails;
import com.netflix.mediaclient.servicemgr.model.Playable;
import com.netflix.mediaclient.ui.Asset;
import android.content.Context;
import android.widget.Toast;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.android.app.Status;
import com.netflix.mediaclient.servicemgr.model.details.VideoDetails;
import com.netflix.mediaclient.ui.common.PlayContext;
import com.netflix.mediaclient.servicemgr.LoggingManagerCallback;

class PlayerActivity$FetchVideoDetailsForPlaybackCallback extends LoggingManagerCallback
{
    private final PlayContext playContext;
    final /* synthetic */ PlayerActivity this$0;
    
    public PlayerActivity$FetchVideoDetailsForPlaybackCallback(final PlayerActivity this$0, final PlayContext playContext) {
        this.this$0 = this$0;
        super("PlayerActivity");
        this.playContext = playContext;
    }
    
    private void handleResponse(final VideoDetails videoDetails, final Status status) {
        boolean b = false;
        this.this$0.mIsAssetReady = false;
        final PlayerActivity this$0 = this.this$0;
        if (this$0.destroyed()) {
            return;
        }
        if (status.isError() || videoDetails == null) {
            Log.w("PlayerActivity", "Error loading video details for video playback");
            Toast.makeText((Context)this$0, 2131493099, 1).show();
            return;
        }
        final PlayerActivity this$2 = this.this$0;
        final Playable playable = videoDetails.getPlayable();
        final PlayContext playContext = this.playContext;
        if (!PlayerActivity.PIN_VERIFIED) {
            b = true;
        }
        this$2.mAsset = Asset.create(playable, playContext, b);
        this.this$0.mIsAssetReady = true;
        this.this$0.updateUI(this.this$0.getServiceManager());
        this.this$0.completeInitIfReady();
        Log.d("PlayerActivity", "Bilboard playback");
        this.this$0.mScreen.getPostPlay().init(this.this$0.mAsset.getPlayableId());
        Log.d("PlayerActivity", "Bilboard playback, check episode visibility");
        this.this$0.mScreen.getTopPanel().setEpisodeSelectorVisibility(this.this$0.mAsset.isEpisode());
    }
    
    @Override
    public void onMovieDetailsFetched(final MovieDetails movieDetails, final Status status) {
        super.onMovieDetailsFetched(movieDetails, status);
        this.handleResponse(movieDetails, status);
    }
    
    @Override
    public void onShowDetailsFetched(final ShowDetails showDetails, final Status status) {
        super.onShowDetailsFetched(showDetails, status);
        this.handleResponse(showDetails, status);
    }
}
