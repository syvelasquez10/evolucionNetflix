// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.lomo;

import com.netflix.mediaclient.util.gfx.ImageLoader$StaticImgConfig;
import com.netflix.mediaclient.util.gfx.ImageLoader;
import android.view.View$OnClickListener;
import com.netflix.mediaclient.servicemgr.interface_.Playable;
import com.netflix.mediaclient.servicemgr.ServiceManagerUtils;
import com.netflix.mediaclient.servicemgr.IClientLogging$AssetType;
import com.netflix.mediaclient.ui.common.PlayContextImp;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.servicemgr.interface_.trackable.Trackable;
import com.netflix.mediaclient.servicemgr.interface_.VideoType;
import com.netflix.mediaclient.ui.common.PlayContextProvider;
import android.view.ViewGroup;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import com.netflix.mediaclient.servicemgr.interface_.Video;
import com.netflix.mediaclient.ui.experience.BrowseExperience;
import com.netflix.mediaclient.util.l10n.LocalizationUtils;
import android.util.AttributeSet;
import android.content.Context;
import android.widget.TextView;
import android.widget.ProgressBar;
import com.netflix.mediaclient.ui.common.PlayContext;
import android.view.View;
import com.netflix.mediaclient.android.widget.AdvancedImageView;
import com.netflix.mediaclient.android.widget.VideoDetailsClickListener;
import com.netflix.mediaclient.servicemgr.interface_.CWVideo;
import android.widget.RelativeLayout;

public class CwView extends RelativeLayout implements VideoViewGroup$IVideoView<CWVideo>
{
    public static final float CW_IMG_HEIGHT_TO_WIDTH_RATIO = 0.5625f;
    private static final String TAG = "CwView";
    protected VideoDetailsClickListener clicker;
    protected AdvancedImageView img;
    protected View info;
    protected PlayContext playContext;
    protected ProgressBar progress;
    protected TextView title;
    
    public CwView(final Context context) {
        super(context);
        this.init();
    }
    
    public CwView(final Context context, final AttributeSet set) {
        super(context, set);
        this.init();
    }
    
    public CwView(final Context context, final AttributeSet set, final int n) {
        super(context, set, n);
        this.init();
    }
    
    public static int calculateProgress(final int n, final int n2) {
        if (n > 0) {
            return n2 * 100 / n;
        }
        return 0;
    }
    
    private String getText(final String s) {
        String string = s;
        if (s != null) {
            string = s;
            if (LocalizationUtils.isCurrentLocaleRTL()) {
                string = '\u200f' + s;
            }
        }
        return string;
    }
    
    public String getImageUrl(final CWVideo cwVideo, final boolean b) {
        return BrowseExperience.getLomoVideoViewImageUrl(this.getContext(), cwVideo, CwView.class, 0);
    }
    
    protected int getLayoutId() {
        return 2130903093;
    }
    
    public PlayContext getPlayContext() {
        return this.playContext;
    }
    
    public void hide() {
        NetflixActivity.getImageLoader(this.getContext()).clear(this.img);
        this.setVisibility(4);
        this.clicker.remove(this.info);
    }
    
    protected void init() {
        this.setFocusable(true);
        this.setBackgroundResource(2130838134);
        this.playContext = PlayContext.EMPTY_CONTEXT;
        final NetflixActivity netflixActivity = (NetflixActivity)this.getContext();
        netflixActivity.getLayoutInflater().inflate(this.getLayoutId(), (ViewGroup)this);
        LocalizationUtils.setLayoutDirection((View)this);
        this.title = (TextView)this.findViewById(2131755301);
        this.img = (AdvancedImageView)this.findViewById(2131755298);
        this.progress = (ProgressBar)this.findViewById(2131755300);
        this.info = this.findViewById(2131755302);
        this.clicker = new VideoDetailsClickListener(netflixActivity, this);
    }
    
    public void setInfoViewId(final int id) {
        this.info.setId(id);
    }
    
    public void setTitle(final CWVideo cwVideo) {
        if (!VideoType.SHOW.equals(cwVideo.getType())) {
            this.setTitle(cwVideo.getTitle());
            return;
        }
        if (cwVideo.isNSRE()) {
            this.setTitle(this.getContext().getString(2131296618, new Object[] { this.getText(cwVideo.getTitle()), this.getText(cwVideo.getCurrentEpisodeTitle()) }));
            return;
        }
        this.setTitle(this.getContext().getString(2131296617, new Object[] { this.getText(cwVideo.getTitle()), cwVideo.getSeasonAbbrSeqLabel(), cwVideo.getEpisodeNumber() }));
    }
    
    protected void setTitle(final CharSequence charSequence) {
        CharSequence string = charSequence;
        if (LocalizationUtils.isCurrentLocaleRTL()) {
            final StringBuilder sb = new StringBuilder();
            LocalizationUtils.addMarkerForRtLocale(sb, '\u200f');
            sb.append(charSequence);
            string = sb.toString();
        }
        this.title.setText(string);
    }
    
    public void update(final CWVideo title, final Trackable trackable, int calculateProgress, final boolean b, final boolean b2) {
        if (Log.isLoggable()) {
            Log.v("CwView", "Updating for video: " + title.toString());
        }
        this.playContext = new PlayContextImp(trackable, calculateProgress);
        this.setVisibility(0);
        final String format = String.format(this.getResources().getString(2131296447), title.getTitle());
        this.setContentDescription((CharSequence)format);
        this.setTitle(title);
        final String imageUrl = this.getImageUrl(title, b2);
        final ImageLoader imageLoader = NetflixActivity.getImageLoader(this.getContext());
        final AdvancedImageView img = this.img;
        final IClientLogging$AssetType bif = IClientLogging$AssetType.bif;
        final ImageLoader$StaticImgConfig imageLoaderConfig = BrowseExperience.getImageLoaderConfig();
        if (b) {
            calculateProgress = 1;
        }
        else {
            calculateProgress = 0;
        }
        imageLoader.showImg(img, imageUrl, bif, format, imageLoaderConfig, true, calculateProgress);
        calculateProgress = calculateProgress(title.getRuntime(), title.getPlayableBookmarkPosition());
        this.progress.setProgress(calculateProgress);
        ServiceManagerUtils.castPrefetchAndCacheManifestIfEnabled(((NetflixActivity)this.getContext()).getServiceManager(), title, this.playContext);
        this.setOnClickListener((View$OnClickListener)new CwView$1(this, title));
        this.info.setContentDescription((CharSequence)String.format(this.getResources().getString(2131296619), title.getTitle()));
        this.clicker.update(this.info, title, this.img.getPressedStateHandler());
    }
}
