// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.details;

import com.netflix.mediaclient.android.widget.ErrorWrapper;
import com.netflix.mediaclient.ui.common.PlayContextProvider;
import com.netflix.mediaclient.ui.common.PlayContext;
import com.netflix.mediaclient.Log;
import android.view.View;
import android.view.View$OnClickListener;
import com.netflix.mediaclient.util.TimeUtils;
import com.netflix.mediaclient.servicemgr.EpisodeDetails;
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
        return this.getResources().getString(2131493247, new Object[] { n, s });
    }
    
    private void init() {
        ((Activity)this.getContext()).getLayoutInflater().inflate(2130903081, (ViewGroup)this);
        this.setBackgroundResource(2130837853);
        this.title = (TextView)this.findViewById(2131165331);
        this.synopsis = (TextView)this.findViewById(2131165333);
        this.playButton = (ImageView)this.findViewById(2131165330);
        this.progressBar = (ProgressBar)this.findViewById(2131165332);
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
        this.setContentDescription((CharSequence)String.format(this.getResources().getString(2131493194), episodeDetails.getEpisodeNumber(), episodeDetails.getTitle(), episodeDetails.getSynopsis(), TimeUtils.convertSecondsToMinutes(episodeDetails.getRuntime())));
        this.title.setText(this.createTitleText(episodeDetails.getEpisodeNumber(), episodeDetails.getTitle()));
        this.title.setClickable(false);
        this.synopsis.setText((CharSequence)episodeDetails.getSynopsis());
        this.synopsis.setVisibility(8);
        final int max = Math.max(0, episodeDetails.getBookmarkPosition());
        int progressVal;
        if (episodeDetails.getRuntime() > 0) {
            progressVal = max * 100 / episodeDetails.getRuntime();
        }
        else {
            progressVal = 0;
        }
        this.progressVal = progressVal;
        this.playButton.setVisibility(0);
        this.playButton.setOnClickListener((View$OnClickListener)new View$OnClickListener() {
            public void onClick(final View view) {
                if (!(EpisodeRowView.this.getContext() instanceof EpisodeRowListenerProvider)) {
                    Log.w("EpisodeRowView", "Context is not an EpisodeRowListenerProvider, context: " + EpisodeRowView.this.getContext());
                    return;
                }
                final EpisodeRowListener episodeRowListener = ((EpisodeRowListenerProvider)EpisodeRowView.this.getContext()).getEpisodeRowListener();
                if (episodeRowListener != null) {
                    PlayContext playContext = PlayContext.EMPTY_CONTEXT;
                    if (EpisodeRowView.this.getContext() instanceof PlayContextProvider) {
                        playContext = ((PlayContextProvider)EpisodeRowView.this.getContext()).getPlayContext();
                    }
                    episodeRowListener.onEpisodeSelectedForPlayback(episodeDetails, playContext);
                    return;
                }
                Log.w("EpisodeRowView", "No EpisodeRowListener provided: " + EpisodeRowView.this.getContext());
            }
        });
        this.setChecked(true);
    }
    
    public void updateToErrorState(int n, final ErrorWrapper.Callback callback) {
        ++n;
        this.setContentDescription((CharSequence)String.format(this.getResources().getString(2131493193), n));
        this.title.setText(this.createTitleText(n, 2131492978));
        this.title.setClickable(true);
        this.title.setOnClickListener((View$OnClickListener)new View$OnClickListener() {
            public void onClick(final View view) {
                callback.onRetryRequested();
            }
        });
        this.synopsis.setText((CharSequence)"");
        this.synopsis.setVisibility(8);
        this.setChecked(false);
    }
    
    public void updateToLoadingState(int n) {
        ++n;
        this.setContentDescription((CharSequence)String.format(this.getResources().getString(2131493192), n));
        this.title.setText(this.createTitleText(n, 2131493177));
        this.title.setClickable(false);
        this.synopsis.setText((CharSequence)"");
        this.synopsis.setVisibility(8);
        this.setChecked(false);
    }
    
    public interface EpisodeRowListener
    {
        void onEpisodeSelectedForPlayback(final EpisodeDetails p0, final PlayContext p1);
    }
    
    public interface EpisodeRowListenerProvider
    {
        EpisodeRowListener getEpisodeRowListener();
    }
}
