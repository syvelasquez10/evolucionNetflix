// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.player;

import com.netflix.mediaclient.util.gfx.ImageLoader$StaticImgConfig;
import com.netflix.mediaclient.servicemgr.IClientLogging$AssetType;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import com.netflix.model.leafs.PostPlayItem;
import android.util.AttributeSet;
import android.content.Context;
import com.netflix.mediaclient.android.widget.AdvancedImageView;

public class PostPlayBackgroundMetadata extends PostPlayBackground
{
    private PostPlayBackgroundBasic backgound;
    private AdvancedImageView logo;
    private PostPlayMetadata metadata;
    
    public PostPlayBackgroundMetadata(final Context context) {
        super(context, null);
    }
    
    public PostPlayBackgroundMetadata(final Context context, final AttributeSet set) {
        super(context, set);
    }
    
    @Override
    protected void findViews() {
        this.backgound = (PostPlayBackgroundBasic)this.findViewById(2131690154);
        this.metadata = (PostPlayMetadata)this.findViewById(2131690186);
        this.logo = (AdvancedImageView)this.findViewById(2131690159);
    }
    
    @Override
    public void startBackgroundAutoPan() {
        if (this.backgound != null) {
            this.backgound.startBackgroundAutoPan();
        }
    }
    
    @Override
    protected void startTimer() {
        if (this.metadata != null) {
            this.metadata.startTimer();
        }
    }
    
    @Override
    protected void stopTimer() {
        if (this.metadata != null) {
            this.metadata.stopTimer();
        }
    }
    
    @Override
    public void updateViews(final PostPlayItem postPlayItem, final NetflixActivity netflixActivity, final PlayerFragment playerFragment, final PostPlayRequestContext postPlayRequestContext) {
        this.backgound.updateViews(postPlayItem, netflixActivity, playerFragment, postPlayRequestContext);
        this.metadata.updateViews(postPlayItem, netflixActivity, playerFragment, postPlayRequestContext);
        if (postPlayItem.getLogoAsset() != null && postPlayItem.getLogoAsset().getUrl() != null) {
            NetflixActivity.getImageLoader((Context)netflixActivity).showImg(this.logo, postPlayItem.getLogoAsset().getUrl(), IClientLogging$AssetType.merchStill, postPlayItem.getAncestorTitle(), ImageLoader$StaticImgConfig.DARK_NO_PLACEHOLDER, true, 1);
        }
    }
}
