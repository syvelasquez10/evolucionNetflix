// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.barker_kids;

import android.support.v7.widget.Toolbar;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import com.netflix.mediaclient.android.widget.NetflixActionBar;

public class BarkerKidsActionBar extends NetflixActionBar
{
    public BarkerKidsActionBar(final NetflixActivity netflixActivity, final boolean b) {
        super(netflixActivity, b);
    }
    
    @Override
    protected int getLayoutId() {
        return 2130903069;
    }
    
    @Override
    public void setSandwichIcon(final boolean b) {
        final Toolbar toolbar = this.toolbar;
        int navigationIcon;
        if (b) {
            navigationIcon = 2130837804;
        }
        else {
            navigationIcon = 2130837803;
        }
        toolbar.setNavigationIcon(navigationIcon);
    }
}
