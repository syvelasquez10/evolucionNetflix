// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.details;

import android.text.Html;
import com.netflix.mediaclient.util.gfx.ImageLoader;
import com.netflix.mediaclient.servicemgr.model.HdEnabledProvider;
import com.netflix.mediaclient.servicemgr.model.Ratable;
import com.netflix.mediaclient.util.StringUtils;
import android.view.View$OnClickListener;
import java.util.List;
import com.netflix.mediaclient.servicemgr.IClientLogging$AssetType;
import android.view.ViewGroup$LayoutParams;
import android.widget.ImageView$ScaleType;
import com.netflix.mediaclient.servicemgr.model.user.FriendProfile;
import com.netflix.mediaclient.util.DeviceUtils;
import android.widget.LinearLayout$LayoutParams;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.servicemgr.model.FriendProfilesProvider;
import com.netflix.mediaclient.servicemgr.model.details.VideoDetails;
import android.view.ViewTreeObserver$OnGlobalLayoutListener;
import com.netflix.mediaclient.util.ViewUtils;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import android.view.LayoutInflater;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.content.Context;
import android.view.ViewGroup;
import com.netflix.mediaclient.android.widget.AdvancedImageView;
import android.graphics.drawable.Drawable;
import android.widget.TextView;
import android.widget.Button;
import android.view.View;
import android.widget.LinearLayout;

public class VideoDetailsViewGroup extends LinearLayout
{
    private static final int MAX_SOCIAL_BITMAP_IMGS_LANDSCAPE = 4;
    private static final int MAX_SOCIAL_BITMAP_IMGS_PORTRAIT = 3;
    private static final String TAG = "VideoDetailsViewGroup";
    private View actionBarDummyView;
    private Button addToMyList;
    protected TextView basicInfo;
    private TextView creators;
    private Drawable hdDrawable;
    private int hdDrawablePadding;
    protected AdvancedImageView horzDispImg;
    private ViewGroup imgGroup;
    protected NetflixRatingBar ratingBar;
    private Button recommend;
    protected TextView relatedTitle;
    private LinearLayout socialGroup;
    private TextView socialTitle;
    private TextView starring;
    protected TextView synopsis;
    protected TextView title;
    
    public VideoDetailsViewGroup(final Context context) {
        super(context);
        this.init();
    }
    
    public VideoDetailsViewGroup(final Context context, final AttributeSet set) {
        super(context, set);
        this.init();
    }
    
    public VideoDetailsViewGroup(final Context context, final AttributeSet set, final int n) {
        super(context, set, n);
        this.init();
    }
    
    private Drawable buildHdDrawable() {
        if (this.basicInfo == null) {
            return null;
        }
        final Drawable drawable = this.getResources().getDrawable(2130837662);
        double n;
        if (drawable.getIntrinsicHeight() > 0) {
            n = this.basicInfo.getHeight() * drawable.getIntrinsicWidth() / drawable.getIntrinsicHeight();
        }
        else {
            n = this.basicInfo.getHeight() * 1.3;
        }
        drawable.setBounds(new Rect(0, 0, (int)n, this.basicInfo.getHeight()));
        return drawable;
    }
    
    private int getHdDrawablePadding() {
        return this.getResources().getDimensionPixelSize(2131361879);
    }
    
    private void init() {
        LayoutInflater.from(this.getContext()).inflate(this.getlayoutId(), (ViewGroup)this, true);
        this.setOrientation(1);
        this.addView(this.actionBarDummyView = ViewUtils.createActionBarDummyView((NetflixActivity)this.getContext()), 0);
        this.findViews();
        if (this.addToMyList != null) {
            this.addToMyList.setEnabled(false);
        }
        this.hdDrawablePadding = this.getHdDrawablePadding();
        this.setImgLayoutListener();
    }
    
    private void setHdIcon() {
        if (this.basicInfo == null) {
            return;
        }
        this.basicInfo.setCompoundDrawablePadding(this.hdDrawablePadding);
        this.basicInfo.setCompoundDrawables((Drawable)null, (Drawable)null, this.hdDrawable, (Drawable)null);
    }
    
    private void setImgLayoutListener() {
        this.getViewTreeObserver().addOnGlobalLayoutListener((ViewTreeObserver$OnGlobalLayoutListener)new VideoDetailsViewGroup$1(this));
    }
    
    private void updateSocialGroup(final VideoDetails videoDetails) {
        final int n = 8;
        List<FriendProfile> facebookFriends;
        if (videoDetails instanceof FriendProfilesProvider) {
            facebookFriends = ((FriendProfilesProvider)videoDetails).getFacebookFriends();
        }
        else {
            facebookFriends = null;
        }
        boolean b;
        if (facebookFriends == null || facebookFriends.size() <= 0) {
            b = true;
        }
        else {
            b = false;
        }
        final LinearLayout socialGroup = this.socialGroup;
        int visibility;
        if (b) {
            visibility = 8;
        }
        else {
            visibility = 0;
        }
        socialGroup.setVisibility(visibility);
        final TextView socialTitle = this.socialTitle;
        int visibility2;
        if (b) {
            visibility2 = n;
        }
        else {
            visibility2 = 0;
        }
        socialTitle.setVisibility(visibility2);
        if (b) {
            Log.v("VideoDetailsViewGroup", "No friends available for this detail view");
        }
        else {
            this.socialTitle.setText(2131493202);
            final int dimensionPixelOffset = this.getResources().getDimensionPixelOffset(2131361923);
            final LinearLayout$LayoutParams linearLayout$LayoutParams = new LinearLayout$LayoutParams(dimensionPixelOffset, dimensionPixelOffset);
            final int dimensionPixelOffset2 = this.getResources().getDimensionPixelOffset(2131361924);
            linearLayout$LayoutParams.topMargin = dimensionPixelOffset2;
            linearLayout$LayoutParams.bottomMargin = dimensionPixelOffset2;
            final int n2 = dimensionPixelOffset2 / 2;
            linearLayout$LayoutParams.leftMargin = n2;
            linearLayout$LayoutParams.rightMargin = n2;
            this.socialGroup.removeAllViews();
            final int size = facebookFriends.size();
            int n3;
            if (DeviceUtils.isPortrait(this.getContext())) {
                n3 = 3;
            }
            else {
                n3 = 4;
            }
            for (int min = Math.min(size, n3), i = 0; i < min; ++i) {
                final FriendProfile friendProfile = facebookFriends.get(i);
                if (Log.isLoggable("VideoDetailsViewGroup", 2)) {
                    Log.v("VideoDetailsViewGroup", "Updating img for friend: " + friendProfile.getFirstName() + ", url: " + friendProfile.getImageUrl());
                }
                final AdvancedImageView advancedImageView = new AdvancedImageView(this.getContext());
                advancedImageView.setAdjustViewBounds(true);
                advancedImageView.setScaleType(ImageView$ScaleType.FIT_CENTER);
                this.socialGroup.addView((View)advancedImageView, (ViewGroup$LayoutParams)linearLayout$LayoutParams);
                NetflixActivity.getImageLoader(this.getContext()).showImg(advancedImageView, friendProfile.getImageUrl(), IClientLogging$AssetType.profileAvatar, friendProfile.getFullName(), false, true);
            }
        }
    }
    
    protected void alignViews() {
        final ViewGroup$LayoutParams layoutParams = this.horzDispImg.getLayoutParams();
        layoutParams.height = this.calculateImageHeight();
        this.horzDispImg.setLayoutParams(layoutParams);
    }
    
    protected int calculateImageHeight() {
        int n;
        if ((n = this.imgGroup.getMeasuredWidth()) <= 0) {
            n = DeviceUtils.getScreenWidthInPixels(this.getContext());
        }
        return (int)(n * 0.5625f);
    }
    
    protected void findViews() {
        this.ratingBar = (NetflixRatingBar)this.findViewById(2131165419);
        this.socialGroup = (LinearLayout)this.findViewById(2131165690);
        this.socialTitle = (TextView)this.findViewById(2131165689);
        this.addToMyList = (Button)this.findViewById(2131165685);
        this.basicInfo = (TextView)this.findViewById(2131165604);
        this.recommend = (Button)this.findViewById(2131165684);
        this.synopsis = (TextView)this.findViewById(2131165460);
        this.starring = (TextView)this.findViewById(2131165449);
        this.creators = (TextView)this.findViewById(2131165450);
        this.horzDispImg = (AdvancedImageView)this.findViewById(2131165461);
        this.title = (TextView)this.findViewById(2131165466);
        this.imgGroup = (ViewGroup)this.findViewById(2131165691);
        this.relatedTitle = (TextView)this.findViewById(2131165468);
    }
    
    public TextView getAddToMyListButton() {
        return (TextView)this.addToMyList;
    }
    
    public TextView getAddToMyListButtonLabel() {
        return null;
    }
    
    public AdvancedImageView getHeroImage() {
        return this.horzDispImg;
    }
    
    public TextView getRecommendButton() {
        return (TextView)this.recommend;
    }
    
    public TextView getRecommendButtonLabel() {
        return null;
    }
    
    protected int getlayoutId() {
        return 2130903209;
    }
    
    public void hideRelatedTitle() {
        if (this.relatedTitle != null) {
            this.relatedTitle.setVisibility(4);
        }
    }
    
    public void refreshImagesIfNecessary() {
        if (this.horzDispImg != null) {
            this.horzDispImg.refreshImageIfNecessary();
        }
    }
    
    public void removeActionBarDummyView() {
        if (this.actionBarDummyView != null) {
            this.removeView(this.actionBarDummyView);
            this.actionBarDummyView = null;
        }
    }
    
    public void removeRelatedTitle() {
        if (this.relatedTitle != null) {
            this.relatedTitle.setVisibility(8);
        }
    }
    
    protected void setupImageClicks(final VideoDetails videoDetails, final NetflixActivity netflixActivity) {
        this.horzDispImg.requestFocus();
        this.horzDispImg.setOnClickListener((View$OnClickListener)new VideoDetailsViewGroup$2(this, netflixActivity, videoDetails));
    }
    
    public void showRelatedTitle() {
        if (this.relatedTitle != null) {
            this.relatedTitle.setVisibility(0);
        }
    }
    
    protected void updateBasicInfo(final VideoDetailsViewGroup$DetailsStringProvider videoDetailsViewGroup$DetailsStringProvider) {
        this.basicInfo.setText(videoDetailsViewGroup$DetailsStringProvider.getBasicInfoString());
    }
    
    protected void updateCredits(final VideoDetailsViewGroup$DetailsStringProvider videoDetailsViewGroup$DetailsStringProvider) {
        this.starring.setText(videoDetailsViewGroup$DetailsStringProvider.getStarringText());
        final CharSequence creatorsText = videoDetailsViewGroup$DetailsStringProvider.getCreatorsText();
        if (StringUtils.isEmpty(creatorsText)) {
            this.creators.setVisibility(8);
            return;
        }
        this.creators.setText(creatorsText);
        this.creators.setVisibility(0);
    }
    
    public void updateDetails(final VideoDetails video, final VideoDetailsViewGroup$DetailsStringProvider videoDetailsViewGroup$DetailsStringProvider) {
        final NetflixActivity netflixActivity = (NetflixActivity)this.getContext();
        this.updateImage(video, netflixActivity, String.format(this.getResources().getString(2131493166), video.getTitle()));
        this.updateRelatedTitle(video);
        this.updateTitle(video);
        this.updateBasicInfo(videoDetailsViewGroup$DetailsStringProvider);
        this.updateHD(video, netflixActivity);
        this.ratingBar.setVideo(video);
        this.updateSynopsis(video);
        this.updateCredits(videoDetailsViewGroup$DetailsStringProvider);
        this.updateSocialGroup(video);
    }
    
    protected void updateHD(final VideoDetails videoDetails, final NetflixActivity netflixActivity) {
        if (ViewUtils.shouldShowHdIcon(netflixActivity, videoDetails)) {
            if (this.hdDrawable == null) {
                this.hdDrawable = this.buildHdDrawable();
            }
            this.setHdIcon();
        }
    }
    
    protected void updateImage(final VideoDetails videoDetails, final NetflixActivity netflixActivity, final String s) {
        final ImageLoader imageLoader = NetflixActivity.getImageLoader((Context)netflixActivity);
        final AdvancedImageView horzDispImg = this.horzDispImg;
        String s2;
        if (DeviceUtils.isTabletByContext((Context)netflixActivity)) {
            s2 = videoDetails.getHorzDispUrl();
        }
        else {
            s2 = videoDetails.getStoryDispUrl();
        }
        imageLoader.showImg(horzDispImg, s2, IClientLogging$AssetType.boxArt, s, true, true);
        this.setupImageClicks(videoDetails, netflixActivity);
    }
    
    protected void updateRelatedTitle(final VideoDetails videoDetails) {
        if (this.relatedTitle != null) {
            this.relatedTitle.setText((CharSequence)this.relatedTitle.getResources().getString(2131493152, new Object[] { videoDetails.getTitle() }));
        }
    }
    
    protected void updateSynopsis(final VideoDetails videoDetails) {
        final String synopsis = videoDetails.getSynopsis();
        final TextView synopsis2 = this.synopsis;
        Object fromHtml;
        if (StringUtils.isEmpty(synopsis)) {
            fromHtml = "";
        }
        else {
            fromHtml = Html.fromHtml(videoDetails.getSynopsis());
        }
        synopsis2.setText((CharSequence)fromHtml);
    }
    
    protected void updateTitle(final VideoDetails videoDetails) {
        if (this.title != null) {
            this.title.setText((CharSequence)videoDetails.getTitle());
        }
    }
}
