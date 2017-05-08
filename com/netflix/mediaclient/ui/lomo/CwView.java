// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.lomo;

import com.netflix.mediaclient.util.gfx.ImageLoader$StaticImgConfig;
import com.netflix.mediaclient.util.gfx.ImageLoader;
import com.netflix.mediaclient.servicemgr.interface_.Video;
import android.view.View$OnClickListener;
import com.netflix.mediaclient.servicemgr.interface_.Playable;
import com.netflix.mediaclient.servicemgr.ServiceManagerUtils;
import com.netflix.mediaclient.ui.experience.BrowseExperience;
import com.netflix.mediaclient.servicemgr.IClientLogging$AssetType;
import com.netflix.mediaclient.servicemgr.interface_.VideoType;
import com.netflix.mediaclient.ui.common.PlayContextImp;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.servicemgr.interface_.trackable.Trackable;
import com.netflix.mediaclient.ui.common.PlayContextProvider;
import android.view.View;
import android.view.ViewGroup;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import com.netflix.mediaclient.util.l10n.LocalizationUtils;
import android.util.AttributeSet;
import android.content.Context;
import android.widget.TextView;
import android.widget.ProgressBar;
import com.netflix.mediaclient.ui.common.PlayContext;
import android.widget.ImageView;
import com.netflix.mediaclient.android.widget.AdvancedImageView;
import com.netflix.mediaclient.android.widget.VideoDetailsClickListener;
import com.netflix.mediaclient.servicemgr.interface_.CWVideo;
import android.widget.RelativeLayout;

public class CwView extends RelativeLayout implements VideoViewGroup$IVideoView<CWVideo>
{
    public static final float CW_IMG_HEIGHT_TO_WIDTH_RATIO = 0.5625f;
    private static final String TAG = "CwView";
    private VideoDetailsClickListener clicker;
    private AdvancedImageView img;
    private ImageView info;
    private PlayContext playContext;
    private ProgressBar progress;
    private TextView title;
    
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
    
    private void init() {
        this.setFocusable(true);
        this.setBackgroundResource(2130837925);
        this.playContext = PlayContext.EMPTY_CONTEXT;
        final NetflixActivity netflixActivity = (NetflixActivity)this.getContext();
        netflixActivity.getLayoutInflater().inflate(2130903080, (ViewGroup)this);
        LocalizationUtils.setLayoutDirection((View)this);
        this.title = (TextView)this.findViewById(2131624151);
        this.img = (AdvancedImageView)this.findViewById(2131624149);
        this.progress = (ProgressBar)this.findViewById(2131624153);
        this.info = (ImageView)this.findViewById(2131624152);
        this.clicker = new VideoDetailsClickListener(netflixActivity, this);
    }
    
    private void setTitle(final CharSequence charSequence) {
        CharSequence string = charSequence;
        if (LocalizationUtils.isCurrentLocaleRTL()) {
            final StringBuilder sb = new StringBuilder();
            LocalizationUtils.addMarkerForRtLocale(sb, '\u200f');
            sb.append(charSequence);
            string = sb.toString();
        }
        this.title.setText(string);
    }
    
    public PlayContext getPlayContext() {
        return this.playContext;
    }
    
    public void hide() {
        NetflixActivity.getImageLoader(this.getContext()).clear(this.img);
        this.setVisibility(4);
        this.clicker.remove((View)this.info);
    }
    
    public void setInfoViewId(final int id) {
        this.info.setId(id);
    }
    
    public void update(final CWVideo cwVideo, final Trackable trackable, int progress, final boolean b, final boolean b2) {
        if (Log.isLoggable()) {
            Log.v("CwView", "Updating for video: " + cwVideo.toString());
        }
        this.playContext = new PlayContextImp(trackable, progress);
        this.setVisibility(0);
        final String format = String.format(this.getResources().getString(2131165354), cwVideo.getTitle());
        this.setContentDescription((CharSequence)format);
        if (VideoType.SHOW.equals(cwVideo.getType())) {
            if (cwVideo.isNSRE()) {
                this.setTitle(this.getContext().getString(2131165511, new Object[] { this.getText(cwVideo.getTitle()), this.getText(cwVideo.getCurrentEpisodeTitle()) }));
            }
            else {
                this.setTitle(this.getContext().getString(2131165510, new Object[] { this.getText(cwVideo.getTitle()), cwVideo.getSeasonAbbrSeqLabel(), cwVideo.getEpisodeNumber() }));
            }
        }
        else {
            this.setTitle(cwVideo.getTitle());
        }
        final ImageLoader imageLoader = NetflixActivity.getImageLoader(this.getContext());
        final AdvancedImageView img = this.img;
        final String modifiedStillUrl = cwVideo.createModifiedStillUrl();
        final IClientLogging$AssetType bif = IClientLogging$AssetType.bif;
        final ImageLoader$StaticImgConfig imageLoaderConfig = BrowseExperience.getImageLoaderConfig();
        if (b) {
            progress = 1;
        }
        else {
            progress = 0;
        }
        imageLoader.showImg(img, modifiedStillUrl, bif, format, imageLoaderConfig, true, progress);
        if (cwVideo.getRuntime() > 0) {
            progress = cwVideo.getPlayableBookmarkPosition() * 100 / cwVideo.getRuntime();
        }
        else {
            progress = 0;
        }
        this.progress.setProgress(progress);
        ServiceManagerUtils.castPrefetchAndCacheManifestIfEnabled(((NetflixActivity)this.getContext()).getServiceManager(), cwVideo, this.playContext);
        this.setOnClickListener((View$OnClickListener)new CwView$1(this, cwVideo));
        this.info.setContentDescription((CharSequence)String.format(this.getResources().getString(2131165512), cwVideo.getTitle()));
        this.clicker.update((View)this.info, cwVideo, this.img.getPressedStateHandler());
    }
}
