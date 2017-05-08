// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.lomo;

import com.netflix.mediaclient.util.gfx.ImageLoader$StaticImgConfig;
import com.netflix.mediaclient.android.widget.AdvancedImageView;
import com.netflix.mediaclient.util.gfx.ImageLoader;
import android.view.View$OnClickListener;
import com.netflix.mediaclient.ui.experience.BrowseExperience;
import com.netflix.mediaclient.servicemgr.IClientLogging$AssetType;
import com.netflix.mediaclient.servicemgr.interface_.VideoType;
import com.netflix.mediaclient.servicemgr.interface_.Video;
import com.netflix.mediaclient.servicemgr.interface_.Playable;
import com.netflix.mediaclient.servicemgr.ServiceManagerUtils;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import com.netflix.mediaclient.ui.common.PlayContextImp;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.servicemgr.interface_.trackable.Trackable;
import com.netflix.mediaclient.servicemgr.interface_.CWVideo;
import android.support.v4.content.ContextCompat;
import android.content.Context;
import com.netflix.mediaclient.ui.lomo.discovery.extended.CWExtendedDiscoveryFrag$RemotePlaybackListener;

public class CwDiscoveryView extends CwView
{
    private static final String TAG = "CwDiscoveryView";
    private CWExtendedDiscoveryFrag$RemotePlaybackListener remotePlaybackListener;
    
    public CwDiscoveryView(final Context context) {
        super(context);
    }
    
    @Override
    protected int getLayoutId() {
        return 2130903090;
    }
    
    public void init() {
        super.init();
        this.progress.setProgressDrawable(ContextCompat.getDrawable(this.getContext(), 2130837670));
    }
    
    @Override
    public void update(final CWVideo cwVideo, final Trackable trackable, int progress, final boolean b, final boolean b2) {
        final int n = 0;
        if (Log.isLoggable()) {
            Log.v("CwDiscoveryView", "Updating for video: " + cwVideo.toString());
        }
        this.playContext = new PlayContextImp(trackable, progress);
        if (cwVideo.getRuntime() > 0) {
            progress = cwVideo.getPlayableBookmarkPosition() * 100 / cwVideo.getRuntime();
        }
        else {
            progress = 0;
        }
        this.progress.setProgress(progress);
        ServiceManagerUtils.castPrefetchAndCacheManifestIfEnabled(((NetflixActivity)this.getContext()).getServiceManager(), cwVideo, this.playContext);
        this.info.setContentDescription((CharSequence)String.format(this.getResources().getString(2131296619), cwVideo.getTitle()));
        this.clicker.update(this.info, cwVideo, this.img.getPressedStateHandler());
        if (VideoType.SHOW.equals(cwVideo.getType())) {
            this.setTitle(this.getContext().getString(2131296781, new Object[] { cwVideo.getSeasonAbbrSeqLabel(), cwVideo.getEpisodeNumber() }));
            this.title.setVisibility(0);
        }
        else {
            this.title.setVisibility(8);
        }
        final String format = String.format(this.getResources().getString(2131296447), cwVideo.getTitle());
        this.setContentDescription((CharSequence)format);
        final ImageLoader imageLoader = NetflixActivity.getImageLoader(this.getContext());
        final AdvancedImageView img = this.img;
        final String horzDispUrl = cwVideo.getHorzDispUrl();
        final IClientLogging$AssetType boxArt = IClientLogging$AssetType.boxArt;
        final ImageLoader$StaticImgConfig imageLoaderConfig = BrowseExperience.getImageLoaderConfig();
        progress = n;
        if (b) {
            progress = 1;
        }
        imageLoader.showImg(img, horzDispUrl, boxArt, format, imageLoaderConfig, true, progress);
        this.setOnClickListener((View$OnClickListener)new CwDiscoveryView$1(this, cwVideo));
    }
    
    public void update(final CWVideo cwVideo, final Trackable trackable, final int n, final boolean b, final boolean b2, final CWExtendedDiscoveryFrag$RemotePlaybackListener remotePlaybackListener) {
        this.remotePlaybackListener = remotePlaybackListener;
        this.update(cwVideo, trackable, n, b, b2);
    }
}
