// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.kids.details;

import com.netflix.mediaclient.servicemgr.model.details.ShowDetails;
import com.netflix.mediaclient.util.TimeUtils;
import com.netflix.mediaclient.servicemgr.model.details.MovieDetails;
import com.netflix.mediaclient.servicemgr.model.details.KidsCharacterDetails;
import com.netflix.mediaclient.ui.common.PlaybackLauncher;
import com.netflix.mediaclient.ui.common.PlayContextProvider;
import android.view.View$OnClickListener;
import com.netflix.mediaclient.servicemgr.IClientLogging;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.servicemgr.model.details.VideoDetails;
import com.netflix.mediaclient.util.StringUtils;
import com.netflix.mediaclient.util.DeviceUtils;
import android.view.LayoutInflater;
import android.util.AttributeSet;
import android.content.Context;
import android.widget.TextView;
import android.view.View;
import android.view.ViewGroup;
import com.netflix.mediaclient.android.widget.AdvancedImageView;
import android.widget.LinearLayout;

public class KidsDetailsViewGroup extends LinearLayout
{
    private static final String TAG = "KidsDetailsViewGroup";
    private AdvancedImageView img;
    private ViewGroup imgGroup;
    private View imgOverlay;
    private TextView imgTitle;
    private TextView info;
    private ViewGroup infoGroup;
    private TextView rating;
    private TextView synopsis;
    
    public KidsDetailsViewGroup(final Context context) {
        super(context);
        this.init();
    }
    
    public KidsDetailsViewGroup(final Context context, final AttributeSet set) {
        super(context, set);
        this.init();
    }
    
    public KidsDetailsViewGroup(final Context context, final AttributeSet set, final int n) {
        super(context, set, n);
        this.init();
    }
    
    private void init() {
        LayoutInflater.from(this.getContext()).inflate(2130903099, (ViewGroup)this, true);
        this.setOrientation(1);
        this.setBackgroundColor(this.getContext().getResources().getColor(2131296423));
        this.imgGroup = (ViewGroup)this.findViewById(2131165402);
        this.imgGroup.getLayoutParams().height = DeviceUtils.getScreenWidthInPixels(this.getContext());
        (this.img = (AdvancedImageView)this.findViewById(2131165404)).setCornerRadius(this.getResources().getDimensionPixelSize(2131361949));
        this.imgOverlay = this.findViewById(2131165405);
        this.imgTitle = (TextView)this.findViewById(2131165406);
        this.infoGroup = (ViewGroup)this.findViewById(2131165407);
        this.rating = (TextView)this.findViewById(2131165408);
        this.info = (TextView)this.findViewById(2131165409);
        this.synopsis = (TextView)this.findViewById(2131165410);
    }
    
    private static void setTextAndVisibility(final TextView textView, final String text) {
        textView.setText((CharSequence)text);
        int visibility;
        if (StringUtils.isEmpty(text)) {
            visibility = 8;
        }
        else {
            visibility = 0;
        }
        textView.setVisibility(visibility);
    }
    
    private void updateSharedDetails(final VideoDetails videoDetails) {
        Log.v("KidsDetailsViewGroup", "Updating details for video: " + videoDetails.getTitle());
        this.infoGroup.setVisibility(0);
        this.imgOverlay.setVisibility(8);
        this.imgTitle.setVisibility(8);
        this.rating.setText((CharSequence)videoDetails.getCertification());
        setTextAndVisibility(this.synopsis, videoDetails.getSynopsis());
        NetflixActivity.getImageLoader(this.getContext()).showImg(this.img, videoDetails.getSquareUrl(), IClientLogging.AssetType.boxArt, videoDetails.getTitle(), false, true);
        this.img.setOnClickListener((View$OnClickListener)new View$OnClickListener() {
            public void onClick(final View view) {
                final NetflixActivity netflixActivity = (NetflixActivity)KidsDetailsViewGroup.this.getContext();
                PlaybackLauncher.startPlaybackAfterPIN(netflixActivity, videoDetails.getPlayable(), ((PlayContextProvider)netflixActivity).getPlayContext());
            }
        });
    }
    
    public void updateDetails(final KidsCharacterDetails kidsCharacterDetails) {
        Log.v("KidsDetailsViewGroup", "Updating details for character: " + kidsCharacterDetails.getTitle());
        this.infoGroup.setVisibility(8);
        this.imgOverlay.setVisibility(0);
        this.imgTitle.setVisibility(0);
        this.imgTitle.setText((CharSequence)kidsCharacterDetails.getPlayable().getPlayableTitle());
        setTextAndVisibility(this.synopsis, kidsCharacterDetails.getCharacterSynopsis());
        NetflixActivity.getImageLoader(this.getContext()).showImg(this.img, kidsCharacterDetails.getWatchNextDispUrl(), IClientLogging.AssetType.boxArt, kidsCharacterDetails.getTitle(), false, true);
        this.img.setOnClickListener((View$OnClickListener)new View$OnClickListener() {
            public void onClick(final View view) {
                final NetflixActivity netflixActivity = (NetflixActivity)KidsDetailsViewGroup.this.getContext();
                PlaybackLauncher.startPlaybackAfterPIN(netflixActivity, kidsCharacterDetails.getPlayable(), ((PlayContextProvider)netflixActivity).getPlayContext());
            }
        });
    }
    
    public void updateDetails(final MovieDetails movieDetails) {
        this.updateSharedDetails(movieDetails);
        this.info.setText((CharSequence)String.format(this.getResources().getString(2131492958), TimeUtils.convertSecondsToMinutes(movieDetails.getPlayable().getRuntime())));
    }
    
    public void updateDetails(final ShowDetails showDetails) {
        this.updateSharedDetails(showDetails);
        this.info.setText((CharSequence)String.format(this.getResources().getString(2131492955), showDetails.getNumOfEpisodes()));
    }
}
