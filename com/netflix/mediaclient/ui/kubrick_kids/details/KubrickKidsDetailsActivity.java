// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.kubrick_kids.details;

import com.netflix.mediaclient.Log;
import android.os.Bundle;
import com.netflix.mediaclient.servicemgr.IClientLogging$ModalView;
import android.app.Fragment;
import com.netflix.mediaclient.servicemgr.ManagerStatusListener;
import android.view.View;
import com.netflix.mediaclient.android.widget.NetflixActionBar$LogoType;
import com.netflix.mediaclient.android.widget.NetflixActionBar;
import com.netflix.mediaclient.servicemgr.interface_.VideoType;
import com.netflix.mediaclient.ui.common.PlayContextProvider;
import com.netflix.mediaclient.ui.details.DetailsActivity;

public class KubrickKidsDetailsActivity extends DetailsActivity implements PlayContextProvider
{
    private static final String TAG = "KidsShowDetailsActivity";
    private VideoType videoType;
    
    @Override
    protected NetflixActionBar createActionBar() {
        final NetflixActionBar actionBar = super.createActionBar();
        final View viewById = this.findViewById(2131165294);
        if (viewById != null) {
            viewById.setVisibility(8);
        }
        actionBar.setLogoType(NetflixActionBar$LogoType.GONE);
        actionBar.setTitle("");
        return actionBar;
    }
    
    @Override
    protected ManagerStatusListener createManagerStatusListener() {
        return new KubrickKidsDetailsActivity$1(this);
    }
    
    @Override
    protected Fragment createPrimaryFrag() {
        switch (KubrickKidsDetailsActivity$2.$SwitchMap$com$netflix$mediaclient$servicemgr$interface_$VideoType[this.videoType.ordinal()]) {
            default: {
                throw new IllegalStateException("Don't know how to handle type: " + this.videoType);
            }
            case 1: {
                return KubrickKidsMovieDetailsFrag.create(this.videoId);
            }
            case 2: {
                return KubrickKidsShowDetailsFrag.create(this.videoId);
            }
            case 3: {
                return KubrickKidsCharacterDetailsFrag.create(this.videoId);
            }
        }
    }
    
    @Override
    public IClientLogging$ModalView getUiScreen() {
        if (this.videoType == VideoType.CHARACTERS) {
            return IClientLogging$ModalView.characterDetails;
        }
        return IClientLogging$ModalView.movieDetails;
    }
    
    @Override
    public VideoType getVideoType() {
        return this.videoType;
    }
    
    @Override
    protected void onCreate(final Bundle bundle) {
        if (!this.getIntent().hasExtra("extra_video_type")) {
            throw new IllegalStateException("Start intent must provide extra value: extra_video_type");
        }
        this.videoType = (VideoType)this.getIntent().getSerializableExtra("extra_video_type");
        super.onCreate(bundle);
        if (Log.isLoggable()) {
            Log.v("KidsShowDetailsActivity", "TRACK_ID: " + this.playContext.getTrackId());
        }
    }
}
