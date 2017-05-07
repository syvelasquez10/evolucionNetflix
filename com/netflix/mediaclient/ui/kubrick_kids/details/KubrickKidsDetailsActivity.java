// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.kubrick_kids.details;

import com.netflix.mediaclient.ui.mdx.MdxMenu;
import com.netflix.mediaclient.ui.common.DebugMenuItems;
import android.view.Menu;
import com.netflix.mediaclient.Log;
import android.os.Bundle;
import com.netflix.mediaclient.ui.details.IHandleBackPress;
import com.netflix.mediaclient.servicemgr.IClientLogging$ModalView;
import android.app.Fragment;
import com.netflix.mediaclient.servicemgr.ManagerStatusListener;
import android.view.View;
import com.netflix.mediaclient.android.widget.NetflixActionBar$LogoType;
import com.netflix.mediaclient.android.activity.NetflixActivity;
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
        final KubrickKidsDetailActionBar kubrickKidsDetailActionBar = new KubrickKidsDetailActionBar(this, this.hasUpAction());
        kubrickKidsDetailActionBar.setLogoType(NetflixActionBar$LogoType.GONE);
        kubrickKidsDetailActionBar.setTitle("");
        kubrickKidsDetailActionBar.setAlpha(0.0f);
        final View viewById = this.findViewById(2131624043);
        if (viewById != null) {
            viewById.setBackgroundColor(0);
        }
        return kubrickKidsDetailActionBar;
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
                return KubrickKidsCharacterDetailsFrag.create(this.videoId, this.getIntent().getIntExtra("extra_kids_color", 2131558489));
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
    protected boolean handleBackPressed() {
        return ((IHandleBackPress)this.getPrimaryFrag()).handleBackPressed();
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
    
    @Override
    protected void onCreateOptionsMenu(final Menu menu, final Menu menu2) {
        if (menu2 != null) {
            new DebugMenuItems("KidsShowDetailsActivity", this).addItems(this, menu2);
        }
        MdxMenu.addSelectPlayTarget(this, menu, false);
    }
    
    @Override
    protected boolean shouldShowKidsBackground() {
        return false;
    }
}
