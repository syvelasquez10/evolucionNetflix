// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.kids.details;

import com.netflix.mediaclient.servicemgr.ShowDetails;
import com.netflix.mediaclient.util.TimeUtils;
import com.netflix.mediaclient.servicemgr.MovieDetails;
import com.netflix.mediaclient.servicemgr.KidsCharacterDetails;
import com.netflix.mediaclient.servicemgr.Playable;
import com.netflix.mediaclient.ui.common.PlaybackLauncher;
import com.netflix.mediaclient.ui.common.PlayContextProvider;
import android.view.View$OnClickListener;
import com.netflix.mediaclient.servicemgr.IClientLogging;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.servicemgr.VideoDetails;
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
        LayoutInflater.from(this.getContext()).inflate(2130903091, (ViewGroup)this, true);
        this.setOrientation(1);
        this.setBackgroundColor(this.getContext().getResources().getColor(2131296372));
        this.imgGroup = (ViewGroup)this.findViewById(2131165380);
        this.imgGroup.getLayoutParams().height = DeviceUtils.getScreenWidthInPixels(this.getContext());
        (this.img = (AdvancedImageView)this.findViewById(2131165381)).setCornerRadius(this.getResources().getDimensionPixelSize(2131361905));
        this.imgOverlay = this.findViewById(2131165382);
        this.imgTitle = (TextView)this.findViewById(2131165383);
        this.infoGroup = (ViewGroup)this.findViewById(2131165384);
        this.rating = (TextView)this.findViewById(2131165385);
        this.info = (TextView)this.findViewById(2131165386);
        this.synopsis = (TextView)this.findViewById(2131165387);
    }
    
    private void updateSharedDetails(final VideoDetails videoDetails) {
        Log.v("KidsDetailsViewGroup", "Updating details for video: " + videoDetails.getTitle());
        this.infoGroup.setVisibility(0);
        this.imgOverlay.setVisibility(8);
        this.imgTitle.setVisibility(8);
        this.rating.setText((CharSequence)videoDetails.getCertification());
        this.synopsis.setText((CharSequence)videoDetails.getSynopsis());
        NetflixActivity.getImageLoader(this.getContext()).showImg(this.img, videoDetails.getSquareUrl(), IClientLogging.AssetType.boxArt, videoDetails.getTitle(), false, true);
        this.img.setOnClickListener((View$OnClickListener)new View$OnClickListener() {
            public void onClick(final View view) {
                final NetflixActivity netflixActivity = (NetflixActivity)KidsDetailsViewGroup.this.getContext();
                PlaybackLauncher.startPlaybackAfterPIN(netflixActivity, videoDetails, ((PlayContextProvider)netflixActivity).getPlayContext());
            }
        });
    }
    
    public void updateDetails(final KidsCharacterDetails kidsCharacterDetails) {
        Log.v("KidsDetailsViewGroup", "Updating details for character: " + kidsCharacterDetails.getTitle());
        this.infoGroup.setVisibility(8);
        this.imgOverlay.setVisibility(0);
        this.imgTitle.setVisibility(0);
        this.imgTitle.setText((CharSequence)kidsCharacterDetails.getPlayableTitle());
        this.synopsis.setText((CharSequence)kidsCharacterDetails.getCharacterSynopsis());
        NetflixActivity.getImageLoader(this.getContext()).showImg(this.img, kidsCharacterDetails.getWatchNextDispUrl(), IClientLogging.AssetType.boxArt, kidsCharacterDetails.getTitle(), false, true);
        this.img.setOnClickListener((View$OnClickListener)new View$OnClickListener() {
            public void onClick(final View view) {
                final NetflixActivity netflixActivity = (NetflixActivity)KidsDetailsViewGroup.this.getContext();
                PlaybackLauncher.startPlaybackAfterPIN(netflixActivity, kidsCharacterDetails, ((PlayContextProvider)netflixActivity).getPlayContext());
            }
        });
    }
    
    public void updateDetails(final MovieDetails movieDetails) {
        this.updateSharedDetails(movieDetails);
        this.info.setText((CharSequence)String.format(this.getResources().getString(2131492953), TimeUtils.convertSecondsToMinutes(movieDetails.getRuntime())));
    }
    
    public void updateDetails(final ShowDetails showDetails) {
        this.updateSharedDetails(showDetails);
        this.info.setText((CharSequence)String.format(this.getResources().getString(2131492950), showDetails.getNumOfEpisodes()));
    }
}
