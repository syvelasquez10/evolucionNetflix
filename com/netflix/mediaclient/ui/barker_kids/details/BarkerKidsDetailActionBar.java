// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.barker_kids.details;

import android.content.Context;
import com.netflix.mediaclient.ui.kids.KidsUtils;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import com.netflix.mediaclient.android.widget.NetflixActionBar;

public class BarkerKidsDetailActionBar extends NetflixActionBar
{
    public BarkerKidsDetailActionBar(final NetflixActivity netflixActivity, final boolean b) {
        super(netflixActivity, b);
        this.setWidth();
    }
    
    @Override
    protected int getLayoutId() {
        return 2130903068;
    }
    
    public void setWidth() {
        this.toolbar.getLayoutParams().width = KidsUtils.getDetailsPageContentWidth((Context)this.getActivity());
    }
}
