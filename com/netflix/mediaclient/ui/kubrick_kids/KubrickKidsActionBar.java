// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.kubrick_kids;

import android.content.Context;
import com.netflix.mediaclient.ui.kids.KidsUtils;
import android.support.v7.widget.Toolbar;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import com.netflix.mediaclient.android.widget.NetflixActionBar;

public class KubrickKidsActionBar extends NetflixActionBar
{
    public KubrickKidsActionBar(final NetflixActivity netflixActivity, final boolean b) {
        super(netflixActivity, b);
    }
    
    @Override
    protected int getLayoutId() {
        return 2130903066;
    }
    
    @Override
    public void setSandwichIcon(final boolean b) {
        final Toolbar toolbar = this.toolbar;
        int navigationIcon;
        if (b) {
            navigationIcon = 2130837721;
        }
        else {
            navigationIcon = 2130837720;
        }
        toolbar.setNavigationIcon(navigationIcon);
    }
    
    public void setWidth() {
        this.toolbar.getLayoutParams().width = KidsUtils.getDetailsPageContentWidth((Context)this.getActivity());
    }
}
