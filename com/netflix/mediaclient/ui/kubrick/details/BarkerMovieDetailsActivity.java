// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.kubrick.details;

import android.view.Menu;
import android.os.Bundle;
import com.netflix.mediaclient.ui.details.IHandleBackPress;
import android.app.Fragment;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import com.netflix.mediaclient.android.widget.NetflixActionBar;
import com.netflix.mediaclient.ui.details.MovieDetailsActivity;

public class BarkerMovieDetailsActivity extends MovieDetailsActivity
{
    private static final String TAG = "BarkerMovieDetailsActivity";
    
    @Override
    protected NetflixActionBar createActionBar() {
        final KubrickDetailActionBar kubrickDetailActionBar = new KubrickDetailActionBar(this, this.hasUpAction());
        kubrickDetailActionBar.setAlpha(0.0f);
        return kubrickDetailActionBar;
    }
    
    @Override
    protected Fragment createPrimaryFrag() {
        return BarkerMovieDetailsFrag.create(this.getVideoId());
    }
    
    @Override
    protected boolean handleBackPressed() {
        return ((IHandleBackPress)this.getPrimaryFrag()).handleBackPressed();
    }
    
    @Override
    protected void onCreate(final Bundle bundle) {
        super.onCreate(bundle);
        this.getNetflixActionBar().hidelogo();
    }
    
    @Override
    protected void onCreateOptionsMenu(final Menu menu, final Menu menu2) {
        super.onCreateOptionsMenu(menu, menu2);
        menu.removeItem(2131689472);
    }
}
