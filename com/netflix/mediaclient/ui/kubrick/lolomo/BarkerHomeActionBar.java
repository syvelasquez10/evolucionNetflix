// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.kubrick.lolomo;

import com.netflix.mediaclient.android.activity.NetflixActivity;
import com.netflix.mediaclient.android.widget.NetflixActionBar;

public class BarkerHomeActionBar extends NetflixActionBar
{
    public BarkerHomeActionBar(final NetflixActivity netflixActivity, final boolean b) {
        super(netflixActivity, b);
        if (this.toolbar != null) {
            this.toolbar.setBackgroundResource(2131623961);
        }
        this.showDropShadowIfAvailable();
    }
}