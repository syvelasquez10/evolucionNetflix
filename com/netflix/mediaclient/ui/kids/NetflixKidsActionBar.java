// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.kids;

import android.view.View$OnClickListener;
import com.netflix.mediaclient.util.ViewUtils;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.ColorDrawable;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import com.netflix.mediaclient.android.widget.NetflixActionBar;

public class NetflixKidsActionBar extends NetflixActionBar
{
    public NetflixKidsActionBar(final NetflixActivity netflixActivity, final boolean b) {
        super(netflixActivity, b);
        this.systemActionBar.setBackgroundDrawable((Drawable)new ColorDrawable(netflixActivity.getResources().getColor(2131296362)));
        ViewUtils.setTextViewSizeByRes(this.title, 2131361841);
        if (b) {
            final PerformUpActionOnClickListener performUpActionOnClickListener = new PerformUpActionOnClickListener(netflixActivity);
            this.logo.setOnClickListener((View$OnClickListener)performUpActionOnClickListener);
            this.title.setOnClickListener((View$OnClickListener)performUpActionOnClickListener);
        }
    }
    
    @Override
    protected int getFullSizeLogoId() {
        return 2130837747;
    }
    
    @Override
    public void onManagerReady() {
        if (this.hasUpAction) {
            this.configureBackButtonIfNecessary(false);
        }
    }
}
