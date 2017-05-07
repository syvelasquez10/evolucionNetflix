// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.details;

import com.netflix.mediaclient.android.widget.ErrorWrapper$Callback;
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
import android.view.View;
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
    protected View rowHeader;
    protected TextView synopsis;
    protected TextView title;
    
    public AbsEpisodeView(final Context context, final int resId) {
        super(context);
        this.resId = resId;
        this.init();
    }
    
    private CharSequence createStatusText(final int n, final int n2) {
        return this.getResources().getString(2131493234, new Object[] { n, this.getResources().getString(n2) });
    }
    
    private void init() {
        this.isCheckable = true;
        ((Activity)this.getContext()).getLayoutInflater().inflate(this.resId, (ViewGroup)this);
        this.findViews();
    }
    
    private void updateCheckability(final EpisodeDetails episodeDetails) {
        this.isCheckable = (episodeDetails.isAvailableToStream() && StringUtils.isNotEmpty(episodeDetails.getSynopsis()));
    }
    
    private void updateSynopsis(final EpisodeDetails episodeDetails) {
        if (this.synopsis == null) {
            return;
        }
        this.synopsis.setText((CharSequence)episodeDetails.getSynopsis());
        this.synopsis.setVisibility(this.getDefaultSynopsisVisibility());
    }
    
    protected CharSequence createTitleText(final EpisodeDetails episodeDetails) {
        if (episodeDetails.isAvailableToStream()) {
            return this.getResources().getString(2131493234, new Object[] { episodeDetails.getEpisodeNumber(), episodeDetails.getTitle() });
        }
        String s;
        if (StringUtils.isEmpty(episodeDetails.getAvailabilityDateMessage())) {
            s = this.getResources().getString(2131493160);
        }
        else {
            s = episodeDetails.getAvailabilityDateMessage();
        }
        return this.getResources().getString(2131493234, new Object[] { episodeDetails.getEpisodeNumber(), s });
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
    
    public void handleItemClick() {
        if (this.playButton != null) {
            this.playButton.performClick();
        }
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
        this.playButton.setOnClickListener((View$OnClickListener)new AbsEpisodeView$2(this, episodeDetails));
    }
    
    public void toggle() {
        this.setChecked(!this.checked);
    }
    
    public void update(final EpisodeDetails episodeDetails, final boolean isCurrentEpisode) {
        if (episodeDetails == null) {
            return;
        }
        this.isCurrentEpisode = isCurrentEpisode;
        this.setContentDescription((CharSequence)String.format(this.getResources().getString(2131493179), episodeDetails.getEpisodeNumber(), episodeDetails.getTitle(), episodeDetails.getSynopsis(), TimeUtils.convertSecondsToMinutes(episodeDetails.getPlayable().getRuntime())));
        this.title.setText(this.createTitleText(episodeDetails));
        final TextView title = this.title;
        final Resources resources = this.getResources();
        int n;
        if (episodeDetails.isAvailableToStream()) {
            n = 2131296371;
        }
        else {
            n = 2131296372;
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
    
    public void updateToErrorState(int n, final ErrorWrapper$Callback errorWrapper$Callback) {
        ++n;
        this.setContentDescription((CharSequence)String.format(this.getResources().getString(2131493178), n));
        this.title.setText(this.createStatusText(n, 2131492996));
        this.title.setClickable(true);
        this.title.setOnClickListener((View$OnClickListener)new AbsEpisodeView$1(this, errorWrapper$Callback));
        this.synopsis.setText((CharSequence)"");
        this.synopsis.setVisibility(this.getDefaultSynopsisVisibility());
        this.setChecked(false);
    }
    
    public void updateToLoadingState(int n) {
        ++n;
        this.setContentDescription((CharSequence)String.format(this.getResources().getString(2131493177), n));
        this.title.setText(this.createStatusText(n, 2131493165));
        this.title.setClickable(false);
        this.synopsis.setText((CharSequence)"");
        this.synopsis.setVisibility(this.getDefaultSynopsisVisibility());
        this.setChecked(false);
    }
}
