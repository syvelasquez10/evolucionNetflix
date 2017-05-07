// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.kids;

import android.view.View$OnClickListener;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.ColorDrawable;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import com.netflix.mediaclient.android.widget.NetflixActionBar;

public class NetflixKidsActionBar extends NetflixActionBar
{
    public NetflixKidsActionBar(final NetflixActivity netflixActivity, final boolean b) {
        super(netflixActivity, b);
        this.systemActionBar.setBackgroundDrawable((Drawable)new ColorDrawable(netflixActivity.getResources().getColor(2131296359)));
        if (b) {
            this.title.setOnClickListener((View$OnClickListener)new PerformUpActionOnClickListener(netflixActivity));
            this.configureBackButtonIfNecessary();
        }
    }
    
    @Override
    protected int getFullSizeLogoId() {
        return 2130837732;
    }
}
