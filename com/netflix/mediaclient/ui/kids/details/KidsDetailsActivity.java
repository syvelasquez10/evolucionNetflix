// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.kids.details;

import com.netflix.mediaclient.android.widget.NetflixActionBar$LogoType;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.ui.common.PlayContextImp;
import android.os.Bundle;
import com.netflix.mediaclient.servicemgr.IClientLogging$ModalView;
import com.netflix.mediaclient.service.logging.client.model.DataContext;
import android.app.Fragment;
import com.netflix.mediaclient.servicemgr.ManagerStatusListener;
import com.netflix.mediaclient.servicemgr.model.VideoType;
import com.netflix.mediaclient.ui.common.PlayContext;
import com.netflix.mediaclient.ui.common.PlayContextProvider;
import com.netflix.mediaclient.android.activity.FragmentHostActivity;

public class KidsDetailsActivity extends FragmentHostActivity implements PlayContextProvider
{
    private static final String TAG = "KidsShowDetailsActivity";
    private PlayContext playContext;
    private String videoId;
    private VideoType videoType;
    
    @Override
    protected ManagerStatusListener createManagerStatusListener() {
        return new KidsDetailsActivity$1(this);
    }
    
    @Override
    protected Fragment createPrimaryFrag() {
        switch (KidsDetailsActivity$2.$SwitchMap$com$netflix$mediaclient$servicemgr$model$VideoType[this.videoType.ordinal()]) {
            default: {
                throw new IllegalStateException("Don't know how to handle type: " + this.videoType);
            }
            case 1: {
                return KidsMovieDetailsFrag.create(this.videoId);
            }
            case 2: {
                return KidsShowDetailsFrag.create(this.videoId);
            }
            case 3: {
                return KidsCharacterDetailsFrag.create(this.videoId);
            }
        }
    }
    
    @Override
    public DataContext getDataContext() {
        return new DataContext(this.playContext, this.videoId);
    }
    
    @Override
    public PlayContext getPlayContext() {
        return this.playContext;
    }
    
    @Override
    public IClientLogging$ModalView getUiScreen() {
        if (this.videoType == VideoType.CHARACTERS) {
            return IClientLogging$ModalView.characterDetails;
        }
        return IClientLogging$ModalView.movieDetails;
    }
    
    @Override
    public boolean isForKids() {
        return true;
    }
    
    @Override
    protected void onCreate(final Bundle bundle) {
        if (!this.getIntent().hasExtra("extra_video_type")) {
            throw new IllegalStateException("Start intent must provide extra value: extra_video_type");
        }
        this.videoType = (VideoType)this.getIntent().getSerializableExtra("extra_video_type");
        this.videoId = this.getIntent().getStringExtra("extra_video_id");
        this.playContext = (PlayContextImp)this.getIntent().getParcelableExtra("extra_playcontext");
        if (Log.isLoggable("KidsShowDetailsActivity", 2)) {
            Log.v("KidsShowDetailsActivity", "TRACK_ID: " + this.playContext.getTrackId());
        }
        super.onCreate(bundle);
        this.getNetflixActionBar().setLogoType(NetflixActionBar$LogoType.GONE);
        this.getNetflixActionBar().setTitle(this.getIntent().getStringExtra("extra_video_title"));
    }
}
