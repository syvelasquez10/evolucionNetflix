// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.lomo;

import com.netflix.mediaclient.servicemgr.ServiceManager;
import com.netflix.mediaclient.service.browse.BrowseAgent$BillboardActivityType;
import android.view.View$OnClickListener;
import com.netflix.mediaclient.servicemgr.model.Playable;
import com.netflix.mediaclient.servicemgr.ServiceManagerUtils;
import com.netflix.mediaclient.servicemgr.model.VideoType;
import com.netflix.mediaclient.servicemgr.model.Video;
import android.text.Html;
import com.netflix.mediaclient.util.api.Api17Util;
import com.netflix.mediaclient.util.StringUtils;
import com.netflix.mediaclient.util.SocialUtils;
import com.netflix.mediaclient.ui.common.PlayContextImp;
import com.netflix.mediaclient.servicemgr.model.trackable.Trackable;
import com.netflix.mediaclient.service.logging.client.model.Error;
import com.netflix.mediaclient.util.log.SocialLoggingUtils;
import com.netflix.mediaclient.servicemgr.IClientLogging$ModalView;
import com.netflix.mediaclient.servicemgr.IClientLogging$AssetType;
import com.netflix.mediaclient.util.log.ConsolidatedLoggingUtils;
import android.view.ViewTreeObserver$OnGlobalLayoutListener;
import android.widget.RelativeLayout$LayoutParams;
import com.netflix.mediaclient.util.DeviceUtils;
import com.netflix.mediaclient.Log;
import android.view.ViewGroup;
import com.netflix.mediaclient.ui.common.PlayContextProvider;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import android.util.AttributeSet;
import com.netflix.mediaclient.util.log.UIViewLogUtils;
import android.content.Context;
import com.netflix.mediaclient.ui.common.PlayContext;
import android.view.View;
import android.widget.TextView;
import com.netflix.mediaclient.android.widget.AdvancedImageView;
import com.netflix.mediaclient.android.widget.VideoDetailsClickListener;
import com.netflix.mediaclient.servicemgr.model.Billboard;
import android.widget.RelativeLayout;

public class BillboardView extends RelativeLayout implements VideoViewGroup$IVideoView<Billboard>
{
    private static final String TAG = "BillboardView";
    private VideoDetailsClickListener detailsListener;
    private AdvancedImageView friendAvatar;
    private TextView friendRecommendationText;
    private TextView info;
    private View infoButton;
    private View infoViewGroup;
    private boolean isTablet;
    private TextView label;
    private String mGUID;
    private String mStoryId;
    private View playButton;
    private PlayContext playContext;
    private View postcardShadowOverlayGradient;
    private View shadowOverlay;
    private View shadowOverlayGradient;
    private boolean showArtworkOnly;
    private AdvancedImageView storyArt;
    private TextView synopsis;
    private AdvancedImageView tvCard;
    
    public BillboardView(final Context context) {
        super(context);
        this.playContext = PlayContext.EMPTY_CONTEXT;
        this.isTablet = true;
        this.mGUID = UIViewLogUtils.MISSING_GUID;
        this.init();
    }
    
    public BillboardView(final Context context, final AttributeSet set) {
        super(context, set);
        this.playContext = PlayContext.EMPTY_CONTEXT;
        this.isTablet = true;
        this.mGUID = UIViewLogUtils.MISSING_GUID;
        this.init();
    }
    
    public BillboardView(final Context context, final AttributeSet set, final int n) {
        super(context, set, n);
        this.playContext = PlayContext.EMPTY_CONTEXT;
        this.isTablet = true;
        this.mGUID = UIViewLogUtils.MISSING_GUID;
        this.init();
    }
    
    private void init() {
        this.setFocusable(true);
        final NetflixActivity netflixActivity = (NetflixActivity)this.getContext();
        this.detailsListener = new VideoDetailsClickListener(netflixActivity, this);
        netflixActivity.getLayoutInflater().inflate(2130903070, (ViewGroup)this);
        if (netflixActivity.getServiceManager() != null) {
            this.isTablet = netflixActivity.getServiceManager().isTablet();
        }
        this.showArtworkOnly = shouldShowArtworkOnly(netflixActivity);
        if (Log.isLoggable("BillboardView", 2)) {
            Log.v("BillboardView", "isTablet: " + this.isTablet + ", showArtworkOnly: " + this.showArtworkOnly);
        }
        this.infoViewGroup = this.findViewById(2131165306);
        this.label = (TextView)this.findViewById(2131165307);
        this.info = (TextView)this.findViewById(2131165309);
        this.synopsis = (TextView)this.findViewById(2131165310);
        this.tvCard = (AdvancedImageView)this.findViewById(2131165308);
        this.friendAvatar = (AdvancedImageView)this.findViewById(2131165312);
        this.friendRecommendationText = (TextView)this.findViewById(2131165313);
        this.postcardShadowOverlayGradient = this.findViewById(2131165305);
        this.storyArt = (AdvancedImageView)this.findViewById(2131165302);
        int n;
        if (this.showArtworkOnly) {
            n = 0;
        }
        else {
            n = DeviceUtils.getScreenWidthInPixels((Context)netflixActivity) / 3;
        }
        this.storyArt.setPadding(n, 0, 0, 0);
        this.shadowOverlay = this.findViewById(2131165303);
        ((RelativeLayout$LayoutParams)this.shadowOverlay.getLayoutParams()).width = n;
        this.shadowOverlayGradient = this.findViewById(2131165304);
        ((RelativeLayout$LayoutParams)this.shadowOverlayGradient.getLayoutParams()).leftMargin = n;
        this.playButton = this.findViewById(2131165314);
        this.infoButton = this.findViewById(2131165315);
        this.updateViewVisibility();
        this.getViewTreeObserver().addOnGlobalLayoutListener((ViewTreeObserver$OnGlobalLayoutListener)new BillboardView$1(this));
    }
    
    public static boolean shouldShowArtworkOnly(final NetflixActivity netflixActivity) {
        boolean b;
        if (DeviceUtils.getScreenResolutionDpi((Context)netflixActivity) <= 240) {
            b = true;
        }
        else {
            b = false;
        }
        final boolean b2 = netflixActivity.getServiceManager() == null || netflixActivity.getServiceManager().isTablet();
        return (b && DeviceUtils.isPortrait((Context)netflixActivity)) || (!b2 && DeviceUtils.isPortrait((Context)netflixActivity));
    }
    
    private void updateViewVisibility() {
        final boolean b = false;
        final View infoViewGroup = this.infoViewGroup;
        int visibility;
        if (this.showArtworkOnly) {
            visibility = 8;
        }
        else {
            visibility = 0;
        }
        infoViewGroup.setVisibility(visibility);
        final View shadowOverlay = this.shadowOverlay;
        int visibility2;
        if (this.showArtworkOnly) {
            visibility2 = 8;
        }
        else {
            visibility2 = 0;
        }
        shadowOverlay.setVisibility(visibility2);
        final View shadowOverlayGradient = this.shadowOverlayGradient;
        int visibility3;
        if (this.showArtworkOnly) {
            visibility3 = 8;
        }
        else {
            visibility3 = 0;
        }
        shadowOverlayGradient.setVisibility(visibility3);
        this.storyArt.setVisibility(0);
        final TextView label = this.label;
        int visibility4;
        if (this.isTablet) {
            visibility4 = 8;
        }
        else {
            visibility4 = 0;
        }
        label.setVisibility(visibility4);
        final TextView synopsis = this.synopsis;
        int visibility5;
        if (this.isTablet) {
            visibility5 = (b ? 1 : 0);
        }
        else {
            visibility5 = 8;
        }
        synopsis.setVisibility(visibility5);
    }
    
    public String getGUID() {
        if (this.mGUID == UIViewLogUtils.MISSING_GUID) {
            this.mGUID = ConsolidatedLoggingUtils.createGUID();
        }
        return this.mGUID;
    }
    
    public PlayContext getPlayContext() {
        return this.playContext;
    }
    
    public void hide() {
        NetflixActivity.getImageLoader(this.getContext()).showImg(this.tvCard, null, IClientLogging$AssetType.boxArt, null, false, false);
        NetflixActivity.getImageLoader(this.getContext()).showImg(this.storyArt, null, IClientLogging$AssetType.merchStill, null, false, false);
        this.setVisibility(4);
        this.detailsListener.remove((View)this.storyArt);
    }
    
    protected void onWindowVisibilityChanged(int n) {
        super.onWindowVisibilityChanged(n);
        int n2;
        if (this.mStoryId != null) {
            n2 = 1;
        }
        else {
            n2 = 0;
        }
        if (n2 == 0) {
            return;
        }
        if (n == 0) {
            n = UIViewLogUtils.MISSING_TRACK_ID;
            if (this.playContext != null) {
                n = this.playContext.getTrackId();
            }
            SocialLoggingUtils.reportStartSocialImpressionSession(this.getContext(), IClientLogging$ModalView.socialFeedbackPostcard, this.getGUID(), this.mStoryId, n);
            return;
        }
        SocialLoggingUtils.reportEndSocialImpressionSession(this.getContext(), true, null);
    }
    
    public void update(final Billboard billboard, final Trackable trackable, int n, final boolean b, final boolean b2) {
        final ServiceManager serviceManager = ((NetflixActivity)this.getContext()).getServiceManager();
        this.playContext = new PlayContextImp(trackable, n);
        if (Log.isLoggable("BillboardView", 2)) {
            Log.v("BillboardView", "updating billboard, title: " + billboard.getTitle() + ", story url: " + billboard.getStoryUrl());
        }
        this.setVisibility(0);
        final String format = String.format(this.getResources().getString(2131493166), billboard.getTitle());
        this.setContentDescription((CharSequence)format);
        if (billboard.getSocialBadge() != null && SocialUtils.isNotificationsFeatureSupported((NetflixActivity)this.getContext()) && StringUtils.isNotEmpty(billboard.getSocialBadge().getFullName())) {
            n = 1;
        }
        else {
            n = 0;
        }
        if (n != 0) {
            this.infoViewGroup.setVisibility(0);
            this.label.setVisibility(8);
            this.info.setVisibility(8);
            final RelativeLayout$LayoutParams relativeLayout$LayoutParams = (RelativeLayout$LayoutParams)this.infoViewGroup.getLayoutParams();
            Api17Util.removeRelativeLayoutParamsRule(relativeLayout$LayoutParams, 15);
            relativeLayout$LayoutParams.addRule(12, -1);
            relativeLayout$LayoutParams.width = -1;
            this.postcardShadowOverlayGradient.setVisibility(0);
            this.playButton.setVisibility(8);
            this.infoButton.setVisibility(8);
            this.friendAvatar.setVisibility(0);
            NetflixActivity.getImageLoader(this.getContext()).showImg(this.friendAvatar, billboard.getSocialBadge().getImageUrl(), IClientLogging$AssetType.merchStill, format, true, true, 1);
            this.friendRecommendationText.setVisibility(0);
            this.friendRecommendationText.setText((CharSequence)Html.fromHtml(this.getContext().getString(2131493377, new Object[] { billboard.getSocialBadge().getFullName(), billboard.getTitle() })));
            this.mStoryId = billboard.getSocialBadge().getStoryId();
        }
        if (this.showArtworkOnly) {
            this.storyArt.setPressedStateHandlerEnabled(true);
            this.detailsListener.update((View)this.storyArt, billboard);
            String s;
            if (this.isTablet) {
                s = billboard.getHighResolutionLandscapeBoxArtUrl();
            }
            else {
                s = billboard.getHorzDispUrl();
            }
            if (Log.isLoggable("BillboardView", 2)) {
                Log.v("BillboardView", "Showing artwork only, image url: " + s);
            }
            NetflixActivity.getImageLoader(this.getContext()).showImg(this.storyArt, s, IClientLogging$AssetType.merchStill, format, true, true, 1);
        }
        else {
            if (Log.isLoggable("BillboardView", 2)) {
                Log.v("BillboardView", "Showing billboard with action buttons, image url: " + billboard.getStoryUrl());
            }
            NetflixActivity.getImageLoader(this.getContext()).showImg(this.storyArt, billboard.getStoryUrl(), IClientLogging$AssetType.merchStill, format, true, true, 1);
            NetflixActivity.getImageLoader(this.getContext()).showImg(this.tvCard, billboard.getTvCardUrl(), IClientLogging$AssetType.boxArt, format, false, true, 1);
            if (n != 0) {
                this.storyArt.setPressedStateHandlerEnabled(true);
                this.detailsListener.update((View)this.storyArt, billboard);
            }
            else {
                this.storyArt.setPressedStateHandlerEnabled(false);
                this.detailsListener.remove((View)this.storyArt);
                final TextView info = this.info;
                CharSequence text;
                if (billboard.getType() == VideoType.MOVIE) {
                    text = StringUtils.getBasicMovieInfoString(this.getContext(), billboard.getYear(), billboard.getCertification(), billboard.getRuntime());
                }
                else {
                    text = StringUtils.getBasicShowInfoString(this.getContext(), billboard.getYear(), billboard.getCertification(), billboard.getNumOfSeasons());
                }
                info.setText(text);
                this.synopsis.setText((CharSequence)billboard.getSynopsis());
                ServiceManagerUtils.cacheManifestIfEnabled(serviceManager, billboard);
                this.playButton.setOnClickListener((View$OnClickListener)new BillboardView$2(this, serviceManager, billboard));
                this.infoButton.setOnClickListener((View$OnClickListener)new BillboardView$3(this, serviceManager, billboard));
            }
        }
        if (serviceManager != null && serviceManager.isReady()) {
            Log.v("BillboardView", "Loggin billboard impression for video: " + billboard.getId());
            serviceManager.getBrowse().logBillboardActivity(billboard, BrowseAgent$BillboardActivityType.IMPRESSION);
        }
    }
}
