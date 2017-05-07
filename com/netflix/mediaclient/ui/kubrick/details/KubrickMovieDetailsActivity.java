// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.kubrick.details;

import com.netflix.mediaclient.ui.mdx.MdxMenu;
import android.view.Menu;
import android.os.Bundle;
import android.app.Fragment;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import com.netflix.mediaclient.ui.kubrick.KubrickDetailActionBar;
import com.netflix.mediaclient.android.widget.NetflixActionBar;
import com.netflix.mediaclient.ui.details.MovieDetailsActivity;

public class KubrickMovieDetailsActivity extends MovieDetailsActivity
{
    @Override
    protected NetflixActionBar createActionBar() {
        return new KubrickDetailActionBar(this, this.hasUpAction());
    }
    
    @Override
    protected Fragment createPrimaryFrag() {
        return KubrickMovieDetailsFrag.create(this.getVideoId());
    }
    
    @Override
    protected void onCreate(final Bundle bundle) {
        super.onCreate(bundle);
        this.getNetflixActionBar().hidelogo();
    }
    
    @Override
    protected void onCreateOptionsMenu(final Menu menu, final Menu menu2) {
        MdxMenu.addSelectPlayTarget(this, menu);
    }
}
