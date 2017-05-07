// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.kubrick.lomo;

import com.netflix.mediaclient.util.gfx.ImageLoader;
import com.netflix.mediaclient.servicemgr.model.Video;
import android.view.View$OnClickListener;
import com.netflix.mediaclient.util.TimeUtils;
import com.netflix.mediaclient.servicemgr.model.VideoType;
import com.netflix.mediaclient.ui.common.PlayContextImp;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.servicemgr.model.trackable.Trackable;
import com.netflix.mediaclient.servicemgr.IClientLogging$AssetType;
import com.netflix.mediaclient.ui.common.PlayContextProvider;
import android.view.ViewGroup;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import android.util.AttributeSet;
import android.content.Context;
import android.widget.TextView;
import android.widget.ProgressBar;
import com.netflix.mediaclient.ui.common.PlayContext;
import android.view.View;
import com.netflix.mediaclient.android.widget.AdvancedImageView;
import com.netflix.mediaclient.android.widget.VideoDetailsClickListener;
import com.netflix.mediaclient.servicemgr.model.CWVideo;
import com.netflix.mediaclient.ui.lomo.VideoViewGroup$IVideoView;
import android.widget.RelativeLayout;

public class KubrickCwView extends RelativeLayout implements VideoViewGroup$IVideoView<CWVideo>
{
    public static final float CW_IMG_HEIGHT_TO_WIDTH_RATIO = 0.5625f;
    private static final String TAG = "KubrickCwView";
    private VideoDetailsClickListener clicker;
    private AdvancedImageView img;
    private View infoIcon;
    private View infoViewGroup;
    private PlayContext playContext;
    private View playViewGroup;
    private ProgressBar progress;
    private TextView subtitle;
    private TextView title;
    
    public KubrickCwView(final Context context) {
        super(context);
        this.init();
    }
    
    public KubrickCwView(final Context context, final AttributeSet set) {
        super(context, set);
        this.init();
    }
    
    public KubrickCwView(final Context context, final AttributeSet set, final int n) {
        super(context, set, n);
        this.init();
    }
    
    private void init() {
        this.setFocusable(true);
        this.setBackgroundResource(2130837848);
        this.playContext = PlayContext.EMPTY_CONTEXT;
        final NetflixActivity netflixActivity = (NetflixActivity)this.getContext();
        netflixActivity.getLayoutInflater().inflate(2130903116, (ViewGroup)this);
        this.playViewGroup = this.findViewById(2131165428);
        this.infoViewGroup = this.findViewById(2131165426);
        this.title = (TextView)this.findViewById(2131165345);
        this.subtitle = (TextView)this.findViewById(2131165427);
        this.img = (AdvancedImageView)this.findViewById(2131165342);
        this.progress = (ProgressBar)this.findViewById(2131165347);
        this.infoIcon = this.findViewById(2131165346);
        this.clicker = new VideoDetailsClickListener(netflixActivity, this);
    }
    
    public PlayContext getPlayContext() {
        return this.playContext;
    }
    
    public void hide() {
        NetflixActivity.getImageLoader(this.getContext()).showImg(this.img, null, IClientLogging$AssetType.bif, null, false, false);
        this.setVisibility(4);
        this.clicker.remove(this.infoViewGroup);
    }
    
    public void setInfoViewId(final int id) {
        this.infoIcon.setId(id);
    }
    
    public void update(final CWVideo cwVideo, final Trackable trackable, int progress, final boolean b, final boolean b2) {
        if (Log.isLoggable("KubrickCwView", 2)) {
            Log.v("KubrickCwView", "Updating for video: " + cwVideo.getTitle());
        }
        this.playContext = new PlayContextImp(trackable, progress);
        this.setVisibility(0);
        final String format = String.format(this.getResources().getString(2131493166), cwVideo.getTitle());
        this.playViewGroup.setContentDescription((CharSequence)format);
        this.title.setText((CharSequence)cwVideo.getTitle());
        if (VideoType.SHOW.equals(cwVideo.getType())) {
            this.subtitle.setText((CharSequence)this.getContext().getString(2131493229, new Object[] { cwVideo.getSeasonNumber(), cwVideo.getEpisodeNumber(), cwVideo.getCurrentEpisodeTitle() }));
        }
        else {
            this.subtitle.setText((CharSequence)this.getResources().getString(2131493159, new Object[] { TimeUtils.convertSecondsToMinutes(cwVideo.getRuntime()) }));
        }
        final ImageLoader imageLoader = NetflixActivity.getImageLoader(this.getContext());
        final AdvancedImageView img = this.img;
        final String modifiedStillUrl = cwVideo.getModifiedStillUrl();
        final IClientLogging$AssetType bif = IClientLogging$AssetType.bif;
        if (b) {
            progress = 1;
        }
        else {
            progress = 0;
        }
        imageLoader.showImg(img, modifiedStillUrl, bif, format, true, true, progress);
        if (cwVideo.getRuntime() > 0) {
            progress = cwVideo.getPlayableBookmarkPosition() * 100 / cwVideo.getRuntime();
        }
        else {
            progress = 0;
        }
        this.progress.setProgress(progress);
        this.playViewGroup.setOnClickListener((View$OnClickListener)new KubrickCwView$1(this, cwVideo));
        this.infoViewGroup.setContentDescription((CharSequence)String.format(this.getResources().getString(2131493203), cwVideo.getTitle()));
        this.clicker.update(this.infoViewGroup, cwVideo);
    }
}
