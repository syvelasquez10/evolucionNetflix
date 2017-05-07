// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.details;

import com.netflix.mediaclient.android.widget.ErrorWrapper$Callback;
import android.view.View$OnClickListener;
import com.netflix.mediaclient.util.TimeUtils;
import com.netflix.mediaclient.servicemgr.model.details.EpisodeDetails;
import com.netflix.mediaclient.util.StringUtils;
import android.view.ViewGroup;
import android.app.Activity;
import android.util.AttributeSet;
import android.content.Context;
import android.widget.TextView;
import android.widget.ProgressBar;
import android.widget.ImageView;
import android.widget.Checkable;
import android.widget.RelativeLayout;

public class EpisodeRowView extends RelativeLayout implements Checkable
{
    private static final String TAG = "EpisodeRowView";
    private boolean checked;
    private boolean isCurrentEpisode;
    private ImageView playButton;
    private ProgressBar progressBar;
    private int progressVal;
    private TextView synopsis;
    private TextView title;
    
    public EpisodeRowView(final Context context) {
        super(context);
        this.init();
    }
    
    public EpisodeRowView(final Context context, final AttributeSet set) {
        super(context, set);
        this.init();
    }
    
    public EpisodeRowView(final Context context, final AttributeSet set, final int n) {
        super(context, set, n);
        this.init();
    }
    
    private CharSequence createTitleText(final int n, final int n2) {
        return this.createTitleText(n, this.getResources().getString(n2));
    }
    
    private CharSequence createTitleText(final int n, final String s) {
        return this.getResources().getString(2131493217, new Object[] { n, s });
    }
    
    private void init() {
        ((Activity)this.getContext()).getLayoutInflater().inflate(2130903090, (ViewGroup)this);
        this.setBackgroundResource(2130837850);
        this.title = (TextView)this.findViewById(2131165353);
        this.synopsis = (TextView)this.findViewById(2131165355);
        this.playButton = (ImageView)this.findViewById(2131165352);
        this.progressBar = (ProgressBar)this.findViewById(2131165354);
    }
    
    public void handleItemClick() {
        if (this.playButton != null) {
            this.playButton.performClick();
        }
    }
    
    public boolean isChecked() {
        return this.checked;
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
    
    public void toggle() {
        this.setChecked(!this.checked);
    }
    
    public void updateToEpisode(final EpisodeDetails episodeDetails, final boolean isCurrentEpisode) {
        this.isCurrentEpisode = isCurrentEpisode;
        this.setContentDescription((CharSequence)String.format(this.getResources().getString(2131493165), episodeDetails.getEpisodeNumber(), episodeDetails.getTitle(), episodeDetails.getSynopsis(), TimeUtils.convertSecondsToMinutes(episodeDetails.getPlayable().getRuntime())));
        this.title.setText(this.createTitleText(episodeDetails.getEpisodeNumber(), episodeDetails.getTitle()));
        this.title.setClickable(false);
        this.synopsis.setText((CharSequence)episodeDetails.getSynopsis());
        this.synopsis.setVisibility(8);
        final int max = Math.max(0, episodeDetails.getBookmarkPosition());
        int progressVal;
        if (episodeDetails.getPlayable().getRuntime() > 0) {
            progressVal = max * 100 / episodeDetails.getPlayable().getRuntime();
        }
        else {
            progressVal = 0;
        }
        this.progressVal = progressVal;
        this.playButton.setVisibility(0);
        this.playButton.setOnClickListener((View$OnClickListener)new EpisodeRowView$2(this, episodeDetails));
        this.setChecked(true);
    }
    
    public void updateToErrorState(int n, final ErrorWrapper$Callback errorWrapper$Callback) {
        ++n;
        this.setContentDescription((CharSequence)String.format(this.getResources().getString(2131493164), n));
        this.title.setText(this.createTitleText(n, 2131492982));
        this.title.setClickable(true);
        this.title.setOnClickListener((View$OnClickListener)new EpisodeRowView$1(this, errorWrapper$Callback));
        this.synopsis.setText((CharSequence)"");
        this.synopsis.setVisibility(8);
        this.setChecked(false);
    }
    
    public void updateToLoadingState(int n) {
        ++n;
        this.setContentDescription((CharSequence)String.format(this.getResources().getString(2131493163), n));
        this.title.setText(this.createTitleText(n, 2131493151));
        this.title.setClickable(false);
        this.synopsis.setText((CharSequence)"");
        this.synopsis.setVisibility(8);
        this.setChecked(false);
    }
}
