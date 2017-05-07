// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.details;

import com.netflix.mediaclient.ui.mdx.MdxMenu;
import android.view.Menu;
import com.netflix.mediaclient.ui.common.PlayContextImp;
import android.os.Bundle;
import com.netflix.mediaclient.servicemgr.VideoDetails;
import com.netflix.mediaclient.servicemgr.IClientLogging;
import com.netflix.mediaclient.util.NumberUtils;
import com.netflix.mediaclient.service.logging.client.model.DataContext;
import java.io.Serializable;
import android.os.Parcelable;
import android.content.Context;
import android.content.Intent;
import com.netflix.mediaclient.servicemgr.Video;
import com.netflix.mediaclient.servicemgr.VideoType;
import com.netflix.mediaclient.servicemgr.Playable;
import android.app.Activity;
import com.netflix.mediaclient.Log;
import android.app.Fragment;
import com.netflix.mediaclient.servicemgr.ServiceManager;
import com.netflix.mediaclient.ui.common.PlayContext;
import com.netflix.mediaclient.ui.common.VideoDetailsProvider;
import com.netflix.mediaclient.servicemgr.ManagerStatusListener;
import com.netflix.mediaclient.android.widget.ErrorWrapper;
import com.netflix.mediaclient.android.activity.FragmentHostActivity;

public abstract class DetailsActivity extends FragmentHostActivity implements ErrorWrapper.Callback, ManagerStatusListener, VideoDetailsProvider
{
    static final String EXTRA_EPISODE_ID = "extra_episode_id";
    static final String EXTRA_PLAY_CONTEXT = "extra_playcontext";
    static final String EXTRA_VIDEO_ID = "extra_video_id";
    private static final String TAG = "DetailsActivity";
    static final boolean USE_DUMMY_DATA = false;
    private DetailsMenu detailsMenu;
    private String episodeId;
    private PlayContext mPlayContext;
    private ServiceManager serviceMan;
    private String videoId;
    
    private void sendRetryRequest(final Fragment fragment) {
        if (fragment == null || !(fragment instanceof ErrorWrapper.Callback)) {
            return;
        }
        Log.v("DetailsActivity", "Found frag to execute retry request...");
        ((ErrorWrapper.Callback)fragment).onRetryRequested();
    }
    
    public static void show(final Activity activity, final Playable playable, final PlayContext playContext) {
        if (playable.getType() == VideoType.EPISODE) {
            show(activity, VideoType.SHOW, playable.getParentId(), playContext);
            return;
        }
        show(activity, playable.getType(), playable.getId(), playContext);
    }
    
    public static void show(final Activity activity, final Video video, final PlayContext playContext) {
        show(activity, video.getType(), video.getId(), playContext);
    }
    
    public static void show(final Activity activity, final VideoType videoType, final String s, final PlayContext playContext) {
        Serializable s2 = null;
        if (VideoType.MOVIE.equals(videoType)) {
            s2 = MovieDetailsActivity.class;
        }
        else if (VideoType.SHOW.equals(videoType)) {
            s2 = ShowDetailsActivity.class;
        }
        else if (!com.netflix.mediaclient.service.webclient.model.branches.Video.isSocialVideoType(videoType)) {
            throw new IllegalStateException("Don't know how to handle type: " + videoType);
        }
        if (s2 != null) {
            activity.startActivity(new Intent((Context)activity, (Class)s2).putExtra("extra_video_id", s).putExtra("extra_playcontext", (Parcelable)playContext));
        }
    }
    
    public static void showEpisodeDetails(final Activity activity, final String s, final String s2, final PlayContext playContext) {
        activity.startActivity(new Intent((Context)activity, (Class)ShowDetailsActivity.class).putExtra("extra_video_id", s).putExtra("extra_episode_id", s2).putExtra("extra_playcontext", (Parcelable)playContext));
    }
    
    @Override
    protected ManagerStatusListener createManagerStatusListener() {
        return this;
    }
    
    @Override
    protected DataContext getDataContext() {
        final DataContext dataContext = new DataContext();
        dataContext.setVideoId(NumberUtils.toIntegerSafely(this.videoId, null));
        if (this.mPlayContext != null) {
            dataContext.setRow(this.mPlayContext.getListPos());
            dataContext.setRank(this.mPlayContext.getVideoPos());
            dataContext.setRequestId(this.mPlayContext.getRequestId());
            dataContext.setTrackId(this.mPlayContext.getTrackId());
        }
        return dataContext;
    }
    
    public String getEpisodeId() {
        return this.episodeId;
    }
    
    @Override
    public PlayContext getPlayContext() {
        return this.mPlayContext;
    }
    
    @Override
    public IClientLogging.ModalView getUiScreen() {
        return IClientLogging.ModalView.movieDetails;
    }
    
    @Override
    public VideoDetails getVideoDetails() {
        return null;
    }
    
    @Override
    public String getVideoId() {
        return this.videoId;
    }
    
    @Override
    public void onActionExecuted() {
    }
    
    @Override
    protected void onCreate(final Bundle bundle) {
        this.videoId = this.getIntent().getStringExtra("extra_video_id");
        this.episodeId = this.getIntent().getStringExtra("extra_episode_id");
        final PlayContextImp playContext = (PlayContextImp)this.getIntent().getParcelableExtra("extra_playcontext");
        this.setPlayContext(playContext);
        if (Log.isLoggable("DetailsActivity", 2)) {
            Log.v("DetailsActivity", "TRACK_ID: " + playContext.getTrackId());
        }
        super.onCreate(bundle);
    }
    
    @Override
    public boolean onCreateOptionsMenu(final Menu menu) {
        MdxMenu.addSelectPlayTarget(this.getMdxMiniPlayerFrag(), menu);
        this.detailsMenu = new DetailsMenu(this, menu, this.videoId);
        return super.onCreateOptionsMenu(menu);
    }
    
    @Override
    public void onManagerReady(final ServiceManager serviceMan, final int n) {
        Log.v("DetailsActivity", "ServiceManager ready");
        this.serviceMan = serviceMan;
        if (this.detailsMenu != null) {
            this.invalidateOptionsMenu();
        }
        ((ManagerStatusListener)this.getPrimaryFrag()).onManagerReady(serviceMan, n);
        final Fragment secondaryFrag = this.getSecondaryFrag();
        if (secondaryFrag != null) {
            ((ManagerStatusListener)secondaryFrag).onManagerReady(serviceMan, n);
        }
    }
    
    @Override
    public void onManagerUnavailable(final ServiceManager serviceManager, final int n) {
        Log.w("DetailsActivity", "ServiceManager unavailable");
        ((ManagerStatusListener)this.getPrimaryFrag()).onManagerUnavailable(serviceManager, n);
        final Fragment secondaryFrag = this.getSecondaryFrag();
        if (secondaryFrag != null) {
            ((ManagerStatusListener)secondaryFrag).onManagerUnavailable(serviceManager, n);
        }
    }
    
    @Override
    public void onRetryRequested() {
        this.sendRetryRequest(this.getPrimaryFrag());
        this.sendRetryRequest(this.getSecondaryFrag());
    }
    
    protected void setPlayContext(final PlayContext mPlayContext) {
        this.mPlayContext = mPlayContext;
    }
    
    protected void setVideoId(final String videoId) {
        this.videoId = videoId;
    }
    
    public void updateMenus(final VideoDetails videoDetails) {
        if (this.detailsMenu != null) {
            this.detailsMenu.updateShareItem(this.serviceMan, videoDetails);
        }
    }
}
