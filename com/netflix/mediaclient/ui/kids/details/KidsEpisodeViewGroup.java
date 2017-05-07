// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.kids.details;

import com.netflix.mediaclient.servicemgr.Playable;
import com.netflix.mediaclient.ui.common.PlaybackLauncher;
import android.view.View;
import android.view.View$OnClickListener;
import com.netflix.mediaclient.servicemgr.IClientLogging;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import com.netflix.mediaclient.servicemgr.EpisodeDetails;
import android.view.ViewGroup;
import android.view.LayoutInflater;
import android.util.AttributeSet;
import android.content.Context;
import android.widget.TextView;
import com.netflix.mediaclient.android.widget.AdvancedImageView;
import android.widget.RelativeLayout;

public class KidsEpisodeViewGroup extends RelativeLayout
{
    private AdvancedImageView img;
    private TextView title;
    
    public KidsEpisodeViewGroup(final Context context) {
        super(context);
        this.init();
    }
    
    public KidsEpisodeViewGroup(final Context context, final AttributeSet set) {
        super(context, set);
        this.init();
    }
    
    public KidsEpisodeViewGroup(final Context context, final AttributeSet set, final int n) {
        super(context, set, n);
        this.init();
    }
    
    private void init() {
        LayoutInflater.from(this.getContext()).inflate(2130903092, (ViewGroup)this, true);
        (this.img = (AdvancedImageView)this.findViewById(2131165388)).setCornerRadius(this.getResources().getDimensionPixelSize(2131361909));
        this.title = (TextView)this.findViewById(2131165389);
    }
    
    public void update(final EpisodeDetails episodeDetails) {
        final String string = this.getResources().getString(2131493249, new Object[] { episodeDetails.getSeasonNumber(), episodeDetails.getEpisodeNumber(), episodeDetails.getTitle() });
        this.title.setText((CharSequence)string);
        NetflixActivity.getImageLoader(this.getContext()).showImg(this.img, episodeDetails.getHorzDispUrl(), IClientLogging.AssetType.boxArt, string, false, true);
        this.setOnClickListener((View$OnClickListener)new View$OnClickListener() {
            public void onClick(final View view) {
                final KidsDetailsActivity kidsDetailsActivity = (KidsDetailsActivity)KidsEpisodeViewGroup.this.getContext();
                PlaybackLauncher.startPlaybackAfterPIN(kidsDetailsActivity, episodeDetails, kidsDetailsActivity.getPlayContext());
            }
        });
    }
}
