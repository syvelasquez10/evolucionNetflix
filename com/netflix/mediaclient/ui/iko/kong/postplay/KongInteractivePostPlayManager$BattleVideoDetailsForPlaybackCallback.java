// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.iko.kong.postplay;

import com.netflix.mediaclient.ui.common.PlayContext;
import com.netflix.mediaclient.service.logging.error.ErrorLoggingManager;
import com.netflix.mediaclient.ui.common.PlayContextImp;
import com.netflix.mediaclient.ui.iko.kong.model.KongInteractivePostPlayModel$KongSound;
import com.netflix.mediaclient.servicemgr.interface_.VideoType;
import com.netflix.mediaclient.ui.common.MediaPlayerWrapper$PlaybackEventsListener;
import android.view.TextureView;
import android.view.LayoutInflater;
import com.netflix.mediaclient.util.ViewUtils;
import com.netflix.mediaclient.android.widget.AdvancedImageView;
import com.netflix.mediaclient.android.widget.PressAnimationFrameLayout;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import java.io.IOException;
import android.graphics.BitmapFactory;
import com.netflix.mediaclient.util.FileUtils;
import java.io.File;
import com.netflix.mediaclient.util.ThreadUtils;
import android.graphics.Bitmap;
import com.netflix.mediaclient.servicemgr.ManagerCallback;
import com.netflix.mediaclient.servicemgr.IClientLogging$AssetType;
import com.netflix.mediaclient.servicemgr.ServiceManager;
import com.netflix.mediaclient.util.StringUtils;
import com.netflix.mediaclient.android.osp.AsyncTaskCompat;
import com.facebook.device.yearclass.YearClass;
import java.util.Collections;
import com.netflix.mediaclient.ui.iko.kong.model.KongInteractivePostPlayModel;
import com.netflix.mediaclient.service.resfetcher.volley.LocalCachedFileMetadata;
import java.util.Map;
import java.util.List;
import com.netflix.mediaclient.ui.player.PostPlay;
import com.netflix.mediaclient.ui.player.PlayerFragment;
import android.graphics.BitmapFactory$Options;
import com.netflix.mediaclient.ui.common.MediaPlayerWrapper;
import android.view.View;
import android.os.Handler;
import android.animation.Animator;
import android.widget.ImageView;
import android.view.ViewGroup;
import com.netflix.mediaclient.ui.iko.InteractivePostPlayManager;
import com.netflix.mediaclient.servicemgr.interface_.details.ShowDetails;
import com.netflix.mediaclient.servicemgr.interface_.details.MovieDetails;
import com.netflix.mediaclient.servicemgr.interface_.details.EpisodeDetails;
import android.content.Context;
import android.widget.Toast;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.android.app.Status;
import com.netflix.mediaclient.servicemgr.interface_.details.VideoDetails;
import com.netflix.mediaclient.servicemgr.LoggingManagerCallback;

class KongInteractivePostPlayManager$BattleVideoDetailsForPlaybackCallback extends LoggingManagerCallback
{
    final /* synthetic */ KongInteractivePostPlayManager this$0;
    
    public KongInteractivePostPlayManager$BattleVideoDetailsForPlaybackCallback(final KongInteractivePostPlayManager this$0) {
        this.this$0 = this$0;
        super("KongInteractivePostPlayManager");
    }
    
    protected void handleResponse(final VideoDetails videoDetails, final Status status) {
        if (!this.this$0.isActivityValid()) {
            if (Log.isLoggable()) {
                Log.d("KongInteractivePostPlayManager", "Activity is already destroyed, ignore request!");
            }
        }
        else {
            if (status.isError() || videoDetails == null) {
                Log.e("KongInteractivePostPlayManager", "Error loading video details for video playback");
                Toast.makeText((Context)this.this$0.getActivity(), 2131231079, 1).show();
                return;
            }
            if (Log.isLoggable()) {
                Log.d("KongInteractivePostPlayManager", "Retrieved details: " + videoDetails.getTitle() + ", " + videoDetails);
            }
            final int battleWinVideoId = this.this$0.gearSelectionScreen.getBattleWinVideoId();
            final int battleLostVideoId = this.this$0.gearSelectionScreen.getBattleLostVideoId();
            final int nextEpisodeVideoId = this.this$0.battleIntroScreen.getNextEpisodeVideoId();
            if (videoDetails != null && videoDetails.getPlayable() != null) {
                final String playableId = videoDetails.getPlayable().getPlayableId();
                if (String.valueOf(battleWinVideoId).equalsIgnoreCase(playableId)) {
                    this.this$0.battleWinVideoDetails = videoDetails;
                }
                if (String.valueOf(battleLostVideoId).equalsIgnoreCase(playableId)) {
                    this.this$0.battleLoseVideoDetails = videoDetails;
                }
                if (String.valueOf(nextEpisodeVideoId).equalsIgnoreCase(playableId)) {
                    this.this$0.nextEpisodeDetails = videoDetails;
                }
            }
        }
    }
    
    @Override
    public void onEpisodeDetailsFetched(final EpisodeDetails episodeDetails, final Status status) {
        super.onEpisodeDetailsFetched(episodeDetails, status);
        this.handleResponse(episodeDetails, status);
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
