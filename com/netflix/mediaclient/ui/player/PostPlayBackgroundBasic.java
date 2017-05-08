// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.player;

import android.view.View;
import com.netflix.mediaclient.android.widget.AdvancedImageView;
import com.netflix.mediaclient.util.gfx.ImageLoader$StaticImgConfig;
import com.netflix.mediaclient.servicemgr.IClientLogging$AssetType;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import com.netflix.model.leafs.PostPlayItem;
import android.animation.TimeInterpolator;
import com.netflix.mediaclient.util.DeviceUtils;
import android.util.AttributeSet;
import android.content.Context;
import android.widget.ImageView;
import android.view.animation.DecelerateInterpolator;
import com.netflix.mediaclient.android.widget.TopCropImageView;

public class PostPlayBackgroundBasic extends PostPlayBackground
{
    private static final int DEFAULT_OFFSET_MS = 10000;
    private static final double SIXTY_PERCENT = 0.6;
    protected TopCropImageView background;
    protected int mOffsetMs;
    private final DecelerateInterpolator mPanAnimationInterpolator;
    private PostPlayCallToAction playAction;
    protected ImageView playButton;
    
    public PostPlayBackgroundBasic(final Context context) {
        this(context, null);
    }
    
    public PostPlayBackgroundBasic(final Context context, final AttributeSet set) {
        super(context, set);
        this.mPanAnimationInterpolator = new DecelerateInterpolator();
        this.mOffsetMs = 10000;
    }
    
    @Override
    protected void findViews() {
        this.background = (TopCropImageView)this.findViewById(2131755724);
        this.playButton = (ImageView)this.findViewById(2131755728);
    }
    
    @Override
    public void startBackgroundAutoPan() {
        if (this.netflixActivity != null && this.background != null && !DeviceUtils.isLandscape((Context)this.netflixActivity) && this.background.getMeasuredWidth() == 0) {
            this.background.getLayoutParams().height = (int)(DeviceUtils.getScreenHeightInPixels((Context)this.netflixActivity) * 0.6);
            this.background.getLayoutParams().width = (int)(this.background.getLayoutParams().height * 1.778f);
            this.background.animate().setStartDelay(1000L).setDuration((long)this.mOffsetMs).x((float)(this.background.getLayoutParams().height - this.background.getLayoutParams().width)).setInterpolator((TimeInterpolator)this.mPanAnimationInterpolator);
        }
    }
    
    @Override
    protected void startTimer() {
    }
    
    @Override
    protected void stopTimer() {
    }
    
    @Override
    public void updateViews(final PostPlayItem postPlayItem, final NetflixActivity netflixActivity, final PlayerFragment playerFragment, final PostPlayRequestContext postPlayRequestContext) {
        this.netflixActivity = netflixActivity;
        if (this.background != null && postPlayRequestContext != null) {
            this.background.setCutomCroppingEnabled(postPlayRequestContext.equals(PostPlayRequestContext.MDX) && DeviceUtils.isLandscape((Context)netflixActivity));
        }
        if (postPlayItem.getBackgroundAsset() != null && postPlayItem.getBackgroundAsset().getUrl() != null) {
            NetflixActivity.getImageLoader((Context)netflixActivity).showImg(this.background, postPlayItem.getBackgroundAsset().getUrl(), IClientLogging$AssetType.merchStill, String.format(netflixActivity.getResources().getString(2131296449), postPlayItem.getTitle()), ImageLoader$StaticImgConfig.DARK, true, 1);
        }
        if (this.playButton != null && postPlayItem.getPlayAction() != null && playerFragment != null) {
            this.playAction = new PostPlayCallToAction(netflixActivity, playerFragment, postPlayItem.getPlayAction(), postPlayRequestContext, (View)this.playButton);
            this.playButton.setVisibility(0);
        }
    }
}
