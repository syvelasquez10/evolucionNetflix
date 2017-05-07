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
import android.graphics.Color;
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
        this.setBackgroundColor(Color.parseColor("#3D7AA1"));
        this.imgGroup = (ViewGroup)this.findViewById(2131165376);
        this.imgGroup.getLayoutParams().height = (int)(DeviceUtils.getScreenWidthInPixels(this.getContext()) * 0.5625f);
        this.img = (AdvancedImageView)this.findViewById(2131165377);
        this.imgOverlay = this.findViewById(2131165378);
        this.imgTitle = (TextView)this.findViewById(2131165379);
        this.infoGroup = (ViewGroup)this.findViewById(2131165380);
        this.rating = (TextView)this.findViewById(2131165381);
        this.info = (TextView)this.findViewById(2131165382);
        this.synopsis = (TextView)this.findViewById(2131165383);
    }
    
    private void updateSharedDetails(final VideoDetails videoDetails) {
        Log.v("KidsDetailsViewGroup", "Updating details for video: " + videoDetails.getTitle());
        this.infoGroup.setVisibility(0);
        this.imgOverlay.setVisibility(8);
        this.imgTitle.setVisibility(8);
        this.rating.setText((CharSequence)videoDetails.getCertification());
        this.synopsis.setText((CharSequence)videoDetails.getSynopsis());
        NetflixActivity.getImageLoader(this.getContext()).showImg(this.img, videoDetails.getStoryUrl(), IClientLogging.AssetType.boxArt, videoDetails.getTitle(), true, true);
        this.img.setOnClickListener((View$OnClickListener)new View$OnClickListener() {
            public void onClick(final View view) {
                final NetflixActivity netflixActivity = (NetflixActivity)KidsDetailsViewGroup.this.getContext();
                PlaybackLauncher.startPlayback(netflixActivity, videoDetails, ((PlayContextProvider)netflixActivity).getPlayContext());
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
        NetflixActivity.getImageLoader(this.getContext()).showImg(this.img, kidsCharacterDetails.getStoryUrl(), IClientLogging.AssetType.boxArt, kidsCharacterDetails.getTitle(), true, true);
        this.img.setOnClickListener((View$OnClickListener)new View$OnClickListener() {
            public void onClick(final View view) {
                final NetflixActivity netflixActivity = (NetflixActivity)KidsDetailsViewGroup.this.getContext();
                PlaybackLauncher.startPlayback(netflixActivity, kidsCharacterDetails, ((PlayContextProvider)netflixActivity).getPlayContext());
            }
        });
    }
    
    public void updateDetails(final MovieDetails movieDetails) {
        this.updateSharedDetails(movieDetails);
        this.info.setText((CharSequence)String.format(this.getResources().getString(2131492954), TimeUtils.convertSecondsToMinutes(movieDetails.getRuntime())));
    }
    
    public void updateDetails(final ShowDetails showDetails) {
        this.updateSharedDetails(showDetails);
        this.info.setText((CharSequence)String.format(this.getResources().getString(2131492950), showDetails.getNumOfEpisodes()));
    }
}
