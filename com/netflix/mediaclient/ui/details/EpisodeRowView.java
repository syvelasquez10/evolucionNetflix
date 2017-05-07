// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.details;

import com.netflix.mediaclient.android.widget.ErrorWrapper$Callback;
import com.netflix.mediaclient.util.TimeUtils;
import com.netflix.mediaclient.servicemgr.model.trackable.Trackable;
import android.view.View$OnClickListener;
import com.netflix.mediaclient.util.StringUtils;
import com.netflix.mediaclient.ui.common.PlayContextProvider;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.ui.common.PlayContext;
import com.netflix.mediaclient.servicemgr.model.details.EpisodeDetails;
import android.view.ViewGroup;
import android.app.Activity;
import android.content.Context;
import android.widget.TextView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.ImageView;
import com.netflix.mediaclient.servicemgr.model.Video;
import com.netflix.mediaclient.ui.lomo.VideoViewGroup$IVideoView;
import android.widget.Checkable;
import android.widget.RelativeLayout;

public class EpisodeRowView extends RelativeLayout implements Checkable, VideoViewGroup$IVideoView<Video>
{
    private static final String TAG = "EpisodeRowView";
    protected boolean checked;
    private boolean isCurrentEpisode;
    private ImageView playButton;
    private ProgressBar progressBar;
    private int progressVal;
    private int resId;
    protected View rowHeader;
    protected TextView synopsis;
    protected TextView title;
    
    public EpisodeRowView(final Context context) {
        super(context);
        this.resId = 2130903092;
        this.init();
    }
    
    public EpisodeRowView(final Context context, final int resId) {
        super(context);
        this.resId = 2130903092;
        this.resId = resId;
        this.init();
    }
    
    private CharSequence createTitleText(final int n, final int n2) {
        return this.createTitleText(n, this.getResources().getString(n2));
    }
    
    private CharSequence createTitleText(final int n, final String s) {
        return this.getResources().getString(2131493226, new Object[] { n, s });
    }
    
    private void init() {
        ((Activity)this.getContext()).getLayoutInflater().inflate(this.resId, (ViewGroup)this);
        this.findViews();
        this.setTag(2131165257, (Object)true);
    }
    
    protected CharSequence createTitleText(final EpisodeDetails episodeDetails, final String s) {
        return this.getResources().getString(2131493226, new Object[] { episodeDetails.getEpisodeNumber(), s });
    }
    
    protected void findViews() {
        this.title = (TextView)this.findViewById(2131165363);
        this.synopsis = (TextView)this.findViewById(2131165365);
        this.playButton = (ImageView)this.findViewById(2131165361);
        this.progressBar = (ProgressBar)this.findViewById(2131165364);
        this.rowHeader = this.findViewById(2131165360);
    }
    
    protected int getDefaultSynopsisVisibility() {
        return 8;
    }
    
    public PlayContext getPlayContext() {
        return null;
    }
    
    public void handleItemClick() {
        if (this.playButton != null) {
            this.playButton.performClick();
        }
    }
    
    public void hide() {
    }
    
    public boolean isChecked() {
        return this.checked;
    }
    
    protected void playEpisode(final EpisodeDetails episodeDetails) {
        if (!(this.getContext() instanceof EpisodeRowView$EpisodeRowListenerProvider)) {
            Log.w("EpisodeRowView", "Context is not an EpisodeRowListenerProvider, context: " + this.getContext());
            return;
        }
        final EpisodeRowView$EpisodeRowListener episodeRowListener = ((EpisodeRowView$EpisodeRowListenerProvider)this.getContext()).getEpisodeRowListener();
        if (episodeRowListener != null) {
            PlayContext playContext = PlayContext.EMPTY_CONTEXT;
            if (this.getContext() instanceof PlayContextProvider) {
                playContext = ((PlayContextProvider)this.getContext()).getPlayContext();
            }
            episodeRowListener.onEpisodeSelectedForPlayback(episodeDetails, playContext);
            return;
        }
        Log.w("EpisodeRowView", "No EpisodeRowListener provided: " + this.getContext());
    }
    
    public void setChecked(final boolean checked) {
        this.checked = checked;
        final TextView synopsis = this.synopsis;
        int visibility;
        if (checked && StringUtils.isNotEmpty(this.synopsis.getText().toString())) {
            visibility = 0;
        }
        else {
            visibility = 8;
        }
        synopsis.setVisibility(visibility);
        this.setCheckedProgressBar();
    }
    
    protected void setCheckedProgressBar() {
        if (this.progressVal <= 0) {
            this.progressBar.setVisibility(8);
            return;
        }
        this.progressBar.setVisibility(0);
        if (this.isCurrentEpisode) {
            this.progressBar.setProgress(this.progressVal);
            this.progressBar.setSecondaryProgress(0);
            return;
        }
        this.progressBar.setProgress(0);
        this.progressBar.setSecondaryProgress(this.progressVal);
    }
    
    protected void setupPlayButton(final EpisodeDetails episodeDetails) {
        if (this.playButton == null) {
            return;
        }
        this.playButton.setVisibility(0);
        this.playButton.setOnClickListener((View$OnClickListener)new EpisodeRowView$2(this, episodeDetails));
    }
    
    public void toggle() {
        this.setChecked(!this.checked);
    }
    
    public void update(final Video video, final Trackable trackable, int max, final boolean b, final boolean b2) {
        final EpisodeDetails episodeDetails = (EpisodeDetails)video;
        if (episodeDetails == null) {
            return;
        }
        this.setContentDescription((CharSequence)String.format(this.getResources().getString(2131493172), episodeDetails.getEpisodeNumber(), episodeDetails.getTitle(), episodeDetails.getSynopsis(), TimeUtils.convertSecondsToMinutes(episodeDetails.getPlayable().getRuntime())));
        this.title.setText(this.createTitleText(episodeDetails, episodeDetails.getTitle()));
        this.title.setClickable(false);
        this.synopsis.setText((CharSequence)episodeDetails.getSynopsis());
        this.synopsis.setVisibility(this.getDefaultSynopsisVisibility());
        max = Math.max(0, episodeDetails.getBookmarkPosition());
        if (episodeDetails.getPlayable().getRuntime() > 0) {
            max = max * 100 / episodeDetails.getPlayable().getRuntime();
        }
        else {
            max = 0;
        }
        this.progressVal = max;
        this.setupPlayButton(episodeDetails);
        this.setChecked(false);
    }
    
    public void updateToErrorState(int n, final ErrorWrapper$Callback errorWrapper$Callback) {
        ++n;
        this.setContentDescription((CharSequence)String.format(this.getResources().getString(2131493171), n));
        this.title.setText(this.createTitleText(n, 2131492990));
        this.title.setClickable(true);
        this.title.setOnClickListener((View$OnClickListener)new EpisodeRowView$1(this, errorWrapper$Callback));
        this.synopsis.setText((CharSequence)"");
        this.synopsis.setVisibility(this.getDefaultSynopsisVisibility());
        this.setChecked(false);
    }
    
    public void updateToLoadingState(int n) {
        ++n;
        this.setContentDescription((CharSequence)String.format(this.getResources().getString(2131493170), n));
        this.title.setText(this.createTitleText(n, 2131493158));
        this.title.setClickable(false);
        this.synopsis.setText((CharSequence)"");
        this.synopsis.setVisibility(this.getDefaultSynopsisVisibility());
        this.setChecked(false);
    }
}
