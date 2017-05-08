// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.player;

import com.netflix.mediaclient.util.gfx.ImageLoader$StaticImgConfig;
import com.netflix.mediaclient.servicemgr.IClientLogging$AssetType;
import android.view.View$OnClickListener;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import com.netflix.model.leafs.PostPlayItem;
import android.util.AttributeSet;
import android.content.Context;
import android.widget.ImageView;
import com.netflix.mediaclient.android.widget.AdvancedImageView;

public class PostPlayItemBasic extends PostPlayItemView
{
    private AdvancedImageView displayArt;
    private ImageView playButton;
    
    public PostPlayItemBasic(final Context context) {
        super(context, null);
    }
    
    public PostPlayItemBasic(final Context context, final AttributeSet set) {
        super(context, set);
    }
    
    @Override
    protected void findViews() {
        this.displayArt = (AdvancedImageView)this.findViewById(2131755747);
        this.playButton = (ImageView)this.findViewById(2131755728);
    }
    
    @Override
    protected void startTimer(final int n) {
    }
    
    @Override
    protected void stopTimer() {
    }
    
    @Override
    public void updateViews(final PostPlayItem postPlayItem, final NetflixActivity netflixActivity, final PlayerFragment playerFragment, final PostPlayRequestContext postPlayRequestContext, final View$OnClickListener onClickListener) {
        if (postPlayItem.getDisplayArtAsset() != null && postPlayItem.getDisplayArtAsset().getUrl() != null) {
            NetflixActivity.getImageLoader((Context)netflixActivity).showImg(this.displayArt, postPlayItem.getDisplayArtAsset().getUrl(), IClientLogging$AssetType.merchStill, String.format(netflixActivity.getResources().getString(2131296449), postPlayItem.getTitle()), ImageLoader$StaticImgConfig.LIGHT_NO_PLACEHOLDER, true, 1);
        }
        if (onClickListener == null) {
            this.playButton.setVisibility(8);
            return;
        }
        this.displayArt.setOnClickListener(onClickListener);
        if (!postPlayItem.getExperienceType().equals("recommendations")) {
            this.playButton.setVisibility(0);
            return;
        }
        this.playButton.setVisibility(8);
    }
}
