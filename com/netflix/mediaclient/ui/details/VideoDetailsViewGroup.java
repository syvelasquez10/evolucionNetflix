// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.details;

import android.text.Html;
import com.netflix.mediaclient.util.StringUtils;
import com.netflix.mediaclient.servicemgr.Playable;
import com.netflix.mediaclient.ui.common.PlaybackLauncher;
import com.netflix.mediaclient.ui.common.PlayContextProvider;
import android.view.View$OnClickListener;
import java.util.List;
import com.netflix.mediaclient.servicemgr.IClientLogging;
import android.widget.ImageView$ScaleType;
import com.netflix.mediaclient.servicemgr.FriendProfile;
import android.widget.LinearLayout$LayoutParams;
import com.netflix.mediaclient.servicemgr.VideoDetails;
import android.view.ViewGroup$LayoutParams;
import android.widget.FrameLayout$LayoutParams;
import com.netflix.mediaclient.Log;
import android.view.View;
import com.netflix.mediaclient.util.ViewUtils;
import android.view.ViewTreeObserver$OnGlobalLayoutListener;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import android.app.Activity;
import android.view.LayoutInflater;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.content.Context;
import android.view.ViewGroup;
import com.netflix.mediaclient.android.widget.AdvancedImageView;
import android.graphics.drawable.Drawable;
import android.widget.TextView;
import android.widget.Button;
import android.widget.LinearLayout;

public class VideoDetailsViewGroup extends LinearLayout
{
    private static final String TAG = "VideoDetailsViewGroup";
    private Button addToMyList;
    private TextView basicInfo;
    private TextView creators;
    private Drawable hdDrawable;
    private int hdDrawablePadding;
    private AdvancedImageView horzDispImg;
    private ViewGroup imgGroup;
    private NetflixRatingBar ratingBar;
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
        final Drawable drawable = this.getResources().getDrawable(2130837580);
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
        return this.getResources().getDimensionPixelSize(2131361819);
    }
    
    private void init() {
        LayoutInflater.from(this.getContext()).inflate(2130903127, (ViewGroup)this, true);
        this.setOrientation(1);
        this.imgGroup = (ViewGroup)this.findViewById(2131099932);
        this.horzDispImg = (AdvancedImageView)this.findViewById(2131099923);
        this.ratingBar = (NetflixRatingBar)this.findViewById(2131099926);
        this.title = (TextView)this.findViewById(2131099934);
        this.basicInfo = (TextView)this.findViewById(2131099925);
        this.synopsis = (TextView)this.findViewById(2131099929);
        this.starring = (TextView)this.findViewById(2131099930);
        this.creators = (TextView)this.findViewById(2131099931);
        (this.addToMyList = (Button)this.findViewById(2131099924)).setEnabled(false);
        this.socialGroup = (LinearLayout)this.findViewById(2131099928);
        this.socialTitle = (TextView)this.findViewById(2131099927);
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
        this.getViewTreeObserver().addOnGlobalLayoutListener((ViewTreeObserver$OnGlobalLayoutListener)new ViewTreeObserver$OnGlobalLayoutListener() {
            public void onGlobalLayout() {
                ViewUtils.removeGlobalLayoutListener((View)VideoDetailsViewGroup.this, (ViewTreeObserver$OnGlobalLayoutListener)this);
                Log.v("VideoDetailsViewGroup", "img group width: " + VideoDetailsViewGroup.this.imgGroup.getWidth() + ", height: " + VideoDetailsViewGroup.this.imgGroup.getHeight());
                final FrameLayout$LayoutParams layoutParams = (FrameLayout$LayoutParams)VideoDetailsViewGroup.this.horzDispImg.getLayoutParams();
                layoutParams.height = (int)(VideoDetailsViewGroup.this.imgGroup.getWidth() * 0.562f);
                VideoDetailsViewGroup.this.horzDispImg.setLayoutParams((ViewGroup$LayoutParams)layoutParams);
            }
        });
    }
    
    private void updateSocialGroup(final VideoDetails videoDetails) {
        final List<FriendProfile> facebookFriends = videoDetails.getFacebookFriends();
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
            visibility2 = 8;
        }
        else {
            visibility2 = 0;
        }
        socialTitle.setVisibility(visibility2);
        if (b) {
            Log.v("VideoDetailsViewGroup", "No friends available for this detail view");
        }
        else {
            this.socialTitle.setText((CharSequence)this.getResources().getQuantityString(2131558401, facebookFriends.size(), new Object[] { facebookFriends.size() }));
            final int dimensionPixelOffset = this.getResources().getDimensionPixelOffset(2131361854);
            final LinearLayout$LayoutParams linearLayout$LayoutParams = new LinearLayout$LayoutParams(dimensionPixelOffset, dimensionPixelOffset);
            final int dimensionPixelOffset2 = this.getResources().getDimensionPixelOffset(2131361855);
            linearLayout$LayoutParams.topMargin = dimensionPixelOffset2;
            linearLayout$LayoutParams.bottomMargin = dimensionPixelOffset2;
            final int n = dimensionPixelOffset2 / 2;
            linearLayout$LayoutParams.leftMargin = n;
            linearLayout$LayoutParams.rightMargin = n;
            this.socialGroup.removeAllViews();
            for (int min = Math.min(facebookFriends.size(), 9), i = 0; i < min; ++i) {
                final FriendProfile friendProfile = facebookFriends.get(i);
                Log.v("VideoDetailsViewGroup", "Updating img for friend: " + friendProfile.getFirstName() + ", url: " + friendProfile.getImageUrl());
                final AdvancedImageView advancedImageView = new AdvancedImageView(this.getContext());
                advancedImageView.setAdjustViewBounds(true);
                advancedImageView.setScaleType(ImageView$ScaleType.FIT_CENTER);
                this.socialGroup.addView((View)advancedImageView, (ViewGroup$LayoutParams)linearLayout$LayoutParams);
                NetflixActivity.getImageLoader(this.getContext()).showImg(advancedImageView, friendProfile.getImageUrl(), IClientLogging.AssetType.profileAvatar, friendProfile.getFullName(), false, true);
            }
        }
    }
    
    public TextView getAddToMyListButton() {
        return (TextView)this.addToMyList;
    }
    
    public void refreshImagesIfNecessary() {
        if (this.horzDispImg != null) {
            this.horzDispImg.refreshImageIfNecessary();
        }
    }
    
    public void updateDetails(final VideoDetails details, final DetailsStringProvider detailsStringProvider) {
        final NetflixActivity netflixActivity = (NetflixActivity)this.getContext();
        NetflixActivity.getImageLoader((Context)netflixActivity).showImg(this.horzDispImg, details.getHorzDispUrl(), IClientLogging.AssetType.boxArt, String.format(this.getResources().getString(2131493124), details.getTitle()), true, true);
        this.horzDispImg.requestFocus();
        this.horzDispImg.setOnClickListener((View$OnClickListener)new View$OnClickListener() {
            public void onClick(final View view) {
                PlaybackLauncher.startPlayback(netflixActivity, details, ((PlayContextProvider)netflixActivity).getPlayContext());
            }
        });
        this.title.setText((CharSequence)details.getTitle());
        this.basicInfo.setText(detailsStringProvider.getBasicInfoString());
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
        this.starring.setText(detailsStringProvider.getStarringText());
        if (StringUtils.isNotEmpty(details.getCreators())) {
            this.creators.setText(detailsStringProvider.getCreatorsText());
            this.creators.setVisibility(0);
        }
        else {
            this.creators.setVisibility(8);
        }
        this.updateSocialGroup(details);
    }
    
    public interface DetailsStringProvider
    {
        CharSequence getBasicInfoString();
        
        CharSequence getCreatorsText();
        
        CharSequence getStarringText();
    }
}
