// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.mdx;

import android.support.v7.widget.RecyclerView$LayoutManager;
import android.support.v7.widget.RecyclerView$Adapter;
import com.netflix.mediaclient.android.widget.RecyclerViewHeaderAdapter$IViewCreator;
import com.netflix.mediaclient.ui.common.SimilarItemsGridViewAdapter;
import com.netflix.mediaclient.util.ThreadUtils;
import com.netflix.mediaclient.android.app.Status;
import com.netflix.mediaclient.ui.common.PlaybackLauncher;
import android.content.BroadcastReceiver;
import android.os.Bundle;
import com.netflix.mediaclient.util.ViewUtils;
import android.app.FragmentTransaction;
import com.netflix.mediaclient.ui.details.RoleDetailsFrag;
import android.view.animation.Animation;
import android.view.animation.AlphaAnimation;
import com.netflix.mediaclient.servicemgr.interface_.Playable;
import com.netflix.mediaclient.servicemgr.ServiceManager;
import com.netflix.falkor.CachedModelProxy$CmpTaskDetails;
import com.netflix.falkor.CachedModelProxy$FetchTurboCollectionVideosTask;
import android.view.KeyEvent;
import com.netflix.mediaclient.servicemgr.ManagerCallback;
import com.netflix.mediaclient.servicemgr.interface_.details.EpisodeDetails;
import com.netflix.mediaclient.servicemgr.ServiceManagerUtils;
import com.netflix.mediaclient.servicemgr.interface_.VideoType;
import com.netflix.mediaclient.service.configuration.PersistentConfig;
import android.support.v7.widget.RecyclerView$ItemDecoration;
import com.netflix.mediaclient.util.ItemDecorationUniformPadding;
import android.content.Intent;
import android.annotation.SuppressLint;
import android.app.Fragment;
import android.app.Activity;
import com.netflix.mediaclient.util.AndroidUtils;
import android.app.FragmentManager;
import android.app.DialogFragment;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.util.DeviceUtils;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;
import android.view.View;
import com.netflix.mediaclient.android.widget.RecyclerViewHeaderAdapter;
import com.netflix.mediaclient.service.mdx.MdxKeyEventHandler;
import com.netflix.mediaclient.service.mdx.MdxKeyEventHandler$MdxKeyEventCallbacks;
import com.netflix.mediaclient.service.mdx.MdxErrorHandler;
import com.netflix.mediaclient.ui.common.LanguageSelector$LanguageSelectorCallback;
import com.netflix.mediaclient.ui.common.LanguageSelector;
import android.os.Handler;
import android.support.v7.widget.GridLayoutManager;
import com.netflix.mediaclient.servicemgr.IMdx;
import com.netflix.mediaclient.servicemgr.interface_.details.VideoDetails;
import com.netflix.mediaclient.util.MdxUtils$MdxTargetSelectionDialogInterface;
import com.netflix.mediaclient.ui.details.NetflixRatingBar$RatingBarDataProvider;
import com.netflix.mediaclient.ui.details.IHandleBackPress;
import com.netflix.mediaclient.ui.details.AbsEpisodeView$EpisodeRowListener;
import com.netflix.mediaclient.android.fragment.NetflixFrag;
import com.netflix.mediaclient.util.gfx.ImageLoader$StaticImgConfig;
import com.netflix.mediaclient.util.gfx.ImageLoader;
import android.view.View$OnClickListener;
import com.netflix.mediaclient.ui.experience.BrowseExperience;
import com.netflix.mediaclient.servicemgr.IClientLogging$AssetType;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import com.netflix.mediaclient.android.widget.AdvancedImageView;
import com.netflix.mediaclient.ui.common.PlayContextImp;
import com.netflix.mediaclient.servicemgr.interface_.trackable.Trackable;
import android.view.ViewGroup;
import android.view.LayoutInflater;
import android.content.Context;
import com.netflix.mediaclient.ui.common.PlayContext;
import com.netflix.mediaclient.servicemgr.interface_.Video;
import com.netflix.mediaclient.ui.lomo.VideoViewGroup$IVideoView;
import android.widget.RelativeLayout;

class MiniPlayerControlsFrag$MementoRelatedView extends RelativeLayout implements VideoViewGroup$IVideoView<Video>
{
    PlayContext playContext;
    final /* synthetic */ MiniPlayerControlsFrag this$0;
    
    public MiniPlayerControlsFrag$MementoRelatedView(final MiniPlayerControlsFrag this$0, final Context context) {
        this.this$0 = this$0;
        super(context);
        this.playContext = PlayContext.EMPTY_CONTEXT;
        LayoutInflater.from(this.getContext()).inflate(2130903198, (ViewGroup)this, true);
    }
    
    private void adjustDimensions() {
        this.getLayoutParams().width = this.this$0.relatedViewWidth;
        this.getLayoutParams().height = this.this$0.relatedViewHeight;
    }
    
    public String getImageUrl(final Video video, final boolean b) {
        return video.getBoxshotUrl();
    }
    
    public PlayContext getPlayContext() {
        return PlayContext.NFLX_MDX_CONTEXT;
    }
    
    public void hide() {
    }
    
    public void update(final Video video, final Trackable trackable, int n, final boolean b, final boolean b2) {
        if (trackable != null) {
            this.playContext = new PlayContextImp(trackable, n);
        }
        final AdvancedImageView advancedImageView = (AdvancedImageView)this.findViewById(2131689984);
        if (advancedImageView != null) {
            final String imageUrl = this.getImageUrl(video, b2);
            final ImageLoader imageLoader = NetflixActivity.getImageLoader(this.getContext());
            final IClientLogging$AssetType boxArt = IClientLogging$AssetType.boxArt;
            final String title = video.getTitle();
            final ImageLoader$StaticImgConfig imageLoaderConfig = BrowseExperience.getImageLoaderConfig();
            if (b) {
                n = 1;
            }
            else {
                n = 0;
            }
            imageLoader.showImg(advancedImageView, imageUrl, boxArt, title, imageLoaderConfig, true, n);
            this.adjustDimensions();
            advancedImageView.setOnClickListener((View$OnClickListener)new MiniPlayerControlsFrag$MementoRelatedView$1(this, video));
        }
    }
}
