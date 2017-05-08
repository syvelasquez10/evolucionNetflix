// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.details;

import android.content.res.Resources;
import com.netflix.mediaclient.ui.lomo.LoMoUtils;
import com.netflix.mediaclient.util.TimeUtils;
import com.netflix.mediaclient.ui.common.PlayLocationType;
import com.netflix.mediaclient.ui.common.PlayContextProvider;
import com.netflix.mediaclient.ui.common.PlayContext;
import com.netflix.mediaclient.Log;
import android.view.ViewGroup;
import android.app.Activity;
import com.netflix.mediaclient.util.StringUtils;
import android.content.Context;
import android.widget.ProgressBar;
import android.widget.ImageView;
import android.widget.TextView;
import com.netflix.mediaclient.servicemgr.interface_.details.EpisodeDetails;
import android.widget.Checkable;
import android.widget.RelativeLayout;

public abstract class AbsEpisodeView extends RelativeLayout implements Checkable, IEpisodeView<EpisodeDetails>
{
    private static final String TAG = "EpisodeRowView";
    protected boolean checked;
    protected TextView episodeBadge;
    protected TextView episodeDate;
    private boolean isCheckable;
    protected boolean isCurrentEpisode;
    protected boolean isNSRE;
    protected ImageView playButton;
    protected ProgressBar progressBar;
    protected int progressVal;
    private final int resId;
    protected TextView synopsis;
    protected TextView title;
    
    public AbsEpisodeView(final Context context, final int resId) {
        super(context);
        this.isNSRE = false;
        this.resId = resId;
        this.init();
    }
    
    public static CharSequence createTitleText(final EpisodeDetails episodeDetails, final Context context) {
        if (episodeDetails.isNSRE()) {
            return episodeDetails.getTitle();
        }
        if (episodeDetails.isAvailableToStream()) {
            return context.getString(2131231066, new Object[] { episodeDetails.getEpisodeNumber(), episodeDetails.getTitle() });
        }
        String s;
        if (StringUtils.isEmpty(episodeDetails.getAvailabilityDateMessage())) {
            s = context.getString(2131231120);
        }
        else {
            s = episodeDetails.getAvailabilityDateMessage();
        }
        return context.getString(2131231066, new Object[] { episodeDetails.getEpisodeNumber(), s });
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
        return createTitleText(episodeDetails, this.getContext());
    }
    
    protected void findViews() {
        this.episodeBadge = (TextView)this.findViewById(2131689626);
        this.title = (TextView)this.findViewById(2131689627);
        this.synopsis = (TextView)this.findViewById(2131689628);
        this.playButton = (ImageView)this.findViewById(2131689621);
        this.progressBar = (ProgressBar)this.findViewById(2131689623);
        this.episodeDate = (TextView)this.findViewById(2131689629);
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
            playContext.setPlayLocation(PlayLocationType.EPISODE);
            episodeRowListener.onEpisodeSelectedForPlayback(episodeDetails, playContext);
            return;
        }
        Log.e("EpisodeRowView", "No EpisodeRowListener provided: " + this.getContext());
    }
    
    public void setChecked(final boolean checked) {
        final boolean b = false;
        this.checked = checked;
        boolean b2;
        if (checked && this.isCheckable) {
            b2 = true;
        }
        else {
            b2 = false;
        }
        if (this.synopsis != null) {
            final TextView synopsis = this.synopsis;
            int visibility;
            if (b2) {
                visibility = 0;
            }
            else {
                visibility = 8;
            }
            synopsis.setVisibility(visibility);
        }
        if (this.episodeDate != null) {
            final TextView episodeDate = this.episodeDate;
            int visibility2;
            if (b2 && this.isNSRE) {
                visibility2 = (b ? 1 : 0);
            }
            else {
                visibility2 = 8;
            }
            episodeDate.setVisibility(visibility2);
        }
        this.updateProgressBar();
    }
    
    protected abstract void setupPlayButton(final EpisodeDetails p0);
    
    public void toggle() {
        this.setChecked(!this.checked);
    }
    
    public void update(final EpisodeDetails episodeDetails, final boolean isCurrentEpisode) {
        if (episodeDetails == null) {
            return;
        }
        this.isNSRE = episodeDetails.episodeIsNSRE();
        this.isCurrentEpisode = isCurrentEpisode;
        this.setContentDescription((CharSequence)String.format(this.getResources().getString(2131230888), episodeDetails.getEpisodeNumber(), episodeDetails.getTitle(), episodeDetails.getSynopsis(), TimeUtils.convertSecondsToMinutes(episodeDetails.getPlayable().getRuntime())));
        this.title.setText(createTitleText(episodeDetails, this.getContext()));
        final TextView title = this.title;
        final Resources resources = this.getResources();
        int n;
        if (episodeDetails.isAvailableToStream()) {
            n = 2131624099;
        }
        else {
            n = 2131624114;
        }
        title.setTextColor(resources.getColor(n));
        this.title.setClickable(false);
        if (this.episodeBadge != null) {
            LoMoUtils.toggleEpisodeBadge(episodeDetails.getEpisodeBadges(), this.episodeBadge);
        }
        if (episodeDetails.isNSRE() && this.episodeDate != null) {
            this.episodeDate.setText((CharSequence)episodeDetails.getAvailabilityDateMessage());
            this.episodeDate.setVisibility(0);
        }
        else if (this.episodeDate != null) {
            this.episodeDate.setVisibility(8);
        }
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
