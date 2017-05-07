// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.kids;

import com.netflix.mediaclient.android.activity.NetflixActivity;
import com.netflix.mediaclient.android.widget.NetflixActionBar;

public class KidsActionBar extends NetflixActionBar
{
    public KidsActionBar(final NetflixActivity netflixActivity, final boolean b) {
        super(netflixActivity, b);
    }
    
    @Override
    protected int getFullSizeLogoId() {
        return 2130837726;
    }
    
    @Override
    protected int getLayoutId() {
        return 2130903065;
    }
}
