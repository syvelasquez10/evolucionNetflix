// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.details;

import android.content.res.Resources;
import com.netflix.mediaclient.util.TimeUtils;
import android.view.View$OnClickListener;
import com.netflix.mediaclient.ui.common.PlayContextProvider;
import com.netflix.mediaclient.ui.common.PlayContext;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.util.StringUtils;
import android.view.ViewGroup;
import android.app.Activity;
import android.content.Context;
import android.widget.TextView;
import android.widget.ProgressBar;
import android.widget.ImageView;
import com.netflix.mediaclient.servicemgr.interface_.details.EpisodeDetails;
import android.widget.Checkable;
import android.widget.RelativeLayout;

public abstract class AbsEpisodeView extends RelativeLayout implements Checkable, IEpisodeView<EpisodeDetails>
{
    private static final String TAG = "EpisodeRowView";
    protected boolean checked;
    private boolean isCheckable;
    protected boolean isCurrentEpisode;
    protected ImageView playButton;
    protected ProgressBar progressBar;
    protected int progressVal;
    private final int resId;
    protected TextView synopsis;
    protected TextView title;
    
    public AbsEpisodeView(final Context context, final int resId) {
        super(context);
        this.resId = resId;
        this.init();
    }
    
    private void init() {
        this.isCheckable = true;
        ((Activity)this.getContext()).getLayoutInflater().inflate(this.resId, (ViewGroup)this);
        this.findViews();
    }
    
    private void updateCheckability(final EpisodeDetails episodeDetails) {
        this.isCheckable = (episodeDetails.isAvailableToStream() && StringUtils.isNotEmpty(episodeDetails.getSynopsis()));
    }
    
    protected CharSequence createTitleText(final EpisodeDetails episodeDetails) {
        if (episodeDetails.isAvailableToStream()) {
            return this.getResources().getString(2131493235, new Object[] { episodeDetails.getEpisodeNumber(), episodeDetails.getTitle() });
        }
        String s;
        if (StringUtils.isEmpty(episodeDetails.getAvailabilityDateMessage())) {
            s = this.getResources().getString(2131493161);
        }
        else {
            s = episodeDetails.getAvailabilityDateMessage();
        }
        return this.getResources().getString(2131493235, new Object[] { episodeDetails.getEpisodeNumber(), s });
    }
    
    protected void findViews() {
        this.title = (TextView)this.findViewById(2131427513);
        this.synopsis = (TextView)this.findViewById(2131427515);
        this.playButton = (ImageView)this.findViewById(2131427511);
        this.progressBar = (ProgressBar)this.findViewById(2131427514);
    }
    
    protected int getDefaultSynopsisVisibility() {
        return 8;
    }
    
    public boolean isChecked() {
        return this.checked;
    }
    
    protected void playEpisode(final EpisodeDetails episodeDetails) {
        if (!(this.getContext() instanceof AbsEpisodeView$EpisodeRowListenerProvider)) {
            Log.w("EpisodeRowView", "Context is not an EpisodeRowListenerProvider, context: " + this.getContext());
            return;
        }
        final AbsEpisodeView$EpisodeRowListener episodeRowListener = ((AbsEpisodeView$EpisodeRowListenerProvider)this.getContext()).getEpisodeRowListener();
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
        if (this.synopsis != null) {
            final TextView synopsis = this.synopsis;
            int visibility;
            if (checked && this.isCheckable) {
                visibility = 0;
            }
            else {
                visibility = 8;
            }
            synopsis.setVisibility(visibility);
        }
        this.updateProgressBar();
    }
    
    protected void setupPlayButton(final EpisodeDetails episodeDetails) {
        if (this.playButton == null) {
            return;
        }
        final ImageView playButton = this.playButton;
        int visibility;
        if (episodeDetails.isAvailableToStream()) {
            visibility = 0;
        }
        else {
            visibility = 4;
        }
        playButton.setVisibility(visibility);
        this.playButton.setOnClickListener((View$OnClickListener)new AbsEpisodeView$1(this, episodeDetails));
    }
    
    public void toggle() {
        this.setChecked(!this.checked);
    }
    
    public void update(final EpisodeDetails episodeDetails, final boolean isCurrentEpisode) {
        if (episodeDetails == null) {
            return;
        }
        this.isCurrentEpisode = isCurrentEpisode;
        this.setContentDescription((CharSequence)String.format(this.getResources().getString(2131493180), episodeDetails.getEpisodeNumber(), episodeDetails.getTitle(), episodeDetails.getSynopsis(), TimeUtils.convertSecondsToMinutes(episodeDetails.getPlayable().getRuntime())));
        this.title.setText(this.createTitleText(episodeDetails));
        final TextView title = this.title;
        final Resources resources = this.getResources();
        int n;
        if (episodeDetails.isAvailableToStream()) {
            n = 2131230840;
        }
        else {
            n = 2131230841;
        }
        title.setTextColor(resources.getColor(n));
        this.title.setClickable(false);
        this.updateSynopsis(episodeDetails);
        this.updateBookmark(episodeDetails);
        this.setupPlayButton(episodeDetails);
        this.setChecked(false);
        this.updateCheckability(episodeDetails);
    }
    
    protected void updateBookmark(final EpisodeDetails episodeDetails) {
        int progressVal = 0;
        final int max = Math.max(0, episodeDetails.getBookmarkPosition());
        if (episodeDetails.getPlayable().getRuntime() > 0) {
            progressVal = max * 100 / episodeDetails.getPlayable().getRuntime();
        }
        this.progressVal = progressVal;
    }
    
    protected void updateProgressBar() {
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
    
    protected void updateSynopsis(final EpisodeDetails episodeDetails) {
        if (this.synopsis == null) {
            return;
        }
        this.synopsis.setText((CharSequence)episodeDetails.getSynopsis());
        this.synopsis.setVisibility(this.getDefaultSynopsisVisibility());
    }
}
