// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.details;

import com.netflix.mediaclient.util.gfx.ImageLoader;
import android.text.Html;
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
import android.app.Activity;
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
    private TextView basicInfo;
    private TextView creators;
    private Drawable hdDrawable;
    private int hdDrawablePadding;
    private AdvancedImageView horzDispImg;
    private ViewGroup imgGroup;
    private NetflixRatingBar ratingBar;
    private Button recommend;
    private LinearLayout socialGroup;
    private TextView socialTitle;
    private TextView starring;
    private TextView synopsis;
    private TextView title;
    
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
        final Drawable drawable = this.getResources().getDrawable(2130837658);
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
        return this.getResources().getDimensionPixelSize(2131361862);
    }
    
    private void init() {
        LayoutInflater.from(this.getContext()).inflate(2130903200, (ViewGroup)this, true);
        this.setOrientation(1);
        this.addView(this.actionBarDummyView = ViewUtils.createActionBarDummyView((NetflixActivity)this.getContext()), 0);
        this.imgGroup = (ViewGroup)this.findViewById(2131165684);
        this.horzDispImg = (AdvancedImageView)this.findViewById(2131165676);
        this.ratingBar = (NetflixRatingBar)this.findViewById(2131165589);
        this.title = (TextView)this.findViewById(2131165677);
        this.basicInfo = (TextView)this.findViewById(2131165588);
        this.synopsis = (TextView)this.findViewById(2131165681);
        this.starring = (TextView)this.findViewById(2131165682);
        this.creators = (TextView)this.findViewById(2131165683);
        (this.addToMyList = (Button)this.findViewById(2131165673)).setEnabled(false);
        this.recommend = (Button)this.findViewById(2131165672);
        this.socialGroup = (LinearLayout)this.findViewById(2131165680);
        this.socialTitle = (TextView)this.findViewById(2131165679);
        this.hdDrawablePadding = this.getHdDrawablePadding();
        this.setImgLayoutListener();
    }
    
    private boolean isDeviceHd(final Activity activity) {
        return ((NetflixActivity)activity).getServiceManager() != null && ((NetflixActivity)activity).getServiceManager().isDeviceHd();
    }
    
    private void setHdIcon() {
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
            this.socialTitle.setText(2131493195);
            final int dimensionPixelOffset = this.getResources().getDimensionPixelOffset(2131361905);
            final LinearLayout$LayoutParams linearLayout$LayoutParams = new LinearLayout$LayoutParams(dimensionPixelOffset, dimensionPixelOffset);
            final int dimensionPixelOffset2 = this.getResources().getDimensionPixelOffset(2131361906);
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
    
    public TextView getAddToMyListButton() {
        return (TextView)this.addToMyList;
    }
    
    public TextView getRecommendButton() {
        return (TextView)this.recommend;
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
    
    public void updateDetails(final VideoDetails details, final VideoDetailsViewGroup$DetailsStringProvider videoDetailsViewGroup$DetailsStringProvider) {
        final NetflixActivity netflixActivity = (NetflixActivity)this.getContext();
        final String format = String.format(this.getResources().getString(2131493159), details.getTitle());
        final ImageLoader imageLoader = NetflixActivity.getImageLoader((Context)netflixActivity);
        final AdvancedImageView horzDispImg = this.horzDispImg;
        String s;
        if (DeviceUtils.isTabletByContext(this.getContext())) {
            s = details.getHorzDispUrl();
        }
        else {
            s = details.getStoryDispUrl();
        }
        imageLoader.showImg(horzDispImg, s, IClientLogging$AssetType.boxArt, format, true, true);
        this.horzDispImg.requestFocus();
        this.horzDispImg.setOnClickListener((View$OnClickListener)new VideoDetailsViewGroup$2(this, netflixActivity, details));
        this.title.setText((CharSequence)details.getTitle());
        this.basicInfo.setText(videoDetailsViewGroup$DetailsStringProvider.getBasicInfoString());
        if (this.isDeviceHd(netflixActivity) && details.isVideoHd()) {
            if (this.hdDrawable == null) {
                this.hdDrawable = this.buildHdDrawable();
            }
            this.setHdIcon();
        }
        this.ratingBar.setDetails(details);
        final String synopsis = details.getSynopsis();
        final TextView synopsis2 = this.synopsis;
        Object fromHtml;
        if (StringUtils.isEmpty(synopsis)) {
            fromHtml = "";
        }
        else {
            fromHtml = Html.fromHtml(details.getSynopsis());
        }
        synopsis2.setText((CharSequence)fromHtml);
        this.starring.setText(videoDetailsViewGroup$DetailsStringProvider.getStarringText());
        final CharSequence creatorsText = videoDetailsViewGroup$DetailsStringProvider.getCreatorsText();
        if (StringUtils.isEmpty(creatorsText)) {
            this.creators.setVisibility(8);
        }
        else {
            this.creators.setText(creatorsText);
            this.creators.setVisibility(0);
        }
        this.updateSocialGroup(details);
    }
}
