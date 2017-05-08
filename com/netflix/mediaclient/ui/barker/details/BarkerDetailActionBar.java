// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.barker.details;

import android.content.Context;
import com.netflix.mediaclient.ui.barker.BarkerUtils;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import com.netflix.mediaclient.android.widget.NetflixActionBar;

public class BarkerDetailActionBar extends NetflixActionBar
{
    public BarkerDetailActionBar(final NetflixActivity netflixActivity, final boolean b) {
        super(netflixActivity, b);
        this.setWidth();
    }
    
    @Override
    protected int getLayoutId() {
        return 2130903068;
    }
    
    public void setWidth() {
        this.toolbar.getLayoutParams().width = BarkerUtils.getDetailsPageContentWidth((Context)this.getActivity());
    }
}
