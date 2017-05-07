// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.kubrick.details;

import android.content.Context;
import com.netflix.mediaclient.ui.kubrick.KubrickUtils;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import com.netflix.mediaclient.android.widget.NetflixActionBar;

public class KubrickDetailActionBar extends NetflixActionBar
{
    public KubrickDetailActionBar(final NetflixActivity netflixActivity, final boolean b) {
        super(netflixActivity, b);
        this.setWidth();
    }
    
    @Override
    protected int getLayoutId() {
        return 2130903065;
    }
    
    public void setWidth() {
        this.toolbar.getLayoutParams().width = KubrickUtils.getDetailsPageContentWidth((Context)this.getActivity());
    }
}
