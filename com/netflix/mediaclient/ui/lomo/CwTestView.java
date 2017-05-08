// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.lomo;

import com.netflix.mediaclient.util.gfx.ImageLoader$StaticImgConfig;
import com.netflix.mediaclient.android.widget.AdvancedImageView;
import com.netflix.mediaclient.util.gfx.ImageLoader;
import android.view.View$OnClickListener;
import com.netflix.mediaclient.util.CWTestUtil;
import com.netflix.mediaclient.servicemgr.IClientLogging$AssetType;
import com.netflix.mediaclient.ui.common.PlayContextImp;
import com.netflix.mediaclient.servicemgr.interface_.trackable.Trackable;
import android.view.View;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import com.netflix.mediaclient.servicemgr.interface_.Video;
import com.netflix.mediaclient.ui.experience.BrowseExperience;
import android.content.Context;
import com.netflix.mediaclient.servicemgr.interface_.CWVideo;

public class CwTestView extends CwView implements VideoViewGroup$IVideoView<CWVideo>
{
    private static final String TAG = "CwTestView";
    
    public CwTestView(final Context context) {
        super(context);
    }
    
    @Override
    public String getImageUrl(final CWVideo cwVideo, final boolean b) {
        return BrowseExperience.getLomoVideoViewImageUrl(this.getContext(), cwVideo, CwTestView.class, 0);
    }
    
    @Override
    protected int getLayoutId() {
        return 2130903089;
    }
    
    @Override
    public void hide() {
        NetflixActivity.getImageLoader(this.getContext()).clear(this.img);
        this.setVisibility(4);
        this.clicker.remove((View)this.img);
    }
    
    @Override
    public void setInfoViewId(final int n) {
    }
    
    @Override
    public void update(final CWVideo cwVideo, final Trackable trackable, int progress, final boolean b, final boolean b2) {
        final int n = 0;
        this.playContext = new PlayContextImp(trackable, progress);
        this.setVisibility(0);
        final String format = String.format(this.getResources().getString(2131230891), cwVideo.getTitle());
        this.setContentDescription((CharSequence)format);
        final String imageUrl = this.getImageUrl(cwVideo, b2);
        final ImageLoader imageLoader = NetflixActivity.getImageLoader(this.getContext());
        final AdvancedImageView img = this.img;
        final IClientLogging$AssetType boxArt = IClientLogging$AssetType.boxArt;
        final ImageLoader$StaticImgConfig imageLoaderConfig = BrowseExperience.getImageLoaderConfig();
        if (b) {
            progress = 1;
        }
        else {
            progress = 0;
        }
        imageLoader.showImg(img, imageUrl, boxArt, format, imageLoaderConfig, true, progress);
        progress = n;
        if (cwVideo.getRuntime() > 0) {
            progress = cwVideo.getPlayableBookmarkPosition() * 100 / cwVideo.getRuntime();
        }
        this.progress.setProgress(progress);
        if (CWTestUtil.isDirectToPlayback(this.getContext())) {
            this.setOnClickListener((View$OnClickListener)new CwTestView$1(this, cwVideo));
            return;
        }
        this.clicker.update((View)this.img, cwVideo, this.img.getPressedStateHandler());
    }
}
