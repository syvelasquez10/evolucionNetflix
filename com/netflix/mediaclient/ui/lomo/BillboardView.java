// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.lomo;

import com.netflix.mediaclient.servicemgr.Playable;
import com.netflix.mediaclient.ui.details.DetailsActivity;
import com.netflix.mediaclient.ui.common.PlaybackLauncher;
import com.netflix.mediaclient.service.browse.BrowseAgent;
import com.netflix.mediaclient.servicemgr.ServiceManager;
import android.view.View$OnClickListener;
import com.netflix.mediaclient.servicemgr.ShowDetails;
import com.netflix.mediaclient.servicemgr.VideoDetails;
import com.netflix.mediaclient.util.StringUtils;
import com.netflix.mediaclient.servicemgr.VideoType;
import com.netflix.mediaclient.servicemgr.Video;
import com.netflix.mediaclient.ui.common.PlayContextImp;
import com.netflix.mediaclient.servicemgr.Trackable;
import com.netflix.mediaclient.servicemgr.IClientLogging;
import com.netflix.mediaclient.util.ViewUtils;
import android.view.ViewTreeObserver$OnGlobalLayoutListener;
import android.widget.RelativeLayout$LayoutParams;
import com.netflix.mediaclient.util.DeviceUtils;
import com.netflix.mediaclient.Log;
import android.view.ViewGroup;
import com.netflix.mediaclient.ui.common.PlayContextProvider;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import android.util.AttributeSet;
import android.content.Context;
import com.netflix.mediaclient.android.widget.AdvancedImageView;
import com.netflix.mediaclient.ui.common.PlayContext;
import android.view.View;
import android.widget.TextView;
import com.netflix.mediaclient.android.widget.VideoDetailsClickListener;
import com.netflix.mediaclient.service.webclient.model.BillboardDetails;
import android.widget.RelativeLayout;

public class BillboardView extends RelativeLayout implements IVideoView<BillboardDetails>
{
    private static final String TAG = "BillboardView";
    private VideoDetailsClickListener detailsListener;
    private TextView info;
    private View infoButton;
    private View infoViewGroup;
    private boolean isTablet;
    private TextView label;
    private View playButton;
    private PlayContext playContext;
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
        this.init();
    }
    
    public BillboardView(final Context context, final AttributeSet set) {
        super(context, set);
        this.playContext = PlayContext.EMPTY_CONTEXT;
        this.isTablet = true;
        this.init();
    }
    
    public BillboardView(final Context context, final AttributeSet set, final int n) {
        super(context, set, n);
        this.playContext = PlayContext.EMPTY_CONTEXT;
        this.isTablet = true;
        this.init();
    }
    
    private void init() {
        this.setFocusable(true);
        final NetflixActivity netflixActivity = (NetflixActivity)this.getContext();
        this.detailsListener = new VideoDetailsClickListener(netflixActivity, this);
        netflixActivity.getLayoutInflater().inflate(2130903066, (ViewGroup)this);
        if (netflixActivity.getServiceManager() != null) {
            this.isTablet = netflixActivity.getServiceManager().isTablet();
        }
        this.showArtworkOnly = shouldShowArtworkOnly(netflixActivity);
        if (Log.isLoggable("BillboardView", 2)) {
            Log.v("BillboardView", "isTablet: " + this.isTablet + ", showArtworkOnly: " + this.showArtworkOnly);
        }
        this.infoViewGroup = this.findViewById(2131165294);
        this.label = (TextView)this.findViewById(2131165295);
        this.info = (TextView)this.findViewById(2131165297);
        this.synopsis = (TextView)this.findViewById(2131165298);
        this.tvCard = (AdvancedImageView)this.findViewById(2131165296);
        this.storyArt = (AdvancedImageView)this.findViewById(2131165291);
        int n;
        if (this.showArtworkOnly) {
            n = 0;
        }
        else {
            n = DeviceUtils.getScreenWidthInPixels((Context)netflixActivity) / 3;
        }
        this.storyArt.setPadding(n, 0, 0, 0);
        this.shadowOverlay = this.findViewById(2131165292);
        ((RelativeLayout$LayoutParams)this.shadowOverlay.getLayoutParams()).width = n;
        this.shadowOverlayGradient = this.findViewById(2131165293);
        ((RelativeLayout$LayoutParams)this.shadowOverlayGradient.getLayoutParams()).leftMargin = n;
        this.playButton = this.findViewById(2131165300);
        this.infoButton = this.findViewById(2131165301);
        this.updateViewVisibility();
        this.getViewTreeObserver().addOnGlobalLayoutListener((ViewTreeObserver$OnGlobalLayoutListener)new ViewTreeObserver$OnGlobalLayoutListener() {
            public void onGlobalLayout() {
                if (!((NetflixActivity)BillboardView.this.getContext()).destroyed()) {
                    if (Log.isLoggable("BillboardView", 2)) {
                        Log.v("BillboardView", "vg height: " + BillboardView.this.infoViewGroup.getHeight() + ", h: " + BillboardView.this.getHeight());
                    }
                    if (BillboardView.this.getHeight() > 0 && BillboardView.this.infoViewGroup.getHeight() >= BillboardView.this.getHeight()) {
                        Log.d("BillboardView", "Info view group is larger than view height - hiding some text");
                        BillboardView.this.label.setVisibility(8);
                        BillboardView.this.info.setVisibility(8);
                    }
                    if (BillboardView.this.getHeight() > 0) {
                        ViewUtils.removeGlobalLayoutListener((View)BillboardView.this, (ViewTreeObserver$OnGlobalLayoutListener)this);
                    }
                }
            }
        });
    }
    
    public static boolean shouldShowArtworkOnly(final NetflixActivity netflixActivity) {
        boolean tablet = true;
        if (netflixActivity.getServiceManager() != null) {
            tablet = netflixActivity.getServiceManager().isTablet();
        }
        return !tablet && DeviceUtils.isPortrait((Context)netflixActivity);
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
    
    public PlayContext getPlayContext() {
        return this.playContext;
    }
    
    public void hide() {
        NetflixActivity.getImageLoader(this.getContext()).showImg(this.tvCard, null, IClientLogging.AssetType.boxArt, null, false, false);
        NetflixActivity.getImageLoader(this.getContext()).showImg(this.storyArt, null, IClientLogging.AssetType.merchStill, null, false, false);
        this.setVisibility(4);
        this.detailsListener.remove((View)this.storyArt);
    }
    
    public void update(final BillboardDetails billboardDetails, final Trackable trackable, final int n, final boolean b) {
        final ServiceManager serviceManager = ((NetflixActivity)this.getContext()).getServiceManager();
        this.playContext = new PlayContextImp(trackable, n);
        if (Log.isLoggable("BillboardView", 2)) {
            Log.v("BillboardView", "updating billboard, title: " + billboardDetails.getTitle() + ", story url: " + billboardDetails.getStoryUrl());
        }
        this.setVisibility(0);
        final String format = String.format(this.getResources().getString(2131493186), billboardDetails.getTitle());
        this.setContentDescription((CharSequence)format);
        if (this.showArtworkOnly) {
            this.storyArt.setPressedStateHandlerEnabled(true);
            this.detailsListener.update((View)this.storyArt, billboardDetails);
            NetflixActivity.getImageLoader(this.getContext()).showImg(this.storyArt, billboardDetails.getHorzDispUrl(), IClientLogging.AssetType.merchStill, format, true, true, 1);
        }
        else {
            this.storyArt.setPressedStateHandlerEnabled(false);
            this.detailsListener.remove((View)this.storyArt);
            NetflixActivity.getImageLoader(this.getContext()).showImg(this.storyArt, billboardDetails.getStoryUrl(), IClientLogging.AssetType.merchStill, format, true, true, 1);
            NetflixActivity.getImageLoader(this.getContext()).showImg(this.tvCard, billboardDetails.getTvCardUrl(), IClientLogging.AssetType.boxArt, format, false, true, 1);
            final TextView info = this.info;
            CharSequence text;
            if (billboardDetails.getType() == VideoType.MOVIE) {
                text = StringUtils.getBasicInfoString(this.getContext(), (VideoDetails)billboardDetails);
            }
            else {
                text = StringUtils.getBasicInfoString(this.getContext(), billboardDetails);
            }
            info.setText(text);
            this.synopsis.setText((CharSequence)billboardDetails.getSynopsis());
            this.playButton.setOnClickListener((View$OnClickListener)new View$OnClickListener() {
                public void onClick(final View view) {
                    if (serviceManager != null && serviceManager.isReady()) {
                        serviceManager.logBillboardActivity(billboardDetails, BrowseAgent.BillboardActivityType.ACTION);
                    }
                    PlaybackLauncher.getDetailsAndStartPlayback((NetflixActivity)BillboardView.this.getContext(), billboardDetails, BillboardView.this.playContext);
                }
            });
            this.infoButton.setOnClickListener((View$OnClickListener)new View$OnClickListener() {
                public void onClick(final View view) {
                    if (serviceManager != null && serviceManager.isReady()) {
                        serviceManager.logBillboardActivity(billboardDetails, BrowseAgent.BillboardActivityType.ACTION);
                    }
                    DetailsActivity.show((NetflixActivity)BillboardView.this.getContext(), billboardDetails, BillboardView.this.playContext);
                }
            });
        }
        if (serviceManager != null && serviceManager.isReady()) {
            Log.v("BillboardView", "Loggin billboard impression for video: " + billboardDetails.getId());
            serviceManager.logBillboardActivity(billboardDetails, BrowseAgent.BillboardActivityType.IMPRESSION);
        }
    }
}
